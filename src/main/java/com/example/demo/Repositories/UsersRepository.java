package com.example.demo.Repositories;

import com.example.demo.Interfaces.JpaUserRepository;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UsersRepository extends JpaUserRepository {

    Page <User> findById(int id);
}
