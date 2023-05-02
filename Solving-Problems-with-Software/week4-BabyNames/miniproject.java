import java.io.File;
// import java.lang.Math;
import edu.duke.*;
import org.apache.commons.csv.*;

public class miniproject {

    public void totalnames(FileResource fr) {
        int totalnames = 0;
        int boynames = 0;
        int girlnames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalnames += 1;
            if (rec.get(1).equals("M")) {
                boynames += 1;
            } else {
                girlnames += 1;
            }
        }
        System.out.println("total names = " + totalnames);
        System.out.println("Boy names = " + boynames);
        System.out.println("Girl names = " + girlnames);
    }

    public int girlcount(FileResource fr) {
        int girlnames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("F")) {
                girlnames += 1;
            }
        }
        return girlnames;
    }

    public long getRank(int year, String name, String gender) {
        long rank = -1;
        String path = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        int girlnames = girlcount(fr);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
                if (gender.equals("F")) {
                    rank = rec.getRecordNumber();
                } else {
                    rank = rec.getRecordNumber() - girlnames;
                }
            }
        }
        return rank;
    }

    public String getName(int year, long rank, String gender) {
        String name = "NO NAME";
        // String path = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        String path = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        int girlnames = girlcount(fr);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            long rankind = 0;
            if (gender.equals("F")) {
                rankind = rec.getRecordNumber();
            } else {
                rankind = rec.getRecordNumber() - girlnames;
            }

            if (rec.get(1).equals(gender) && rankind == rank) {
                name = rec.get(0);
            }
        }
        return name;
    }

    public long getRank2(FileResource fr, String name, String gender) {
        long rank = 999999;
        int girlnames = girlcount(fr);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
                if (gender.equals("F")) {
                    rank = rec.getRecordNumber();
                } else {
                    rank = rec.getRecordNumber() - girlnames;
                }
            }
        }
        return rank;
    }

    public String yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        long highestRank = 999999;
        String year = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            long currentRank = getRank2(fr, name, gender);
            if (currentRank <= highestRank) {
                highestRank = currentRank;
                year = f.getName();
            }
        }

        if (highestRank != 999999) {
            return year;
        } else {
            return "-1";
        }
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int fileCount = 0;
        int fileNumber = 0;
        double averageRank = 0.0;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            long currentRank = getRank2(fr, name, gender);
            fileCount += 1;
            if (currentRank != 999999) {
                totalRank += currentRank;
                fileNumber += 1;
            }
            if (fileNumber != 0) {
                averageRank = (double) totalRank / fileCount;
            }
        }
        return averageRank;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int result = 0;
        long rank = getRank(year, name, gender);
        String path = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        // String path = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // String gender_temp = rec.get(1);
            if (rec.get(1).equals(gender)) {
                String name_temp = rec.get(0);
                long rank_temp = getRank(year, name_temp, gender);
                if (rank > rank_temp) {
                    result += Integer.parseInt(rec.get(2));
                }
            }
        }
        return result;
    }

    public void testTotalnames() {
        // FileResource fr = new FileResource("example-small.csv");
        FileResource fr = new FileResource();
        totalnames(fr);
    }

    public void testGetRank() {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println(getRank(year, name, gender));
    }

    public void testGetName() {
        int year = 1982;
        long rank = 450;
        String gender = "M";
        System.out.println(getName(year, rank, gender));
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        long oldRank = getRank(year, name, gender);
        String newName = getName(newYear, oldRank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if he/she was born in " + newYear);

    }

    public void testHighestRank() {
        String name = "Mason";
        String gender = "M";
        System.out.println(yearOfHighestRank(name, gender));
    }

    public void testAverageRank() {
        String name = "Jacob";
        String gender = "M";
        System.out.println(getAverageRank(name, gender));
    }

    public void testGetTotalBirthsRankedHigher() {
        int year = 2012;
        String name = "Ethan";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(year, name, gender));
    }

    public static void main(String[] args) {
        miniproject example = new miniproject();
        // example.printnames();
        // example.testTotalnames();
        // example.testGetRank();
        // example.testGetName();
        // example.whatIsNameInYear("Owen", 1974, 2014, "M");
        // example.testHighestRank();
        // example.testAverageRank();
        example.testGetTotalBirthsRankedHigher();
    }
}
