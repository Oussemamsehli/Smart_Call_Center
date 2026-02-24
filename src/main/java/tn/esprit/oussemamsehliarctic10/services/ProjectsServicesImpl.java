package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.Projects;
import tn.esprit.oussemamsehliarctic10.repositories.IProjects;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectsServicesImpl implements IProjectsServices {

    private final IProjects projectsRepository;

    @Override
    public Projects addProject(Projects project) {
        if (project.getProjectDetails() != null) {
            project.getProjectDetails().setProject(project); //  lien owner
        }
        return projectsRepository.save(project);
    }

    @Override
    public Projects updateProject(Projects project) {
        return projectsRepository.save(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectsRepository.deleteById(id);
    }

    @Override
    public void deleteProject(Projects project) {
        projectsRepository.delete(project);
    }

    @Override
    public Projects getById(Long id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }

    @Override
    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public List<Agents> getAgents(Long idProject) {
        Projects project = projectsRepository.findById(idProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        return new ArrayList<>(project.getAgents());
    }
}