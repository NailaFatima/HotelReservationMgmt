package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.CustomerSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class CustomerSessionRepositoryImpl implements CustomerSessionRepository {
    private Map<Long, CustomerSession> customerSessions;
    private int idCounter = 0;

    public CustomerSessionRepositoryImpl() {
        customerSessions = new HashMap<>();
    }

    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId() == 0){
            customerSession.setId(idCounter++);
        }
        customerSessions.put(customerSession.getId(), customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        Optional<CustomerSession> first = customerSessions.values().stream()
                .filter(customerSession -> customerSession.getUser().getId() == userId && customerSession.isActive()).findFirst();
        return first;

    }
}
