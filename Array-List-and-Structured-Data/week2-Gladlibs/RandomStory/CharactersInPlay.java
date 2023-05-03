import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> namesCounts;

    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        namesCounts = new ArrayList<Integer>();
    }

    public void update(String person) {
        // person = person.toLowerCase();
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            namesCounts.add(1);
        } else {
            int count = namesCounts.get(index);
            namesCounts.set(index, count + 1);
        }
    }

    public void findAllCharacters() {
        characterNames.clear();
        namesCounts.clear();
        // FileResource fr = new
        // FileResource("./ProgrammingRandomStoryData/macbethSmall.txt");
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            for (int i = 0; i < line.length(); i++) {
                char currChar = line.charAt(i);
                if (currChar == '.') {
                    int idx = line.indexOf(currChar);
                    String name = line.substring(0, idx);
                    // System.out.println(name);
                    update(name);
                    break;
                }
            }
        }
    }

    public void tester() {
        findAllCharacters();
        // for (int k = 0; k < characterNames.size(); k++) {
        // System.out.println(namesCounts.get(k) + "\t" + characterNames.get(k));
        // }
        charactersWithNumParts(10, 15);
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < characterNames.size(); k++) {
            if (namesCounts.get(k) >= num1 && namesCounts.get(k) <= num2) {
                System.out.println(namesCounts.get(k) + "\t" + characterNames.get(k));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay CIP = new CharactersInPlay();
        CIP.tester();
    }
}
