
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Zhiwei Chang 
 * @version 2023-02-14
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry log = WebLogParser.parseEntry(line);
            records.add(log);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVistsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String ipAddr = le.getIpAddress();
            String str = d.toString().substring(4, 10);
            // System.out.println(str);
            if (str.equals(someday) && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxCounts = 0;
        for (String ip : counts.keySet()) {
            int currCount = counts.get(ip);
            if (currCount > maxCounts) {
                maxCounts = currCount;
            }
        }
        return maxCounts;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> list = new ArrayList<String>();
        int maxCounts = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            int currCount = counts.get(ip);
            if (currCount == maxCounts && !list.contains(ip)) {
                list.add(ip);
            }
        }
        return list;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ips = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String str = d.toString().substring(4, 10);
            String ipAddr = le.getIpAddress();

            ArrayList<String> list = new ArrayList<String>();
            if (!ips.containsKey(str)) {
                list.add(ipAddr);
                ips.put(str, list);
            } else {
                ArrayList<String> existList = ips.get(str);
                existList.add(ipAddr);
                ips.put(str, existList);
            }
        }
        return ips;
    }

    public String dayWithMostIPVisits() {
        HashMap<String, ArrayList<String>> ips = iPsForDays();
        int maxCounts = 0;
        String maxKey = "";
        for (String key : ips.keySet()) {
            int currCount = ips.get(key).size();
            if (currCount > maxCounts) {
                maxCounts = currCount;
                maxKey = key;
            }
        }
        return maxKey;
    }

    private HashMap<String, Integer> countVisitsPerIPonDay(String someday) {
        HashMap<String, ArrayList<String>> ips = iPsForDays();
        ArrayList<String> record = ips.get(someday);

        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ipAddr : record) {
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        return counts;
    }

    private int maxCountsOnDay(HashMap<String, Integer> counts) {
        int maxCounts = 0;
        for (String ip : counts.keySet()) {
            int currCount = counts.get(ip);
            if (currCount > maxCounts) {
                maxCounts = currCount;
            }
        }
        return maxCounts;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(String someday) {
        HashMap<String, Integer> counts = countVisitsPerIPonDay(someday);
        int maxCounts = maxCountsOnDay(counts);
        ArrayList<String> list = new ArrayList<String>();
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == maxCounts) {
                list.add(ip);
            }
        }
        return list;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
