package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;

public class NumberRangeSummarizerImplementationTest {

    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizerImplementation();

    @Test
    public void testStandardCollection() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        assertEquals(expected, summarizer.collect(input));
    }

    @Test
    public void testStandardSummarization() {
        Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, summarizer.summarizeCollection(input));
    }
    
    @Test
    public void testSingleNumber() {
        String input = "5";
        Collection<Integer> collected = summarizer.collect(input);
        assertEquals("5", summarizer.summarizeCollection(collected));
    }

    @Test
    public void testAllSequential() {
        String input = "1, 2, 3, 4, 5";
        Collection<Integer> collected = summarizer.collect(input);
        assertEquals("1-5", summarizer.summarizeCollection(collected));
    }

    @Test
    public void testNoSequential() {
        String input = "1, 3, 5, 7, 9";
        Collection<Integer> collected = summarizer.collect(input);
        assertEquals("1, 3, 5, 7, 9", summarizer.summarizeCollection(collected));
    }

    @Test
    public void testExtremeWhitespace() {
        String input = "  1,2 , 3,   4  ,5  ,  8, 9 ";
        Collection<Integer> collected = summarizer.collect(input);
        assertEquals("1-5, 8-9", summarizer.summarizeCollection(collected));
    }

    @Test
    public void testNegativeNumbers() {
        String input = "-5, -4, -3, 0, 1, 2";
        Collection<Integer> collected = summarizer.collect(input);
        // Note: The output format for negative ranges will be "-5--3" 
        assertEquals("-5--3, 0-2", summarizer.summarizeCollection(collected));
    }
    @Test
    public void testRobustnessEmptyAndNull() {
        assertTrue(summarizer.collect("").isEmpty());
        assertTrue(summarizer.collect(null).isEmpty());
        assertEquals("", summarizer.summarizeCollection(Arrays.asList()));
        assertEquals("", summarizer.summarizeCollection(null));
    }

    @Test
    public void testRobustnessUnorderedAndDuplicates() {
        String input = "3,1,1,6,8,7";
        Collection<Integer> collected = summarizer.collect(input);
        assertEquals(Arrays.asList(1, 3, 6, 7, 8), collected);
        assertEquals("1, 3, 6-8", summarizer.summarizeCollection(collected));
    }
}