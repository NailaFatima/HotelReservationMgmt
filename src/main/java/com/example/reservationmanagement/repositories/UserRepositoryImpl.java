package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository {
    private Map<Long, User> users;
    private int idCounter = 0;

    public UserRepositoryImpl() {
        users = new HashMap<>();
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> first = users.values().stream().filter(user -> user.getId() == id).findFirst();
        return first;
    }

    @Override
    public User save(User user) {

        if(user.getId() == 0){
            user.setId(idCounter++);
        }
        users.put(user.getId(), user);
        return user;
    }
}
