import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.*;

public class WordInFiles {

    private HashMap<String, ArrayList<String>> wordToFiles;

    public WordInFiles() {
        wordToFiles = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for (String w : fr.words()) {
            if (!wordToFiles.containsKey(w)) {
                ArrayList<String> newlist = new ArrayList<String>();
                newlist.add(fileName);
                wordToFiles.put(w, newlist);
            } else if (wordToFiles.containsKey(w) &&
                    !wordToFiles.get(w).contains(fileName)) {
                ArrayList<String> namelist = wordToFiles.get(w);
                namelist.add(fileName);
                wordToFiles.put(w, namelist);
            }
        }
    }

    public void buildWordFileMap() {
        wordToFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    void tester() {
        buildWordFileMap();
        // System.out.println(wordToFiles);
        System.out.println("max number is: " + maxNumber());
        int number = 4;
        // System.out.println("Array list of " + number + " is " +
        // wordsInNumFiles(number));
        System.out
                .println("Total number of words appears in " + number + " files is " + wordsInNumFiles(number).size());
        String word = "cats";
        System.out.println("The file list with word " + word + " is: ");
        printFilesIn(word);
    }

    public int maxNumber() {
        int max = 0;
        for (String w : wordToFiles.keySet()) {
            int arrayListSize = wordToFiles.get(w).size();
            if (arrayListSize > max) {
                max = arrayListSize;
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<String>();
        for (String w : wordToFiles.keySet()) {
            int arrayListSize = wordToFiles.get(w).size();
            if (arrayListSize == number) {
                result.add(w);
            }
        }
        return result;
    }

    public void printFilesIn(String word) {
        ArrayList<String> list = wordToFiles.get(word);
        for (String w : list) {
            System.out.println(w);
        }

    }

    public static void main(String[] args) {
        WordInFiles wif = new WordInFiles();
        wif.tester();
    }

}
