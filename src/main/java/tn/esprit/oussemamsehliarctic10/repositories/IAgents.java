package tn.esprit.oussemamsehliarctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.oussemamsehliarctic10.entities.Agents;

public interface IAgents extends JpaRepository<Agents, Long> {
}