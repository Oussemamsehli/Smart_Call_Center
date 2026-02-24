package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.ProjectDetails;
import tn.esprit.oussemamsehliarctic10.repositories.IProjectDetails;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectDetailsImpl implements ProjectDetailsServices {

    private final IProjectDetails projectDetailsRepository;

    @Override
    public ProjectDetails addProjectDetails(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    @Override
    public ProjectDetails updateProjectDetails(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }

    @Override
    public void deleteProjectDetailsById(Long id) {
        projectDetailsRepository.deleteById(id);
    }

    @Override
    public void deleteProjectDetails(ProjectDetails projectDetails) {
        projectDetailsRepository.delete(projectDetails);
    }

    @Override
    public ProjectDetails getById(Long id) {
        return projectDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project details not found with id: " + id));
    }

    @Override
    public List<ProjectDetails> getAll() {
        return projectDetailsRepository.findAll();
    }
}