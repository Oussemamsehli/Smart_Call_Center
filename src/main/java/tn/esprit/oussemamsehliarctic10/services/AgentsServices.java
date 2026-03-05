package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.Agents;

import java.util.List;

public interface AgentsServices {

    Agents addAgents(Agents agents);

    Agents updateAgents(Agents agents);

    void deleteAgentsById(Long id);

    void deleteAgents(Agents agents);

    Agents getById(Long id);

    List<Agents> getAll();

    Agents addAndassignToProject (Agents agents);
}