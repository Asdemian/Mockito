package com.skypro.mocki;

import java.util.*;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public Collection<User> getAllUser() {
        return Collections.unmodifiableCollection(users);
    }

    public Optional<User> findUserByLogin(String login) {
        for (User user : users
        ) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        for (User user : users
        )
            if (user.getLogin().equals(login) || user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        return Optional.empty();
    }

    public User addUser(User user) {
        this.users.add(user);
        return user;
    }

}


