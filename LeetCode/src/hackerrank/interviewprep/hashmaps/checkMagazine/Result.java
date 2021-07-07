package hackerrank.interviewprep.hashmaps.checkMagazine;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    public static void checkMagazine(List<String> magazine, List<String> note) {
    	
    	Map<Integer, Integer> dictionary = new HashMap<>();
    	for(String s : magazine) {
    		if(dictionary.containsKey(s.hashCode())) {
    			int count = dictionary.get(s.hashCode());
    			dictionary.put(s.hashCode(), count + 1);
    		}else {
    			dictionary.put(s.hashCode(), 1);
    		}
    		
    	}
    	
    	if(containsNote(dictionary, note)) {
    		System.out.print("Yes");
    	}else {
    		System.out.print("No");
    	}

    }
    
    private static boolean containsNote(Map<Integer, Integer> dictionary, List<String> note) {
    	
    	for(String s : note) {
    		if(!dictionary.containsKey(s.hashCode())) {
    			return false;
    		}else {
    			int count = dictionary.get(s.hashCode());
    			if(count < 1) {
    				return false;
    			}else {
    				dictionary.put(s.hashCode(), count -1);
    			}
    			
    		}
    	}
    	
    	return true;
    	
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}
