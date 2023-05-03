public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        int idx = vowels.indexOf(Character.toLowerCase(ch));
        if (idx != -1) {
            return true;
        }
        return false;
    }

    public void testIsVowel(char ch) {
        System.out.println(isVowel(ch));
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder replacedStrings = new StringBuilder(phrase);
        for (int i = 0; i < replacedStrings.length(); i++) {
            char currChar = replacedStrings.charAt(i);
            if (isVowel(currChar)) {
                replacedStrings.setCharAt(i, ch);
            }
        }
        return replacedStrings.toString();
    }

    public void testReplaceVowels(String phrase, char ch) {
        System.out.println(replaceVowels(phrase, ch));
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder replacedStrings = new StringBuilder(phrase);
        for (int i = 0; i < replacedStrings.length(); i++) {
            char currChar_lower = Character.toLowerCase(replacedStrings.charAt(i));
            char ch_lower = Character.toLowerCase(ch);
            if (currChar_lower == ch_lower && i % 2 == 0) {
                replacedStrings.setCharAt(i, '*');
            }
            if (currChar_lower == ch_lower && i % 2 == 1) {
                replacedStrings.setCharAt(i, '+');
            }
        }
        return replacedStrings.toString();
    }

    public void testEmphasize(String phrase, char ch) {
        System.out.println(emphasize(phrase, ch));
    }

    public static void main(String[] args) {
        WordPlay example = new WordPlay();
        example.testIsVowel('f');
        example.testReplaceVowels("Hello World!", '*');
        example.testEmphasize("dna ctgaaactga", 'a');
        example.testEmphasize("Mary Bella Abracadabra", 'a');
    }
}
