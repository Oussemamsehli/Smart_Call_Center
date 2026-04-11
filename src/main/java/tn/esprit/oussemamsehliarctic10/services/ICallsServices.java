package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.CallSkills;
import tn.esprit.oussemamsehliarctic10.entities.CallStatus;
import tn.esprit.oussemamsehliarctic10.entities.Calls;

import java.util.List;
import java.util.Set;

public interface ICallsServices {

    // CRUD de base
    Calls addCalls(Calls calls);
    Calls updateCalls(Calls calls);
    void deleteCalls(Long id);
    void deleteCalls(Calls calls);
    Calls getById(Long id);
    List<Calls> GetAllCalls();

    // Assignation
    Calls assignToAgent(Long callsId, Long agentId);
    Calls assignToAgent(Calls call, Long agentId);
    Calls assignCallToAISystem(Long callId, Long aiSystemId);

    // Logique métier
    boolean callRequiresHumanAgent(Calls call);
    void autoAssignCallsToAgents(Set<Long> callIds);
    void assignCallsToAgents(Set<Long> callsIds);

    //  Query Methods
    List<Calls> findByStatus(CallStatus status);
    List<Calls> findByStatusAndAssignedAgent_AgentId(CallStatus status, long agentId);
    List<Calls> findByAssignedAgentIsNull();
    List<Calls> findByRequiredSkillsContains(CallSkills skill);
    List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(CallSkills skill);
    boolean existsByPhoneNumber(String phoneNumber);
    long countByStatus(CallStatus status);


    // JPQL
    List<Calls> findCallsByAgent(Long idAgent);
    List<Calls> findCallsBySkill(CallSkills skill);
    List<Object[]> countCallsByStatus();
    List<Calls> findTodayCalls();




}