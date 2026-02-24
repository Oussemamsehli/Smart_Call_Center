package tn.esprit.oussemamsehliarctic10.services;

import tn.esprit.oussemamsehliarctic10.entities.Calls;

import java.util.List;

public interface ICallsServices {
    Calls addCalls(Calls calls);
    Calls updateCalls(Calls calls) ;
    void deleteCalls (Long id);
    Calls getById (Long id);
    List<Calls> GetAllCalls();
    void deleteCalls(Calls calls);


}
