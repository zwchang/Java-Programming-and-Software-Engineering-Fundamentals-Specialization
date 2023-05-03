
/**
 * Write a description of class Tester here.
 * 
 * @author Zhiwei Chang
 * @version 2023-02-14
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        // // analyzer.printAll();
        System.out.println("The number of unique IPs is: " +
                analyzer.countUniqueIPs());
        int num = 400;
        System.out.println(
                "Records that have status code larger than " + num + "are :");
        analyzer.printAllHigherThanNum(num);

        String date = "Sep 24";
        ArrayList<String> result = analyzer.uniqueIPVistsOnDay(date);
        System.out.println("The unique IP visits on day " + date + " is: " + result);
        System.out.println("and the size is: " + result.size());

        int low = 400;
        int high = 499;
        System.out.println("The number of unique IPs between " + low + " " + high + " is: "
                + analyzer.countUniqueIPsInRange(low, high));
    }

    public void testCounts() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        // analyzer.readFile("short-test_log");
        HashMap<String, Integer> counts = analyzer.countVisitsPerIP();
        // System.out.println(counts);
        // System.out.println(analyzer.mostNumberVisitsByIP(counts));
        System.out.println(analyzer.iPsMostVisits(counts));
        // System.out.println(analyzer.iPsForDays());
        System.out.println(analyzer.dayWithMostIPVisits());
        System.out.println(analyzer.iPsWithMostVisitsOnDay("Sep 29"));
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        // test.testLogAnalyzer();
        test.testCounts();
    }
}
