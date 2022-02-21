package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoryTest {

    private Story story;

    @BeforeEach
    public void setup(){
        story = new Story(-2000000);
    }

    @Test
    public void isMillionAges(){
        assertTrue(story.getAge() / 1000000 != 0 && story.getAge() < 0);
    }
}
