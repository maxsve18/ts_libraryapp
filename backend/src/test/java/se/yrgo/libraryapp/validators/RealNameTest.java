package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;



public class RealNameTest {
    private Set<String> invalidWords;

    @BeforeEach
    void setUp() {
        loadTestInvalidWords();
    }

    void loadTestInvalidWords() {
        invalidWords = new HashSet<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("bad_words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                invalidWords.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.printf("Error reading file %s\n", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource( strings = {"bastard", "skeet", "hoser"})
    void testInvalidWordsLoaded (String name) {
        assertTrue(RealName.validate(name), "Invalid words list should not contain " + name + ".");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Willy Bjork", "Sven Hagman", "David Henriksson" })
    void testValidWords(String name) {
        assertTrue(RealName.validate(name), "Invalid words list should contain " +  name + ".");
    }

}
