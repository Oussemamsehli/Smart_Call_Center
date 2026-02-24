package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.Projects;
import tn.esprit.oussemamsehliarctic10.services.IProjectsServices;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectRestController {

    private final IProjectsServices projectsServices;

    @PostMapping("/add")
    public Projects addProject(@RequestBody Projects project) {
        return projectsServices.addProject(project);
    }

    @PutMapping("/update")
    public Projects updateProject(@RequestBody Projects project) {
        return projectsServices.updateProject(project);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectsServices.deleteProjectById(id);
    }

    @GetMapping("/get/{id}")
    public Projects getById(@PathVariable Long id) {
        return projectsServices.getById(id);
    }

    @GetMapping("/all")
    public List<Projects> getAll() {
        return projectsServices.getAllProjects();
    }

    @DeleteMapping("/delete")
    public void deleteProject(@RequestBody Projects project) {
        projectsServices.deleteProject(project);
    }

    @GetMapping("/{id}/agents")
    public List<Agents> getAgentsByProject(@PathVariable Long id) {
        return projectsServices.getAgents(id);
    }
}