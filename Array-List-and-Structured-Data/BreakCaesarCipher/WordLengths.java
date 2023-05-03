import edu.duke.*;

public class WordLengths {

    public int countLength(String word) {
        int length = 0;
        for (int k = 0; k < word.length(); k++) {
            if ((k == 0 && !Character.isLetter(word.charAt(k)))
                    || (k == (word.length() - 1) && !Character.isLetter(word.charAt(k)))) {
                continue;
            } else {
                length += 1;
            }
        }
        return length;
    }

    // void testCountLength() {
    // System.out.println(countLength("are"));
    // }

    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int length = countLength(word);
            counts[length] += 1;
        }
        for (int k = 1; k < counts.length; k++) {
            System.out.println(k + "\t" + counts[k]);
        }
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
