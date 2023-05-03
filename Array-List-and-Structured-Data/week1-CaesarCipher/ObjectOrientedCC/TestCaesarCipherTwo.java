import edu.duke.*;

public class TestCaesarCipherTwo {

    private String alpha;

    public TestCaesarCipherTwo() {
        alpha = "abcdefghijklmnopqrstuvwxyz";
    }

    private int[] countLetters(String message) {
        // String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alpha.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    private int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    private String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i = i + 2) {
            char currChar = message.charAt(i);
            halfString.append(currChar);
        }
        return halfString.toString();
    }

    private int getKey(String s) {
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String breakCaesarCipher(String input) {
        String first = halfOfString(input, 0);
        String second = halfOfString(input, 1);
        int firstKey = getKey(first);
        int secondKey = getKey(second);
        System.out.println("The first key is: " + firstKey + "\t" + "The second key is: " + secondKey);
        CaesarCipherTwo cipher = new CaesarCipherTwo(26 - firstKey, 26 - secondKey);
        String decrypted = cipher.encrypt(input);
        return decrypted;
    }

    private void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        String encrypted = cct.encrypt(message);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("encrypted message is " + "\n" + encrypted);
        System.out.println("decrypted message is " + "\n" + decrypted);

        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        String breakCipher = tcct.breakCaesarCipher(encrypted);
        System.out.println("decrypted message is " + "\n" + breakCipher);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        tcct.simpleTests();
    }
}
