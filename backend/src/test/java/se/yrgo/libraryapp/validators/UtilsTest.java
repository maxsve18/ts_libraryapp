package se.yrgo.libraryapp.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.assertj.core.api.Assertions.*;

public class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"Testing Testing", "Hello Library", "A good book can be hard to find."})
    void testOnlyLettersAndWhiteSpace(String str) {
        String expected = str.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
        assertThat(Utils.onlyLettersAndWhitespace(str)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "7357IN6, testing",
            "H3110 1I8R4RY, hello library",
            "6R337ING5, greetings"})
    void testCleanAndUnLeet(String str, String expected) {
        String result = Utils.cleanAndUnLeet(str);
        assertThat(result).isEqualTo(expected);
    }

}
