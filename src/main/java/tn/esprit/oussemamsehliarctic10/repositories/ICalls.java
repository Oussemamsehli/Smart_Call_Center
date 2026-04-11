package tn.esprit.oussemamsehliarctic10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.oussemamsehliarctic10.entities.*;

import java.util.List;

public interface ICalls extends JpaRepository<Calls, Long> {

    // Compter les appels actifs d'un AI System
    long countByAssignedAiSystem(AISystems aiSystem);

    // Trouver les appels par statut
    List<Calls> findByStatus(CallStatus status);

    // Trouver les appels par statut ET par agent assigné
    List<Calls> findByStatusAndAssignedAgent_AgentId(CallStatus status, long agentId);

    // Trouver les appels sans agent assigné
    List<Calls> findByAssignedAgentIsNull();

    // Trouver les appels qui requièrent une compétence donnée
    List<Calls> findByRequiredSkillsContains(CallSkills skill);

    // Top 5 appels les plus anciens avec une compétence donnée
    List<Calls> findTop5ByRequiredSkillsContainsOrderByCallsDateTimeAsc(CallSkills skill);

    // Vérifier si un numéro de téléphone existe déjà
    boolean existsByPhoneNumber(String phoneNumber);

    // Compter les appels par statut
    long countByStatus(CallStatus status);

    // Q1 : Appels affectés à un agent donné
    @Query("SELECT c FROM Calls c JOIN c.assignedAgent a WHERE a.agentId = :idAgent")
    List<Calls> findCallsByAgent(@Param("idAgent") Long idAgent);

    // Q2 : Appels nécessitant une compétence donnée
    @Query("SELECT c FROM Calls c WHERE :skill MEMBER OF c.requiredSkills")
    List<Calls> findCallsBySkill(@Param("skill") CallSkills skill);

    // Q5 : Nombre d'appels par statut → GROUP BY
    @Query("SELECT c.status, COUNT(c) FROM Calls c GROUP BY c.status")
    List<Object[]> countCallsByStatus();

    // Q7 : Appels d'aujourd'hui → CURRENT_DATE
    @Query("SELECT c FROM Calls c WHERE FUNCTION('DATE', c.callsDateTime) = CURRENT_DATE")
    List<Calls> findTodayCalls();
}