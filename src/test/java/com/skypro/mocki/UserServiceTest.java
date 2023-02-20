package com.skypro.mocki;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void checkingTheReturnByNull() {
        when(userRepository.getAllUser()).thenReturn(null);
        assertThat(userService.getAllLogin()).isEqualTo(0);
    }

    @Test
    void addWhenCorrect() {
        when(userRepository.getAllUser()).thenReturn(List.of());
        when(userRepository.addUser(ArgumentMatchers.any())).thenReturn(null);
        userService.addUser("Mitiya", "jlby");
        verify(userRepository).addUser(any());
    }

    @Test
    void exceptionForAddingAUser() {
        assertThatThrownBy(() -> userService.addUser("", "0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Необходимо заполнить данные");
        verify(userRepository, new NoInteractions()).getAllUser();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void exceptionWhenAddingAExistingUser() {
        when(userRepository.getAllUser()).thenReturn(List.of(new User("Gena", "nhtnbq")));
        assertThatThrownBy(() -> userService.addUser("Gena", "nhtnbq"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User Уже существует");
    }

    @Test
    void whenExceptionIsRaisedThenServiceReturnsZero() {
        when(userRepository.getAllUser()).thenThrow(new RuntimeException());
        assertThat(userService.getAllLogin()).isEqualTo(0);
    }


}
