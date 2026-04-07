package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.dto.ProjectDTO;
import tn.esprit.oussemamsehliarctic10.entities.Projects;
import tn.esprit.oussemamsehliarctic10.entities.Agents;

import java.util.List;

public interface IProjectsServices {
    ProjectDTO addProject(Projects project);
    ProjectDTO updateProject(Projects project);
    void deleteProjectById(Long id);
    void deleteProject(Projects project);
    ProjectDTO getById(Long id);
    List<ProjectDTO> getAllProjects();
    List<Agents> getAgents(Long idProject);
    ProjectDTO assignedProject(Long projectId, Long agentId);
    ProjectDTO getProjectDetails(Long id);
}