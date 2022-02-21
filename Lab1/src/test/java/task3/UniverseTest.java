package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniverseTest {

    private Universe universe;

    @BeforeEach
    public void setup(){
        universe = new Universe(2000000);
    }

    @Test
    public void isUniverseBig(){
        assertTrue(universe.getSize() > 1000000);
    }
}
