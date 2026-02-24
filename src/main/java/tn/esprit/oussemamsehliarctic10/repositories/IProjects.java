package tn.esprit.oussemamsehliarctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.oussemamsehliarctic10.entities.Projects;

@Repository
public interface IProjects extends JpaRepository<Projects, Long> {
}