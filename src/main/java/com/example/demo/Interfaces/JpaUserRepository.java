package com.example.demo.Interfaces;

import com.example.demo.Entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaUserRepository {

    @Repository
    public interface JpaRepository<User, Long> {
        Users findByNome(int id);

    }
}
