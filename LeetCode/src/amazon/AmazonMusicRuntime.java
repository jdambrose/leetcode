package amazon;

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

public class AmazonMusicRuntime {

	/*
	 * Complete the 'findSongs' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * following parameters: 1. INTEGER rideDuration in seconds 2. INTEGER_ARRAY
	 * songDurations in seconds
	 */

	public static List<Integer> findSongs(int rideDuration, List<Integer> songDurations) {

		// Default selected songs contains could not find solution [-1, -1]
		List<Integer> selectedSongs = new ArrayList<>();
		selectedSongs.add(0, -1);
		selectedSongs.add(1, -1);

		int idealSongDuration = rideDuration - 30;
		
		// Used to decide beteween multiple solutions, solution with the longest song
		int longestSongTimeOfFoundSolutions = 0;
		
		for(int song : songDurations) {
			
			if(song > idealSongDuration) {
				// Not a possible solution
				continue;
			} else if(selectedSongs.contains(song)) {
				// Would be same as current solution with larger indexes.
				// End here to reduce 
				continue;
			}else{
				
				int timeLeft = idealSongDuration - song;
				
				// Found a possible solution
				if(songDurations.contains(timeLeft)) {
					
					// TODO verify indexOf returns the song with the lowest index
					int song1Index = songDurations.indexOf(song);
					int song2Index = songDurations.indexOf(timeLeft);
					
					if(timeLeft == song) {
						
						// Case where there is only one in the list
						if(songDurations.lastIndexOf(timeLeft) == song1Index) {
							continue;
						}else {
							
							song2Index = getNextIndexOf(songDurations, song1Index);
							
						}
						
					}
					
					if(song > longestSongTimeOfFoundSolutions) {
						longestSongTimeOfFoundSolutions = song;
						selectedSongs.set(0, song1Index);
						selectedSongs.set(1, song2Index);
					}
					
					if(timeLeft > longestSongTimeOfFoundSolutions) {
						longestSongTimeOfFoundSolutions = timeLeft;
						selectedSongs.set(0, song1Index);
						selectedSongs.set(1, song2Index);
					}
					
				}
				
				
			}
			
			
		}

		return selectedSongs;

	}
	
	private static int getNextIndexOf(List<Integer> list, int startIndex) {
		
		int findMe = list.get(startIndex);
		for(int i = startIndex + 1; i < list.size(); i++) {
			
			if(list.get(i) == findMe) {
				return i;
			}
			
		}
		
		return -1;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int rideDuration = Integer.parseInt(bufferedReader.readLine().trim());

		int songDurationsCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> songDurations = IntStream.range(0, songDurationsCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		List<Integer> result = AmazonMusicRuntime.findSongs(rideDuration, songDurations);

		System.out.println(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
	}

}
