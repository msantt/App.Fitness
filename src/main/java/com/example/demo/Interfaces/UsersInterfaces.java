package com.example.demo.Interfaces;

import com.example.demo.Entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UsersInterfaces {

    public Users buscarPorId(int id);

    public List<Users> buscarTodos();

    public void salvar(Users users);

    public void atualizar(int id, Users users);

    public void deletar(int id);

}

