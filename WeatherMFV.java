import java.util.ArrayList;
import java.util.List;

public class Study {
	private static int RAINFALL_POS = 0;
	private static int HUMIDITY_POS = 1;
	private static int END_OF_DATA = -999;
	
	public static double[] main(List<List<Integer>> originalData) {
		List<List<Integer>> dataUpToEnd = copyOnlyToEndOfData(originalData);
		List<List<Integer>> validData = getValidData(dataUpToEnd);
		
		double averageRainfall = averageData(validData, RAINFALL_POS);
		double averageHumidity = averageData(validData, HUMIDITY_POS);
		double rainyDays = countPositive(validData, RAINFALL_POS);
	    
		double[] result = { averageRainfall, averageHumidity, rainyDays };
		return result;
	}
	
	private static List<List<Integer>> copyOnlyToEndOfData(List<List<Integer>> originalData) {
		List<List<Integer>> dataUpToEnd = new ArrayList<List<Integer>>();
		for (List<Integer> dailyData: originalData) {
			if (dailyData.get(RAINFALL_POS) != END_OF_DATA && 
                dailyData.get(HUMIDITY_POS) != END_OF_DATA) {
				dataUpToEnd.add(dailyData);
			} else {
				break;
			}
		}
		return dataUpToEnd;
	}

	private static List<List<Integer>> getValidData(List<List<Integer>> data) {
		List<List<Integer>> validData = new ArrayList<List<Integer>>();
		for (List<Integer> dailyData: data) {
			if (dailyData.get(RAINFALL_POS) >= 0 && dailyData.get(HUMIDITY_POS) >= 0) {
				validData.add(dailyData);
			}
		}
		return validData;
	}
	
    private static double averageData(List<List<Integer>> data, int componentToAverage) {
		double average = 0.0;
		if (data.size() != 0) {
			double sum = 0;
			for (List<Integer> dailyData: data) {
				sum = sum + dailyData.get(componentToAverage);
			}
		    average = sum / data.size();
		}
		return average;
    }
    
	private static double countPositive(List<List<Integer>> data, int componentToCount) {
		int count = 0;
		for (List<Integer> dailyData: data) {
			if (dailyData.get(componentToCount) > 0) {
				count = count + 1;
			}
		}
		return count;
	}
}
