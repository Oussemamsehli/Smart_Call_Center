package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.AISystems;
import tn.esprit.oussemamsehliarctic10.services.AiSystemsServices;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai-systems")
public class AiSystemRestController {

    private final AiSystemsServices aiSystemsServices;

    @PostMapping("/add")
    public AISystems addAiSystem(@RequestBody AISystems aiSystem) {
        return aiSystemsServices.addAiSystems(aiSystem);
    }

    @PutMapping("/update")
    public AISystems updateAiSystem(@RequestBody AISystems aiSystem) {
        return aiSystemsServices.updateAiSystems(aiSystem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAiSystemById(@PathVariable Long id) {
        aiSystemsServices.deleteAiSystemsById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAiSystem(@RequestBody AISystems aiSystem) {
        aiSystemsServices.deleteAiSystems(aiSystem);
    }

    @GetMapping("/get/{id}")
    public AISystems getById(@PathVariable Long id) {
        return aiSystemsServices.getById(id);
    }

    @GetMapping("/all")
    public List<AISystems> getAll() {
        return aiSystemsServices.getAll();
    }
}