package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.dto.ProjectDTO;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.Projects;
import tn.esprit.oussemamsehliarctic10.mapper.ProjectMapper;
import tn.esprit.oussemamsehliarctic10.repositories.IAgents;
import tn.esprit.oussemamsehliarctic10.repositories.IProjects;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectsServicesImpl implements IProjectsServices {

    private final IProjects projectsRepository;
    private final IAgents agentsRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDTO addProject(Projects project) {
        return projectMapper.toDTO(projectsRepository.save(project));
    }

    @Override
    public ProjectDTO updateProject(Projects project) {
        return projectMapper.toDTO(projectsRepository.save(project));
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
    public ProjectDTO getById(Long id) {
        return projectMapper.toDTO(projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id)));
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectsRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .toList();
    }

    @Override
    public List<Agents> getAgents(Long idProject) {
        Projects project = projectsRepository.findById(idProject)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return new ArrayList<>(project.getAgents());
    }

    @Override
    public ProjectDTO assignedProject(Long projectId, Long agentId) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Agents agent = agentsRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found"));

        project.getAgents().add(agent);
        return projectMapper.toDTO(projectsRepository.save(project));
    }

    @Override
    public ProjectDTO getProjectDetails(Long id) {
        return projectMapper.toDTO(projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id)));
    }
}