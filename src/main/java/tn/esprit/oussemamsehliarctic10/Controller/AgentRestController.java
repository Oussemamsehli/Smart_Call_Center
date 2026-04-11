package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.CallSkills;
import tn.esprit.oussemamsehliarctic10.services.AgentsServices;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agents")
public class AgentRestController {

    private final AgentsServices agentsServices;

    @PostMapping("/add")
    public Agents addAgent(@RequestBody Agents agent) {
        return agentsServices.addAgents(agent);
    }

    @PutMapping("/update")
    public Agents updateAgent(@RequestBody Agents agent) {
        return agentsServices.updateAgents(agent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAgentById(@PathVariable Long id) {
        agentsServices.deleteAgentsById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAgent(@RequestBody Agents agent) {
        agentsServices.deleteAgents(agent);
    }

    @GetMapping("/get/{id}")
    public Agents getById(@PathVariable Long id) {
        return agentsServices.getById(id);
    }

    @GetMapping("/all")
    public List<Agents> getAll() {
        return agentsServices.getAll();
    }

    @PostMapping("/addAndAssignAllProjects")
    public Agents addAndAssignAllProjects(@RequestBody Agents agent) {
        return agentsServices.addAndassignToProject(agent);
    }
    // Q1 : GET /agents/available
    @GetMapping("/available")
    public List<Agents> getAvailableAgents() {
        return agentsServices.getAvailableAgents();
    }

    // Q2 : GET /agents/bySkill/TECHNICAL_SUPPORT
    @GetMapping("/bySkill/{skill}")
    public List<Agents> getAgentsBySkill(@PathVariable CallSkills skill) {
        return agentsServices.getAgentsBySkill(skill);
    }

    // Q3 : POST /agents/availableBySkills  (body = ["SALES", "BILLING"])
    @PostMapping("/availableBySkills")
    public List<Agents> getAvailableAgentsBySkills(@RequestBody Set<CallSkills> skills) {
        return agentsServices.getAvailableAgentsBySkills(skills);
    }
}