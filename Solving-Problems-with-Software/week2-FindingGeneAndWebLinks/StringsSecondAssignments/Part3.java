import edu.duke.*;

public class Part3 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return dna.length();
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
            return "";
        else {
            int TAAstopIndex = findStopCodon(dna, startIndex, "TAA");
            int TAGstopIndex = findStopCodon(dna, startIndex, "TAG");
            int TGAstopIndex = findStopCodon(dna, startIndex, "TGA");

            int currMin = Math.min(TAAstopIndex, TAGstopIndex);
            int minIndex = Math.min(currMin, TGAstopIndex);

            if (minIndex == dna.length())
                return "";
            else {
                return dna.substring(startIndex, minIndex + 3);
            }
        }

    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }

    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            count += 1;
        }
        return count;
    }

    // public String mystery(String dna) {
    // int pos = dna.indexOf("T");
    // int count = 0;
    // int startPos = 0;
    // String newDna = "";
    // if (pos == -1) {
    // return dna;
    // }
    // while (count < 3) {
    // count += 1;
    // newDna = newDna + dna.substring(startPos, pos);
    // startPos = pos + 1;
    // pos = dna.indexOf("T", startPos);
    // if (pos == -1) {
    // break;
    // }
    // }
    // newDna = newDna + dna.substring(startPos);
    // return newDna;
    // }

    public void testCountGenes() {
        String dna0 = "AAATGCCCTACTATTABAAACC"; // no stop condons
        String dna1 = "AAATGCCCTGACDETAGATTAAGAAACC"; // one gene - multiple stop condons
        String dna2 = "CDATCCCDADFASDFADSFASD"; // no ATG
        String dna3 = "ATGCCCTGAATGTAGATGTAA"; // three genes
        String dna4 = "ATGTAAGATGCCCTAGT"; // two genes
        // String dna = dna0;
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        // System.out.println("The DNA string is " + dna);
        System.out.println("The gene is " + countGenes(dna));
        // System.out.println(mystery(dna));
    }

    public static void main(String[] args) {
        Part3 oc = new Part3();
        oc.testCountGenes();
    }

}
