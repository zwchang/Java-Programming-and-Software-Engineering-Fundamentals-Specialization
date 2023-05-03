
// import java.util.*;
import java.util.HashMap;
import java.util.HashSet;

import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMsg = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char c = message.toCharArray()[i];
            slicedMsg.append(c);
        }
        return slicedMsg.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i += 1) {
            String slicedMsg = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slicedMsg);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dic = new HashSet<String>();
        for (String line : fr.lines()) {
            String linelower = line.toLowerCase();
            dic.add(linelower);
        }
        return dic;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        // the correct finalKeylen = 57
        int keylength = 100;
        int largestCount = 0;
        String decrypted = "";
        int finalKeyLen = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i = 1; i < keylength; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String currDecrypted = vc.decrypt(encrypted);
            int currCount = countWords(currDecrypted, dictionary);
            if (currCount > largestCount) {
                largestCount = currCount;
                decrypted = currDecrypted;
                finalKeyLen = key.length;
            }
        }
        System.out.println("key length is: " + finalKeyLen);
        System.out.println("number of valid words is: " + largestCount);
        return decrypted;
    }

    private HashMap<Character, Integer> countChar(HashSet<String> dictionary) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int k = 0; k < word.length(); k++) {
                char ch = Character.toLowerCase(word.charAt(k));
                if (!charMap.containsKey(ch)) {
                    charMap.put(ch, 1);
                } else {
                    charMap.put(ch, charMap.get(ch) + 1);
                }
            }
        }
        return charMap;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostCommon = 'a';
        int maxCounts = 0;
        HashMap<Character, Integer> charMap = countChar(dictionary);
        for (char ch : charMap.keySet()) {
            int currCount = charMap.get(ch);
            if (currCount > maxCounts) {
                maxCounts = currCount;
                mostCommon = ch;
            }
        }
        return mostCommon;
    }

    private HashMap<String, HashSet<String>> mapLanguages() {
        HashMap<String, HashSet<String>> langMap = new HashMap<String, HashSet<String>>();
        String[] langList = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish" };
        for (String item : langList) {
            FileResource dicfile = new FileResource("./dictionaries/" + item);
            HashSet<String> dictionary = readDictionary(dicfile);
            langMap.put(item, dictionary);
        }
        return langMap;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCounts = 0;
        String decrypted = "";
        String languageName = "";
        for (String langName : languages.keySet()) {
            HashSet<String> dic = languages.get(langName);
            String currDecrypted = breakForLanguage(encrypted, dic);
            int currCount = countWords(currDecrypted, dic);
            if (currCount > maxCounts) {
                maxCounts = currCount;
                decrypted = currDecrypted;
                languageName = langName;
            }
        }
        System.out.println("Language used to decrypt the msg is: " + languageName);
        System.out.println("Decrypted msg is: " + decrypted);
    }

    public void breakVigenere() {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // char mostCommon = 'e';
        // int klength = 5;
        // int[] key = tryKeyLength(message, klength, mostCommon);
        // VigenereCipher vc = new VigenereCipher(key);
        // String decrypted = vc.decrypt(message);
        // System.out.print("The decrypted message is: " + decrypted);

        // FileResource fr = new FileResource();
        // String encrypted = fr.asString();
        // FileResource dicfile = new FileResource("./dictionaries/English");
        // HashSet<String> dictionary = readDictionary(dicfile);
        // String decrypted = breakForLanguage(encrypted, dictionary);
        // System.out.print("The decrypted message is: " + decrypted);

        // FileResource dicfile = new FileResource("./dictionaries/German");
        // HashSet<String> dictionary = readDictionary(dicfile);
        // System.out.println("The most common char is: " +
        // mostCommonCharIn(dictionary));

        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = mapLanguages();
        breakForAllLangs(encrypted, languages);
    }

}
