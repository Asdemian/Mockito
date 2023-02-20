package com.skypro.mocki;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository;
    User russia = new User("Mitiya", "jlby");
    User greek = new User("Agafangel", "dnjhjq");
    User german = new User("Gena", "nhtnbq");
    User spaniard = new User("Hose", "xtndthnsq");

    @BeforeEach
    public void сreation() {
        userRepository = new UserRepository();
    }

    @Test
    public void getNullUserLis() {
        Collection<User> expected = userRepository.getAllUser();
        Assertions.assertThat(expected).hasSize(0);
    }

    @Test
    public void getAllUsersList() {
        userRepository.addUser(russia);
        userRepository.addUser(greek);
        userRepository.addUser(german);
        userRepository.addUser(spaniard);
        Collection<User> expected = userRepository.getAllUser();
        Collection<User> actual = new ArrayList<>();
        actual.add(russia);
        actual.add(greek);
        actual.add(german);
        actual.add(spaniard);
        assertEquals(expected, actual);
    }

    @Test
    public void testUserLogin() {
        userRepository.addUser(russia);
        Optional<User> russia = userRepository.findUserByLogin("Mitiya");
        assertTrue(russia.isPresent());
    }

    @Test
    public void testNonUserLogin() {
        userRepository.addUser(greek);
        Optional<User> greek = userRepository.findUserByLogin("Gena");
        assertFalse(greek.isPresent());
    }

    @Test
    public void testUserLoginAndPassword() {
        userRepository.addUser(german);
        Optional<User> german = userRepository.findUserByLoginAndPassword("Gena", "nhtnbq");
        assertTrue(german.isPresent());
    }

    @Test
    public void testEqualsPasswordDifferentLogin() {
        userRepository.addUser(spaniard);
        Optional<User> spaniard = userRepository.findUserByLoginAndPassword("Agafangel", "nhtnbq");
        assertTrue(spaniard.isPresent());
    }

    @Test
    public void testEqualsLoginDifferentPassword() {
        userRepository.addUser(russia);
        Optional<User> russia = userRepository.findUserByLoginAndPassword("Mitiya", "dnjhjq");
        assertTrue(russia.isPresent());
    }

}
