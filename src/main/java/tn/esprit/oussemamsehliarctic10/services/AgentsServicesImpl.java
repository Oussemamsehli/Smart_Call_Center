package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.Agents;
import tn.esprit.oussemamsehliarctic10.repositories.IAgents;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgentsServicesImpl implements AgentsServices {

    private final IAgents agentsRepository;

    @Override
    public Agents addAgents(Agents agents) {
        return agentsRepository.save(agents);
    }

    @Override
    public Agents updateAgents(Agents agents) {
        return agentsRepository.save(agents);
    }

    @Override
    public void deleteAgentsById(Long id) {
        agentsRepository.deleteById(id);
    }

    @Override
    public void deleteAgents(Agents agents) {
        agentsRepository.delete(agents);
    }

    @Override
    public Agents getById(Long id) {
        return agentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent with id " + id + " not found"));
    }

    @Override
    public List<Agents> getAll() {
        return agentsRepository.findAll();
    }
}