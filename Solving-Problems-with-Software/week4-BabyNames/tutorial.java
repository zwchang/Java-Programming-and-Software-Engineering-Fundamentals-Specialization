import edu.duke.*;
import org.apache.commons.csv.*;

public class tutorial {
    public void printnames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int boyBirths = 0;
        int girlBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;

            if (rec.get(1).equals("M")) {
                boyBirths += numBorn;
            } else {
                girlBirths += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("Boy births = " + boyBirths);
        System.out.println("Girl births = " + girlBirths);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("example-small.csv");
        // FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public static void main(String[] args) {
        tutorial example = new tutorial();
        example.printnames();
        // example.testTotalBirths();
    }
}