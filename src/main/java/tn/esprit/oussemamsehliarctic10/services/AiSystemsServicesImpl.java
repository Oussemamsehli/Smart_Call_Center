package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.AISystems;
import tn.esprit.oussemamsehliarctic10.repositories.IASystems;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AiSystemsServicesImpl implements AiSystemsServices {

    private final IASystems aiSystemsRepository;

    @Override
    public AISystems addAiSystems(AISystems ai) {
        return aiSystemsRepository.save(ai);
    }

    @Override
    public AISystems updateAiSystems(AISystems ai) {
        return aiSystemsRepository.save(ai);
    }

    @Override
    public void deleteAiSystemsById(Long id) {
        aiSystemsRepository.deleteById(id);
    }

    @Override
    public void deleteAiSystems(AISystems ai) {
        aiSystemsRepository.delete(ai);
    }

    @Override
    public AISystems getById(Long id) {
        return aiSystemsRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("AI System not found with id: " + id));
    }

    @Override
    public List<AISystems> getAll() {
        return aiSystemsRepository.findAll();
    }
}