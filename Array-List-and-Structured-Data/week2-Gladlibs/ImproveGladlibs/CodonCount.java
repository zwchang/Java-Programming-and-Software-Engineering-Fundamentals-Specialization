import java.util.HashMap;
import edu.duke.FileResource;

public class CodonCount {

    private HashMap<String, Integer> mapCodon;

    public CodonCount() {
        mapCodon = new HashMap<String, Integer>();
    }

    private static String dna;
    private int inputOp = 1;

    public void buildCodonMap(int start, String dna) {
        dna = dna.toUpperCase();
        for (int k = start; k <= dna.length() - 3; k += 3) {
            String currCodon = dna.substring(k, k + 3);
            if (!mapCodon.containsKey(currCodon)) {
                mapCodon.put(currCodon, 1);
            } else {
                mapCodon.put(currCodon, mapCodon.get(currCodon) + 1);
            }
        }

        System.out.println("Reading frame starting with " + start + " result in " + mapCodon.size() + " unique codons");

        // for (String currCodon : mapCodon.keySet()) {
        // int occurences = mapCodon.get(currCodon);
        // System.out.println(occurences + "\t" + currCodon);
        // }
    }

    public void tester() {

        if (inputOp == 0) {
            // dna = "CGTTCAAGTTCAA";
            dna = "ATTAATACTTTGTTTAACAGTAATTATTCAACTATTAAATATTTAAATAATTAAGTTATTAAACTATTAAGTACAGGGCCTGTATCTCTGATGCTGAACTATGATGTGTGACTTAAGCCCCCAAATACATCATGTTATTTGGATCCAAGGTGCTGCACAGAACGCTGACCCTCTCTAAGAGCTGGGTATTACT";
            // dna =
            // "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        } else {
            FileResource fr = new FileResource();
            dna = fr.asString();
        }
        mapCodon.clear();
        int start = 0;
        buildCodonMap(start, dna);
        getMostCommonCodon();
        int startInd = 5;
        int endInd = 8;
        System.out.println("Counts of codons between " + startInd + " and " + endInd + " includsive are: ");
        printCodonCounts(startInd, endInd);

    }

    public String getMostCommonCodon() {
        int maxOccur = 0;
        String result = "";
        for (String currCodon : mapCodon.keySet()) {
            int occurences = mapCodon.get(currCodon);
            if (occurences > maxOccur) {
                maxOccur = occurences;
                result = currCodon;
            }
        }
        System.out.println("and the most common codon is: " + result + " with count " + maxOccur);
        return result;
    }

    public void printCodonCounts(int start, int end) {
        // String subdna = dna.substring(start, end + 1);
        // buildCodonMap(0, subdna);
        for (String currCodon : mapCodon.keySet()) {
            int occurences = mapCodon.get(currCodon);
            if (occurences >= start && occurences <= end) {
                System.out.println(occurences + "\t" + currCodon);
            }
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }

}
