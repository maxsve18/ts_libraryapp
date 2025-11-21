package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class UsernameTest {
    @ParameterizedTest
    @ValueSource(strings = {"bosse","lena_larsson@123", "arvid.bengtsson456"})
    void correctUsername(String name) {
        assertThat(Username.validate(name)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"bosse bred sladd", "harald$#nilsson", "linus22 storm!?*" })
    void incorrectUsername(String name) {
        assertThat(Username.validate(name)).isFalse();
    }

    @ParameterizedTest
    @EmptySource
    void InvalidEmptyUsername(String name) {
        assertThat(Username.validate(name)).isFalse();
    }
}
