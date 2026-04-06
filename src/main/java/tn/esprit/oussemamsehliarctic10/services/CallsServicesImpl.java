package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.*;
import tn.esprit.oussemamsehliarctic10.repositories.IASystems;
import tn.esprit.oussemamsehliarctic10.repositories.IAgents;
import tn.esprit.oussemamsehliarctic10.repositories.ICalls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CallsServicesImpl implements ICallsServices {

    private final ICalls callsRepository;
    private final IAgents agentsRepository;
    private final IASystems aiSystemsRepository;

    @Override
    public Calls addCalls(Calls calls) {
        calls.setCallsDateTime(LocalDateTime.now());
        calls.setStatus(CallStatus.ON_HOLD);
        return callsRepository.save(calls);
    }

    @Override
    public Calls updateCalls(Calls calls) {
        return callsRepository.save(calls);
    }

    @Override
    public void deleteCalls(Long id) {
        callsRepository.deleteById(id);
    }

    @Override
    public void deleteCalls(Calls calls) {
        callsRepository.delete(calls);
    }

    @Override
    public Calls getById(Long id) {
        return callsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Call not found"));
    }

    @Override
    public List<Calls> GetAllCalls() {
        return callsRepository.findAll();
    }

    @Override
    public Calls assignToAgent(Long callsId, Long agentId) {
        Calls calls = callsRepository.findById(callsId)
                .orElseThrow(() -> new EntityNotFoundException("Call not found"));

        Agents agent = agentsRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found"));

        if (!agent.getAvailable()) {
            throw new RuntimeException("Agent is not available");
        }

        calls.setAssignedAgent(agent);
        calls.setStatus(CallStatus.IN_PROGRESS);
        agent.setAvailable(false);

        agentsRepository.save(agent);
        return callsRepository.save(calls);
    }

    @Override
    public Calls assignToAgent(Calls call, Long agentId) {
        Agents agent = agentsRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found"));

        if (!agent.getAvailable()) {
            throw new RuntimeException("Agent is not available");
        }

        call.setAssignedAgent(agent);
        call.setStatus(CallStatus.IN_PROGRESS);
        agent.setAvailable(false);

        agentsRepository.save(agent);
        return callsRepository.save(call);
    }

    @Override
    public Calls assignCallToAISystem(Long callId, Long aiSystemId) {
        Calls call = callsRepository.findById(callId)
                .orElseThrow(() -> new EntityNotFoundException("Call not found"));

        AISystems aiSystem = aiSystemsRepository.findById(aiSystemId)
                .orElseThrow(() -> new EntityNotFoundException("AI System not found"));

        if (!aiSystem.getAvailable()) {
            throw new RuntimeException("AI System is not available");
        }

        //  Utilise maintenant le repository 
        long activeCallsCount = callsRepository.countByAssignedAiSystem(aiSystem);

        if (activeCallsCount >= 2) {
            throw new RuntimeException("AI System already handles 2 calls");
        }

        call.setAssignedAiSystem(aiSystem);
        call.setStatus(CallStatus.IN_PROGRESS);

        // Si l'IA atteint 2 appels, elle devient indisponible
        if (activeCallsCount + 1 >= 2) {
            aiSystem.setAvailable(false);
            aiSystemsRepository.save(aiSystem);
        }

        return callsRepository.save(call);
    }

    @Override
    public boolean callRequiresHumanAgent(Calls call) {
        if (call.getRequiredSkills() == null) return false;
        return call.getRequiredSkills().contains(CallSkills.TECHNICAL_SUPPORT);
    }

    @Override
    public void autoAssignCallsToAgents(Set<Long> callIds) {
        for (Long callId : callIds) {
            Calls call = callsRepository.findById(callId)
                    .orElseThrow(() -> new EntityNotFoundException("Call not found: " + callId));

            if (!callRequiresHumanAgent(call)) continue;

            List<Agents> allAgents = agentsRepository.findAll();
            for (Agents agent : allAgents) {
                if (agent.getAvailable() && agent.getSkills() != null
                        && agent.getSkills().stream()
                        .anyMatch(skill -> call.getRequiredSkills().contains(skill))) {

                    call.setAssignedAgent(agent);
                    call.setStatus(CallStatus.IN_PROGRESS);
                    callsRepository.save(call);
                    break;
                }
            }
        }
    }

    @Override
    public void assignCallsToAgents(Set<Long> callsIds) {
        for (Long callId : callsIds) {
            Calls call = callsRepository.findById(callId)
                    .orElseThrow(() -> new EntityNotFoundException("Call not found: " + callId));

            if (!callRequiresHumanAgent(call)) continue;

            List<Agents> allAgents = agentsRepository.findAll();
            for (Agents agent : allAgents) {
                if (agent.getAvailable() && agent.getSkills() != null
                        && agent.getSkills().stream()
                        .anyMatch(skill -> call.getRequiredSkills().contains(skill))) {

                    call.setAssignedAgent(agent);
                    call.setStatus(CallStatus.IN_PROGRESS);
                    agent.setAvailable(false);

                    agentsRepository.save(agent);
                    callsRepository.save(call);
                    break;
                }
            }
        }
    }

    // Query Methods implementations

    @Override
    public List<Calls> findByStatus(CallStatus status) {
        return callsRepository.findByStatus(status);
    }

    @Override
    public List<Calls> findByStatusAndAssignedAgent_AgentId(CallStatus status, long agentId) {
        return callsRepository.findByStatusAndAssignedAgent_AgentId(status, agentId);
    }

    @Override
    public List<Calls> findByAssignedAgentIsNull() {
        return callsRepository.findByAssignedAgentIsNull();
    }

    @Override
    public List<Calls> findByRequiredSkillsContains(CallSkills skill) {
        return callsRepository.findByRequiredSkillsContains(skill);
    }

    @Override
    public List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(CallSkills skill) {
        return callsRepository.findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(skill);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return callsRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public long countByStatus(CallStatus status) {
        return callsRepository.countByStatus(status);
    }
}