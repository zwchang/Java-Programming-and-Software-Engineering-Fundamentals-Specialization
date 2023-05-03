public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (i % 2 == 0) {
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet1.charAt(idx);
                        sb.setCharAt(i, newChar);
                    }
                }
                if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        char newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        sb.setCharAt(i, newChar);
                    }
                }

            }

            if (i % 2 == 1) {
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        char newChar = shiftedAlphabet2.charAt(idx);
                        sb.setCharAt(i, newChar);
                    }
                }
                if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        char newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                        sb.setCharAt(i, newChar);
                    }
                }

            }

        }

        return sb.toString();

    }

    public String decrypt(String input) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cct.encrypt(input);
        return decrypted;
    }

    // private int[] countLetters(String message) {
    // String alpha = "abcdefghijklmnopqrstuvwxyz";
    // int[] counts = new int[26];
    // for (int k = 0; k < message.length(); k++) {
    // char ch = Character.toLowerCase(message.charAt(k));
    // int dex = alpha.indexOf(ch);
    // if (dex != -1) {
    // counts[dex] += 1;
    // }
    // }
    // return counts;
    // }

    // private int maxIndex(int[] vals) {
    // int maxDex = 0;
    // for (int k = 0; k < vals.length; k++) {
    // if (vals[k] > vals[maxDex]) {
    // maxDex = k;
    // }
    // }
    // return maxDex;
    // }

    // private String halfOfString(String message, int start) {
    // StringBuilder halfString = new StringBuilder();
    // for (int i = start; i < message.length(); i = i + 2) {
    // char currChar = message.charAt(i);
    // halfString.append(currChar);
    // }
    // return halfString.toString();
    // }

    // private int getKey(String s) {
    // int[] counts = countLetters(s);
    // int maxDex = maxIndex(counts);
    // int dkey = maxDex - 4;
    // if (maxDex < 4) {
    // dkey = 26 - (4 - maxDex);
    // }
    // return dkey;
    // }

    // private int key1;
    // private int key2;

    // public String decrypt(String input) {
    // String first = halfOfString(input, 0);
    // String second = halfOfString(input, 1);
    // int firstKey = getKey(first);
    // int secondKey = getKey(second);
    // System.out.println("The first key is: " + firstKey + "\t" + "The second key
    // is: " + secondKey);
    // CaesarCipherTwo cipher = new CaesarCipherTwo(key1, key2);
    // String decrypted = cipher.encrypt(input);
    // return decrypted;
    // }

}
