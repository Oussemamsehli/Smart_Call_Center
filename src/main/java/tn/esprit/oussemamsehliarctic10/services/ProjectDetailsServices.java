package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.ProjectDetails;

import java.util.List;

public interface ProjectDetailsServices {

    ProjectDetails addProjectDetails(ProjectDetails projectDetails);

    ProjectDetails updateProjectDetails(ProjectDetails projectDetails);

    void deleteProjectDetailsById(Long id);

    void deleteProjectDetails(ProjectDetails projectDetails);

    ProjectDetails getById(Long id);

    List<ProjectDetails> getAll();
}