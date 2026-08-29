package numberrangesummarizer;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImplementation();
        
        String sampleInput = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        System.out.println("Input: " + sampleInput);
        
        Collection<Integer> collected = summarizer.collect(sampleInput);
        String result = summarizer.summarizeCollection(collected);
        
        System.out.println("Result: " + result);
    }
}