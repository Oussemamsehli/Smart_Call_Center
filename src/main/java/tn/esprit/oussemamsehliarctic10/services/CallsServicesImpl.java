package tn.esprit.oussemamsehliarctic10.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.oussemamsehliarctic10.entities.CallStatus;
import tn.esprit.oussemamsehliarctic10.entities.Calls;
import tn.esprit.oussemamsehliarctic10.repositories.IAgents;
import tn.esprit.oussemamsehliarctic10.repositories.ICalls;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CallsServicesImpl implements ICallsServices {

    private final ICalls callsRepository;
    private final IAgents agentsRepository;
    @Override
    public Calls addCalls(Calls calls) {

        calls.setCallsDateTime(LocalDateTime.now());
        calls.setStatus(CallStatus.ON_HOLD);

        return callsRepository.save(calls);    }


    @Override
    public Calls updateCalls(Calls calls) {
        return callsRepository.save(calls);    }

    @Override
    public void deleteCalls(Long id) {
        callsRepository.deleteById(Math.toIntExact(id));

    }

    @Override
    public Calls getById(Long id) {
        return callsRepository.findById(Math.toIntExact(id)).orElseThrow(()->new EntityNotFoundException("calls not found"));    }

    @Override
    public List<Calls> GetAllCalls() {
        return callsRepository.findAll();
    }


    @Override
    public void deleteCalls(Calls calls) {
        callsRepository.delete(calls);
    }
}
