import edu.duke.*;
// import java.util.*;

public class Tester {

    public void testCaesarCipher() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 10;
        CaesarCipher cc = new CaesarCipher(key);
        String encrpyted = cc.encrypt(message);
        System.out.println("The message encryped with key " + key + " is: ");
        System.out.println(encrpyted);
        System.out.println("The decrypted message with key " + key + " is: ");
        System.out.println(cc.decrypt(encrpyted));
    }

    public void testCaesarCracker() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        char mostCommon = 'a';
        CaesarCracker cc = new CaesarCracker(mostCommon);
        int key = cc.getKey(message);
        String decrypted = cc.decrypt(message);
        System.out.println("The encrypted message is: " + message);
        System.out.println("The message is encrypted with " + key);
        System.out.println("and the decrypted message is: ");
        System.out.println(decrypted);
    }

    public void testVigenereCipher() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = { 17, 14, 12, 4 };
        VigenereCipher vc = new VigenereCipher(key);
        String encrypted = vc.encrypt(message);
        System.out.println("The encrypted message is: " + encrypted);
    }

    public void testVigenereBreaker() {

        // String message = "abcdefghijklm";
        // int whichSlice = 2;
        // int totalSlices = 4;
        // VigenereBreaker vb = new VigenereBreaker();
        // String slicedMsg = vb.sliceString(message, whichSlice, totalSlices);
        // System.out.println(slicedMsg);

        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // VigenereBreaker vb = new VigenereBreaker();
        // int klength = 4;
        // char mostCommon = 'e';
        // int[] key = vb.tryKeyLength(message, klength, mostCommon);
        // System.out.println(Arrays.toString(key));

        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        // test.testCaesarCipher();
        // test.testCaesarCracker();
        // test.testVigenereCipher();
        test.testVigenereBreaker();
    }

}
