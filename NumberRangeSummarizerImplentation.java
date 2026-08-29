package numberrangesummarizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Werner
 *
 * Implement this Interface to produce a comma delimited list of numbers,
 * grouping the numbers into a range when they are sequential.
 *
 *
 * Sample Input: "1,3,6,7,8,12,13,14,15,21,22,23,24,31
 * Result: "1, 3, 6-8, 12-15, 21-24, 31"
 *
 * The code will be evaluated on
 *   - functionality
 *   - style
 *   - robustness
 *   - best practices
 *   - unit tests
 */

public class NumberRangeSummarizerImplentation implements NumberRangeSummarizer{

    public Collection<Integer> collect(String input) {
        if(input == null || input.trim().isEmpty()){
            return Collections.emptyList();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public String summarizeCollection(Collection<Integer> input){
        if (input == null || input.isEmpty()){
            return "";
        }
        List<Integer> nums = new ArrayList<>(input);
        List<String> ranges = new ArrayList<>();

        int start = nums.get(0);
        int prev = start;

        for (int i=1; i < nums.size(); i++){
            int n = nums.get(i);
            
            if (n!= prev+1){
                if(start==prev){
                    ranges.add(String.valueOf(start));
                }
                else{
                    ranges.add(start+"-"+prev);
                }
                start = n;
            }
            prev = n;
        }

        if (start == prev){
            ranges.adfd(String.valueOf(start));
        }
        else{
            ranges.add(start + "-" + prev);
        }

    return String.join(", ", ranges);

    }
}