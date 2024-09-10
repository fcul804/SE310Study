import java.util.ArrayList;
import java.util.List;

public class Study {
	private static int RAINFALL_POS = 0;
	private static int HUMIDITY_POS = 1;
	private static int END_OF_DATA = -999;
	
	public static double[] main(List<List<Integer>> originalData) {
		// Copy all the data up to the end of the data
		List<List<Integer>> dataUpToEnd = new ArrayList<List<Integer>>();
		for (List<Integer> dailyData: originalData) {
			if (dailyData.get(RAINFALL_POS) != END_OF_DATA && 
                dailyData.get(HUMIDITY_POS) != END_OF_DATA) {
				dataUpToEnd.add(dailyData);
			} else {
				break;
			}
		}
		
		// Remove all invalid values
		List<List<Integer>> validData = new ArrayList<List<Integer>>();
		for (List<Integer> dailyData: dataUpToEnd) {
			if (dailyData.get(RAINFALL_POS) >= 0 && 
                dailyData.get(HUMIDITY_POS) >= 0) {
				validData.add(dailyData);
			}
		}
		
		// Determine average rainfall
		double averageRainfall = 0.0;
		if (validData.size() != 0) {
			double sumRain = 0;
			for (List<Integer> dailyData: validData) {
				sumRain = sumRain + dailyData.get(RAINFALL_POS);
			}
			averageRainfall = sumRain / validData.size();
		}
		
		// Determine average humidity
		double averageHumidity = 0.0;
		if (validData.size() != 0) {
			double sumHumidity = 0;
			for (List<Integer> dailyData: validData) {
				sumHumidity = sumHumidity + dailyData.get(HUMIDITY_POS);
			}
			averageHumidity = sumHumidity / validData.size();
		}
		
		// Determine number of rainy days
		int rainyDays = 0;
		for (List<Integer> dailyData: validData) {
			if (dailyData.get(RAINFALL_POS) > 0) {
				rainyDays = rainyDays + 1;
			}
		}
		
		double[] result = { averageRainfall, averageHumidity, rainyDays };
		return result;
	}
}
