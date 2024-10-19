package tiei.ajp.anagram;

import java.util.*;
import java.math.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class LetterCounterTest {

    private LetterCounter letterCounterA;
    private LetterCounter letterCounterB;
    private LetterCounter letterCounterC;

    @Before
    public void setUp() {
        letterCounterA = new LetterCounter("aaabbbccc");
        letterCounterB = new LetterCounter("aabbbcc");
        letterCounterC = new LetterCounter("abc");
    }

    @Test
    public void subtract_SameCounts_ReturnsEmpty() {
        LetterCounter result = letterCounterA.subtract(letterCounterA);
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void subtract_DifferentCounts_ReturnsRemainingLetters() {
        LetterCounter result = letterCounterA.subtract(letterCounterB);
        assertEquals("Expected remaining letters after subtraction", "cc", result.toString());
    }

    @Test
    public void subtract_NotEnoughLetters_ReturnsNull() {
        LetterCounter result = letterCounterB.subtract(letterCounterA);
        assertNull("Expected null when there are not enough letters to subtract", result);
    }

    @Test
    public void subtract_EmptyCounter_ReturnsOriginal() {
        LetterCounter result = letterCounterA.subtract(new LetterCounter());
        assertEquals("Expected original counter when subtracting empty counter", letterCounterA.toString(), result.toString());
    }

    @Test
    public void subtract_DisjointCounters_ReturnsOriginal() {
        LetterCounter result = letterCounterA.subtract(letterCounterC);
        assertEquals("Expected original counter when subtracting disjoint counter", letterCounterA.toString(), result.toString());
    }
}
