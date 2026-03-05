package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.Calls;

import java.util.List;
import java.util.Set;

public interface ICallsServices {
    Calls addCalls(Calls calls);
    Calls updateCalls(Calls calls) ;
    void deleteCalls (Long id);
    Calls getById (Long id);
    List<Calls> GetAllCalls();
    void deleteCalls(Calls calls);

    Calls assignToAgent ( Long callsId, Long agentId);
    Calls assignToAgent ( Calls call, Long agentId);

    Calls assignCallToAISystem(Long callId, Long aiSystemId);
    boolean callRequiresHumanAgent(Calls call);

    void autoAssignCallsToAgents(Set<Long> callIds);

    void assignCallsToAgents(Set<Long> callsIds);



}
