package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceIndividualTest {

    private RaceIndividual individual1;
    private RaceIndividual individual2;
    private RaceIndividual individual3;
    private List<Question> questions;

    @BeforeEach
    public void setup(){
        individual1 = new RaceIndividual("Big size creatures", true, 100,
                "Brokianskiy ultra-cricket", "Luke");
        individual2 = new RaceIndividual("Big size creatures", true, 100,
                "Brokianskiy ultra-cricket", "Ben");
        individual3 = new RaceIndividual("Big size creatures", true, 100,
                "Brokianskiy ultra-cricket", "Mark");
        Question question1 = new Question("Food price");
        Question question2 = new Question("Life sense");
        Question question3 = new Question("Global warming");
        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
    }

    @Test
    public void checkFalseDistractionWrongProblem(){
        Argue argue = individual1.startArgue(individual2, questions.get(0));
        argue.distractRaceIndividual();
        assertAll(() -> assertFalse(individual1.isDistracted()),
                () -> assertFalse(individual2.isDistracted()));
    }

    @Test
    public void checkFalseDistractionRightSolvedProblem(){
        questions.get(1).setSolved(true);
        Argue argue = individual1.startArgue(individual2, questions.get(1));
        argue.distractRaceIndividual();
        assertAll(() -> assertFalse(individual1.isDistracted()),
                () -> assertFalse(individual2.isDistracted()));
    }

    @Test
    public void checkTrueDistration(){
        Argue argue = individual1.startArgue(individual2, questions.get(1));
        argue.distractRaceIndividual();
        assertAll(() -> assertTrue(individual1.isDistracted()),
                () -> assertTrue(individual2.isDistracted()));
    }

    @Test
    public void checkUnsucceedHitBothIndividualsDistracted(){
        Argue argue = individual1.startArgue(individual2, questions.get(1));
        argue.distractRaceIndividual();
        int hitCounter = individual1.getNumberOfSucceedHits();
        int runCounter = individual1.getNumberOfSucceedRuns();
        individual1.hitIndividual(individual2);
        assertAll(() -> assertEquals(hitCounter, individual1.getNumberOfSucceedHits()),
                () -> assertEquals(runCounter, individual1.getNumberOfSucceedRuns()));
    }

    @Test
    public void checkUnsucceedHitHittingIndividualNotDistracted(){
        int hitCounter = individual1.getNumberOfSucceedHits();
        int runCounter = individual1.getNumberOfSucceedRuns();
        individual1.hitIndividual(individual3);
        assertAll(() -> assertEquals(hitCounter, individual1.getNumberOfSucceedHits()),
                () -> assertEquals(runCounter, individual1.getNumberOfSucceedRuns()));
    }

    @Test
    public void checkSucceedHit(){
        questions.get(1).setSolved(false);
        Argue argue = individual1.startArgue(individual2, questions.get(1));
        argue.distractRaceIndividual();
        int hitCounter = individual3.getNumberOfSucceedHits();
        int runCounter = individual3.getNumberOfSucceedRuns();
        individual3.hitIndividual(individual2);
        assertAll(() -> assertEquals(hitCounter + 1, individual3.getNumberOfSucceedHits()),
                () -> assertEquals(runCounter + 1, individual3.getNumberOfSucceedRuns()));
    }
}
