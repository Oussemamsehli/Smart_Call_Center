package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.Projects;
import tn.esprit.oussemamsehliarctic10.entities.Agents;

import java.util.List;

public interface IProjectsServices {

    // CRUD classique
    Projects addProject(Projects project);

    Projects updateProject(Projects project);

    void deleteProjectById(Long id);

    void deleteProject(Projects project);

    Projects getById(Long id);

    List<Projects> getAllProjects();

    // Relation spécifique
    List<Agents> getAgents(Long idProject);

    Projects assignedProject(Long projectId, Long agentId);
}