// import edu.duke.*;
// import java.io.*;

public class Part3 {
    public Boolean twoOccurrences(String a, String b) {
        int startIndex1 = b.indexOf(a);
        if (startIndex1 != -1) {
            int startIndex2 = b.indexOf(a, startIndex1 + 1);
            if (startIndex2 != -1) {
                return true;
            }
        }
        return false;
        // int startIndex1 = b.indexOf(a);
        // int startIndex2 = b.indexOf(a, startIndex1 + a.length());
        // if (startIndex1 == -1 || startIndex2 == -1) {
        // return false;
        // } else {
        // return true;
        // }
    }

    public String lastPart(String a, String b) {
        int startIndex = b.indexOf(a);
        if (startIndex == -1) {
            return b;
        } else {
            return b.substring(startIndex + a.length(), b.length());
        }
    }

    public void testing() {
        String a1 = "by";
        String b1 = "A story by Abby Long";
        Boolean result1 = twoOccurrences(a1, b1);
        String a2 = "an";
        String b2 = "banana";
        String result2 = lastPart(a2, b2);
        System.out.println("The test result is " + result1);
        System.out.println("The part of string after " + a2 + " in " + b2 + " is " + result2);
    }

    public static void main(String[] args) {
        Part3 str = new Part3();
        str.testing();
    }
}
