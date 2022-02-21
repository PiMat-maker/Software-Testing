package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    private Race race;
    private List<Question> questions;

    @BeforeEach
    public void setup() {
        race = new Race("Big size creatures", true, 1500000, "Brokianskiy ultra-cricket");
        Question question1 = new Question("Food price");
        Question question2 = new Question("Life sense");
        Question question3 = new Question("Global warming");
        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
    }

    @Test
    public void checkRaceType() {
        assertEquals("Big size creatures", race.getType());
    }

    @Test
    public void checkRaceLooksLikeHuman() {
        assertTrue(race.isLooksLikeHuman());
    }

    @Test
    public void checkRaceBigSize() {
        assertTrue(race.getSize() > 1000000);
    }

    @Test
    public void checkRaceFavouriteGame() {
        assertEquals("Brokianskiy ultra-cricket", race.getFavouriteGame());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    public void checkRaceFalseExhausted(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        assertFalse(race.isExhausted());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void checkRaceTrueExhausted(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        assertTrue(race.isExhausted());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    public void checkRaceFalseSitDown(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        race.sitDown();
        assertFalse(race.isSitDown());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void checkRaceTrueSitDown(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        race.sitDown();
        assertTrue(race.isSitDown());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    public void checkRaceFalseSolveProblems(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        race.sitDown();
        race.solveProblems(questions);
        for (Question question : questions) {
            assertFalse(question.isSolved());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void checkRaceTrueSolveProblems(int indexOfQuestion) {
        race.gotExhausted(questions.get(indexOfQuestion));
        race.sitDown();
        race.solveProblems(questions);
        for (Question question : questions) {
            assertTrue(question.isSolved());
        }
    }
}
