package tn.esprit.oussemamsehliarctic10.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.oussemamsehliarctic10.entities.Calls;
import tn.esprit.oussemamsehliarctic10.services.ICallsServices;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calls")
public class CallRestController {

    private final ICallsServices callsServices;

    @PostMapping("/add")
    public Calls addCalls(@RequestBody Calls calls) {
        return callsServices.addCalls(calls);
    }

    @PutMapping("/update")
    public Calls updateCalls(@RequestBody Calls calls) {
        return callsServices.updateCalls(calls);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalls(@PathVariable Long id) {
        callsServices.deleteCalls(id);
    }

    @GetMapping("/get/{id}")
    public Calls getById(@PathVariable Long id) {
        return callsServices.getById(id);
    }


    @GetMapping("/all")
    public List<Calls> getAll() {
        return callsServices.GetAllCalls();
    }

    @DeleteMapping("/delete")
    public void deleteCalls(@RequestBody Calls calls) {
        callsServices.deleteCalls(calls);
    }
}