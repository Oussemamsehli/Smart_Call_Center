package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.Calls;
import tn.esprit.oussemamsehliarctic10.services.ICallsServices;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calls")
public class CallRestController {

    private final ICallsServices callsServices;

    @PostMapping("/add")
    public Calls addCalls(@RequestBody Calls calls) {
        return callsServices.addCalls(calls);
    }

    @PutMapping("/update")
    public Calls updateCalls(@RequestBody Calls calls) {
        return callsServices.updateCalls(calls);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalls(@PathVariable Long id) {
        callsServices.deleteCalls(id);
    }

    @GetMapping("/get/{id}")
    public Calls getById(@PathVariable Long id) {
        return callsServices.getById(id);
    }


    @GetMapping("/all")
    public List<Calls> getAll() {
        return callsServices.GetAllCalls();
    }

    @DeleteMapping("/delete")
    public void deleteCalls(@RequestBody Calls calls) {
        callsServices.deleteCalls(calls);
    }

    @PutMapping("/assignToAgent/{callsId}/{agentId}")
    public Calls assignToAgent (@PathVariable Long callsId, @PathVariable Long agentId) {

        return callsServices.assignToAgent(callsId, agentId);

    }

    @PostMapping("/assignToAgent/{agentId}")
    public Calls assignToAgent(@RequestBody Calls call,
                               @PathVariable Long agentId) {

        return callsServices.assignToAgent(call, agentId);
    }

    @PutMapping("/assignToAISystem/{callId}/{aiSystemId}")
    public Calls assignToAISystem(@PathVariable Long callId, @PathVariable Long aiSystemId) {
        return callsServices.assignCallToAISystem(callId, aiSystemId);
    }

    @PostMapping("/requiresHumanAgent")
    public boolean callRequiresHumanAgent(@RequestBody Calls call) {
        return callsServices.callRequiresHumanAgent(call);
    }

    @PutMapping("/autoAssign")
    public void autoAssignCallsToAgents(@RequestBody Set<Long> callIds) {
        callsServices.autoAssignCallsToAgents(callIds);
    }

    @PutMapping("/assignAll")
    public void assignCallsToAgents(@RequestBody Set<Long> callsIds) {
        callsServices.assignCallsToAgents(callsIds);
    }


}