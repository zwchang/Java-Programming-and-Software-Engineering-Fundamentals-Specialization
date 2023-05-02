import edu.duke.*;

public class Part4 {
    public void findLinks(String ur) {
        URLResource currURL = new URLResource(ur);
        String keyWord = "youtube.com";
        for (String word : currURL.words()) {
            if (word.toLowerCase().indexOf(keyWord) != -1) {
                String wordLower = word.toLowerCase();
                int keyIndex = wordLower.indexOf(keyWord);
                int beginIndex = word.lastIndexOf("\"", keyIndex);
                int endIndex = word.indexOf("\"", keyIndex + 1);
                System.out.println(word.substring(beginIndex + 1, endIndex));
            }
        }
    }

    public void testing() {
        findLinks("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }

    public static void main(String[] args) {
        Part4 url = new Part4();
        url.testing();
    }
}
