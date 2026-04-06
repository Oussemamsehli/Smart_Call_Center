package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.CallSkills;
import tn.esprit.oussemamsehliarctic10.entities.CallStatus;
import tn.esprit.oussemamsehliarctic10.entities.Calls;
import tn.esprit.oussemamsehliarctic10.services.ICallsServices;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calls")
public class CallRestController {

    private final ICallsServices callsServices;

    // ── CRUD ──────────────────────────────────────────────

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

    @DeleteMapping("/delete")
    public void deleteCalls(@RequestBody Calls calls) {
        callsServices.deleteCalls(calls);
    }

    @GetMapping("/get/{id}")
    public Calls getById(@PathVariable Long id) {
        return callsServices.getById(id);
    }

    @GetMapping("/all")
    public List<Calls> getAll() {
        return callsServices.GetAllCalls();
    }

    // ── ASSIGNATION ───────────────────────────────────────

    @PutMapping("/assignToAgent/{callsId}/{agentId}")
    public Calls assignToAgent(@PathVariable Long callsId,
                               @PathVariable Long agentId) {
        return callsServices.assignToAgent(callsId, agentId);
    }

    @PostMapping("/assignToAgent/{agentId}")
    public Calls assignToAgent(@RequestBody Calls call,
                               @PathVariable Long agentId) {
        return callsServices.assignToAgent(call, agentId);
    }

    @PutMapping("/assignToAISystem/{callId}/{aiSystemId}")
    public Calls assignToAISystem(@PathVariable Long callId,
                                  @PathVariable Long aiSystemId) {
        return callsServices.assignCallToAISystem(callId, aiSystemId);
    }

    // ── LOGIQUE MÉTIER ────────────────────────────────────

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

    // ── 🆕 QUERY METHODS ──────────────────────────────────

    // GET /calls/findByStatus/IN_PROGRESS
    @GetMapping("/findByStatus/{status}")
    public List<Calls> findByStatus(@PathVariable CallStatus status) {
        return callsServices.findByStatus(status);
    }

    // GET /calls/findByStatusAndAgentId/IN_PROGRESS/1
    @GetMapping("/findByStatusAndAgentId/{status}/{agentId}")
    public List<Calls> findByStatusAndAgentId(@PathVariable CallStatus status,
                                              @PathVariable long agentId) {
        return callsServices.findByStatusAndAssignedAgent_AgentId(status, agentId);
    }

    // GET /calls/findUnassigned
    @GetMapping("/findUnassigned")
    public List<Calls> findByAssignedAgentIsNull() {
        return callsServices.findByAssignedAgentIsNull();
    }

    // GET /calls/findBySkill/TECHNICAL_SUPPORT
    @GetMapping("/findBySkill/{skill}")
    public List<Calls> findBySkill(@PathVariable CallSkills skill) {
        return callsServices.findByRequiredSkillsContains(skill);
    }

    // GET /calls/top5BySkill/BILLING
    @GetMapping("/top5BySkill/{skill}")
    public List<Calls> findTop5BySkill(@PathVariable CallSkills skill) {
        return callsServices.findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(skill);
    }

    // GET /calls/existsByPhone/0612345678
    @GetMapping("/existsByPhone/{phoneNumber}")
    public boolean existsByPhoneNumber(@PathVariable String phoneNumber) {
        return callsServices.existsByPhoneNumber(phoneNumber);
    }

    // GET /calls/countByStatus/RESOLVED
    @GetMapping("/countByStatus/{status}")
    public long countByStatus(@PathVariable CallStatus status) {
        return callsServices.countByStatus(status);
    }
}