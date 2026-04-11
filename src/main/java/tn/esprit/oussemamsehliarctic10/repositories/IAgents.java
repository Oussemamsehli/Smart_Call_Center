package tn.esprit.oussemamsehliarctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.CallSkills;

import java.util.List;
import java.util.Set;

public interface IAgents extends JpaRepository<Agents, Long> {

    // Q1 : Rechercher les agents disponibles

    List<Agents> findByAvailableTrue();

    // Q2 : Agents ayant une compétence donnée

    List<Agents> findBySkillsContaining(CallSkills skill);

    // Q3 : Agents disponibles ET compétents pour un appel

    List<Agents> findByAvailableTrueAndSkillsIn(Set<CallSkills> skills);
}