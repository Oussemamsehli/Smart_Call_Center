package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.entities.CallSkills;

import java.util.List;
import java.util.Set;

public interface AgentsServices {

    Agents addAgents(Agents agents);

    Agents updateAgents(Agents agents);

    void deleteAgentsById(Long id);

    void deleteAgents(Agents agents);

    Agents getById(Long id);

    List<Agents> getAll();

    Agents addAndassignToProject (Agents agents);

    List<Agents> getAvailableAgents();

    List<Agents> getAgentsBySkill(CallSkills skill);

    List<Agents> getAvailableAgentsBySkills(Set<CallSkills> skills);
}