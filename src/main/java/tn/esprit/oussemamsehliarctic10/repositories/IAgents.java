package tn.esprit.oussemamsehliarctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.CallSkills;
import tn.esprit.oussemamsehliarctic10.entities.Calls;

import java.util.List;
import java.util.Set;

public interface IAgents extends JpaRepository<Agents, Long> {

    // Q1 : Rechercher les agents disponibles

    List<Agents> findByAvailableTrue();

    // Q2 : Agents ayant une compétence donnée

    List<Agents> findBySkillsContaining(CallSkills skill);

    // Q3 : Agents disponibles ET compétents pour un appel

    List<Agents> findByAvailableTrueAndSkillsIn(Set<CallSkills> skills);


    // Q3 : Agents possédant une compétence donnée
    @Query("SELECT a FROM Agents a WHERE :skill MEMBER OF a.skills")
    List<Agents> findAgentsBySkill(@Param("skill") CallSkills skill);

    // Q4 : Agent le plus compétent et disponible pour un appel

    @Query("SELECT a FROM Agents a JOIN a.skills s " +
            "WHERE a.available = true " +
            "AND s IN (SELECT rs FROM Calls c JOIN c.requiredSkills rs WHERE c.callsId = :callsId) " +
            "GROUP BY a " +
            "ORDER BY COUNT(s) DESC " +
            "LIMIT 1")
    Agents findMostCompetentAgentForCall(@Param("callsId") Long callsId);

    // Q6 : Agents ayant traité plus de 5 appels → GROUP BY + HAVING
    @Query("SELECT a.name, COUNT(c) FROM Agents a JOIN a.assignedCalls c " +
            "GROUP BY a " +
            "HAVING COUNT(c) > 5")
    List<Object[]> findTopActiveAgents();

}