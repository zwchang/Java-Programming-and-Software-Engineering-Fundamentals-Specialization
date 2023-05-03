import edu.duke.*;

public class CaesarBreaker {

    public int[] countLetters(String message) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
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

    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    void testDecrypt() {
        FileResource fr = new FileResource();
        String messsage = fr.asString();
        String decrypted = decrypt(messsage);
        System.out.println("decrypted message is " + "\n" + decrypted);
    }

    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i = i + 2) {
            char currChar = message.charAt(i);
            halfString.append(currChar);
        }
        return halfString.toString();
    }

    void testHalfOfString() {
        String message = "Qbkm Zgis";
        int start = 1;
        System.out.println(halfOfString(message, start));
    }

    public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String decryptTwoKeys(String encrypted) {
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        // System.out.println("The first half string is: " + first);
        // System.out.println("The second half string is: " + second);
        int firstKey = getKey(first);
        int secondKey = getKey(second);
        System.out.println("The first key is: " + firstKey + "\t" + "The second key is: " + secondKey);
        CaesarCipher cipher = new CaesarCipher();
        String decrypted = cipher.encryptTwoKeys(encrypted, 26 - firstKey, 26 - secondKey);
        return decrypted;
    }

    void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("decrypted message is " + "\n" + decrypted);
    }

    public static void main(String[] args) {
        CaesarBreaker CB = new CaesarBreaker();
        // CB.testDecrypt();
        // CB.testHalfOfString();
        CB.testDecryptTwoKeys();
    }

}
