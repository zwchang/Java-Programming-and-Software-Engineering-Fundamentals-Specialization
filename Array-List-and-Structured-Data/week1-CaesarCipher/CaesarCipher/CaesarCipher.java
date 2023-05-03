import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        // Make a StringBuilder with message
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            if (Character.isLowerCase(currChar)) {
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx != -1) {
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public void testCaesar() {
        // int key = 15;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        int key = 23;
        String message = "First Legion";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key);
        System.out.println("encrypted message is " + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println("decrypted message is " + "\n" + decrypted);
    }

    // public StringBuilder replaceChar(String shiftedAlphabet, int i, int idx, char
    // ch) {
    // if (Character.isUpperCase(ch)) {
    // // int idx = alphabet.indexOf(ch);
    // if (idx != -1) {
    // char newChar = shiftedAlphabet.charAt(idx);
    // encrypted.setCharAt(i, newChar);
    // }
    // }
    // if (Character.isLowerCase(ch)) {
    // int idx = alphabet.indexOf(Character.toUpperCase(ch));
    // if (idx != -1) {
    // char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
    // encrypted.setCharAt(i, newChar);
    // }
    // }
    // }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0) {
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        char newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }

            }

            if (i % 2 == 1) {
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        char newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }

            }

        }

        return encrypted.toString();
    }

    public void testEncryptTwokeys(int key1, int key2, String input) {
        System.out.println(encryptTwoKeys(input, key1, key2));

    }

    public static void main(String[] args) {
        CaesarCipher Cipher = new CaesarCipher();
        Cipher.testCaesar();
        Cipher.testEncryptTwokeys(8, 21,
                "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        Cipher.testEncryptTwokeys(26 - 2, 26 - 20, "Top ncmy qkff vi vguv vbg ycpx");
    }
}
