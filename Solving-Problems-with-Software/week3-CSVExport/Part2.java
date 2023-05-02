import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }

        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp > -50) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out
                .println("coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        String filename = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                filename = f.getAbsolutePath();
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp && currentTemp > -50) {
                    smallestSoFar = currentRow;
                    filename = f.getAbsolutePath();
                }
            }
        }
        return filename;
    }

    public void testFileWithColdestTemperature() {
        String filePathWithColdestTemp = fileWithColdestTemperature();
        File f = new File(filePathWithColdestTemp);
        String filename = f.getName();
        System.out.println("File with coldest temperatureure was " + filename);

        FileResource fr = new FileResource(filePathWithColdestTemp);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
    }

    public CSVRecord getLowestHumidity(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            int currentHumidity;
            int lowestHumidity;
            if (!currentRow.get("Humidity").equals("N/A") && !lowestSoFar.get("Humidity").equals("N/A")) {
                currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));

                if (currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentRow : parser) {
            // if (lowest == null) {
            // lowest = currentRow;
            // } else {
            // if (currentRow.get("Humidity") != "N/A" && lowest.get("Humidity") != "N/A") {
            // double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            // double lowestHumidity = Double.parseDouble(lowest.get("Humidity"));
            // if (currentHumidity < lowestHumidity) {
            // lowest = currentRow;
            // }
            // }
            // }
            lowest = getLowestHumidity(currentRow, lowest);
        }

        return lowest;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out
                .println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestHumidity(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0.0;
        int recordNum = 0;

        for (CSVRecord currentRow : parser) {
            recordNum += 1;
            totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
        }

        double averageTemp = totalTemp / recordNum;
        return averageTemp;
    }

    public void testaverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0.0;
        int recordNum = 0;
        for (CSVRecord currentRow : parser) {
            int currenthum = Integer.parseInt(currentRow.get("Humidity"));
            if (currenthum >= value) {
                recordNum += 1;
                totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
            }
        }
        double averageTemp = 0.0;
        if (recordNum != 0) {
            averageTemp = totalTemp / recordNum;
        }
        return averageTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        if (averageTemp != 0.0) {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }

    public static void main(String[] args) {
        Part2 example = new Part2();
        // example.testColdestHourInFile();
        // example.testFileWithColdestTemperature();
        // example.testLowestHumidityInFile();
        // example.testLowestHumidityInManyFiles();
        // example.testaverageTemperatureInFile();
        example.testAverageTemperatureWithHighHumidityInFile();
    }

}
