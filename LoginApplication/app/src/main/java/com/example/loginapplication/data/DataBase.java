package com.example.loginapplication.data;

import com.example.loginapplication.model.User;

import java.util.ArrayList;

public class DataBase {
    ArrayList<User> contasBanco = new ArrayList<User>() {
        {
            add(new User(1, "Usuario 1", "user1", "123"));
            add(new User(2, "Usuario 2", "user2", "12345"));
        }
    };

    public Boolean procurarUsuario(String email, String password) {
        for (int i = 0; i < contasBanco.size(); i++) {
            if (contasBanco.get(i).getEmail().equals(email) && contasBanco.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
