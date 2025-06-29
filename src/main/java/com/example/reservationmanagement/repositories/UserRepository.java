package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);

    User save(User user);
}
