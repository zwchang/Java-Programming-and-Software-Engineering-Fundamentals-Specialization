import edu.duke.*;

public class testCaesarCipher {

    private String alpha;

    public testCaesarCipher() {
        alpha = "abcdefghijklmnopqrstuvwxyz";
    }

    public int[] countLetters(String message) {
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

    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println("decrypted key is " + "\n" + dkey);
        CaesarCipherOO cc = new CaesarCipherOO(26 - dkey);
        return cc.encrypt(input);
    }

    private void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherOO cc = new CaesarCipherOO(15);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("encrypted message is " + "\n" + encrypted);
        System.out.println("decrypted message is " + "\n" + decrypted);

        testCaesarCipher tCC = new testCaesarCipher();
        String breakCipher = tCC.breakCaesarCipher(encrypted);
        System.out.println("decrypted message is " + "\n" + breakCipher);
    }

    public static void main(String[] args) {
        testCaesarCipher tCC = new testCaesarCipher();
        tCC.simpleTests();
    }
}
