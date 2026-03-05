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

        return callsRepository.save(calls);    }


    @Override
    public Calls updateCalls(Calls calls) {
        return callsRepository.save(calls);    }

    @Override
    public void deleteCalls(Long id) {
        callsRepository.deleteById(id);
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
    public void deleteCalls(Calls calls) {
        callsRepository.delete(calls);
    }

    @Override
    public Calls assignToAgent(Long callsId, Long agentId) {
        Calls calls = callsRepository.findById(callsId)
                .orElseThrow(() -> new EntityNotFoundException("Call not found"));

        Agents agent = agentsRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found"));

        // L'agent doit être disponible
        if (!agent.getAvailable()) {
            throw new RuntimeException("Agent is not available");
        }

        calls.setAssignedAgent(agent);
        // L'appel passe à IN_PROGRESS
        calls.setStatus(CallStatus.IN_PROGRESS);
        // L'agent devient indisponible
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

        long activeCallsCount = aiSystem.getAssignedCalls().stream()
                .filter(c -> c.getStatus() == CallStatus.IN_PROGRESS || c.getStatus() == CallStatus.ON_HOLD)
                .count();

        if (activeCallsCount >= 2) {
            throw new RuntimeException("AI System already handles 2 calls");
        }

        call.setAssignedAiSystem(aiSystem);
        call.setStatus(CallStatus.IN_PROGRESS);

        return callsRepository.save(call);
    }


    @Override
    public boolean callRequiresHumanAgent(Calls call) {
        if (call.getRequiredSkills() == null) {
            return false;
        }
        return call.getRequiredSkills().contains(CallSkills.TECHNICAL_SUPPORT);
    }

    @Override
    public void autoAssignCallsToAgents(Set<Long> callIds) {
        for (Long callId : callIds) {
            Calls call = callsRepository.findById(callId)
                    .orElseThrow(() -> new EntityNotFoundException("Call not found: " + callId));

            if (!callRequiresHumanAgent(call)) {
                continue;
            }

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

            if (!callRequiresHumanAgent(call)) {
                continue;
            }

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


}
