package tn.esprit.oussemamsehliarctic10.services;


import tn.esprit.oussemamsehliarctic10.entities.AISystems;

import java.util.List;

public interface AiSystemsServices {

    AISystems addAiSystems(AISystems ai);

    AISystems updateAiSystems(AISystems ai);

    void deleteAiSystemsById(Long id);

    void deleteAiSystems(AISystems ai);

    AISystems getById(Long id);

    List<AISystems> getAll();
}