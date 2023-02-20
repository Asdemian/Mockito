package com.skypro.mocki;

import com.skypro.mocki.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object getAllLogin() {
        try {
            Collection<User> users = this.userRepository
                    .getAllUser();
            if (users == null) {
                return 0;
            }
            return users
                    .stream()
                    .map(User::getLogin)
                    .collect(Collectors.toList());

        } catch (RuntimeException e) {
            return 0;
        }
    }

    public void addUser(String login, String password) {
        User user = new User(login, password);

        if (login == null || login.isBlank() || login.isEmpty()
                && password == null || password.isBlank() || password.isEmpty()) {
            throw new IllegalArgumentException("Необходимо заполнить");
        }
        boolean userExist = this.userRepository
                .getAllUser()
                .stream()
                .allMatch(t -> t.equals(user));
        if (userExist) {
            throw new IllegalArgumentException("User Уже существует");
        }
        this.userRepository.addUser(user);
    }
}
