package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.ProjectDetails;
import tn.esprit.oussemamsehliarctic10.services.ProjectDetailsServices;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project-details")
public class ProjectDetailsRestController {

    private final ProjectDetailsServices projectDetailsServices;

    @PostMapping("/add")
    public ProjectDetails addProjectDetails(@RequestBody ProjectDetails projectDetails) {
        return projectDetailsServices.addProjectDetails(projectDetails);
    }

    @PutMapping("/update")
    public ProjectDetails updateProjectDetails(@RequestBody ProjectDetails projectDetails) {
        return projectDetailsServices.updateProjectDetails(projectDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProjectDetailsById(@PathVariable Long id) {
        projectDetailsServices.deleteProjectDetailsById(id);
    }

    @DeleteMapping("/delete")
    public void deleteProjectDetails(@RequestBody ProjectDetails projectDetails) {
        projectDetailsServices.deleteProjectDetails(projectDetails);
    }

    @GetMapping("/get/{id}")
    public ProjectDetails getById(@PathVariable Long id) {
        return projectDetailsServices.getById(id);
    }

    @GetMapping("/all")
    public List<ProjectDetails> getAll() {
        return projectDetailsServices.getAll();
    }
}