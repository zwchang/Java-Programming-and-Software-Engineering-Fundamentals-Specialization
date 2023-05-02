import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExample {
    public void readRecord() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("Country") + " ");
            System.out.println(record.get("Exports") + " ");
            System.out.println(record.get("Value (dollars)"));
        }
    }

    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }

    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public static void main(String[] args) {
        CSVExample example = new CSVExample();
        // example.readRecord();
        example.whoExportsCoffee();
    }
}
