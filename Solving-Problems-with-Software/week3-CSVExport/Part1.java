import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {

    public String countryInfo(CSVParser parser, String countryofInterest) {

        for (CSVRecord record : parser) {
            String country = record.get("Country");
            if (country.equals(countryofInterest)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                return countryofInterest + ": " + export + ": " + value;
            }
        }
        return "NOT FOUND!";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                // System.out.println(export);
                count += 1;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String country = countryInfo(parser, "Nauru");
        // System.out.println(country);
        // listExportersTwoProducts(parser, "cotton", "flowers");
        // int number = numberOfExporters(parser, "cocoa");
        // System.out.println(number);
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        Part1 example = new Part1();
        // example.readRecord();
        example.tester();
    }

}
