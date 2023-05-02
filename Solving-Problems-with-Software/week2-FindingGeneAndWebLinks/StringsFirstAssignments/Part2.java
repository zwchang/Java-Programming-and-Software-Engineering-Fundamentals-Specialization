// import edu.duke.*;
// import java.io.*;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 != 0) {
            return "";
        } else {
            return dna.substring(startIndex, stopIndex + 3);
        }
    }

    public void testing() {
        // String dna = "EFDCCCTTTTAADSDSADT";
        // String dna = "EFDATGCCCTTTDSDSADT";
        // String dna = "EFDCCCTTTDSDSADT";
        // String dna = "EFDATGCCCTTTTAADSDSADT";
        // String dna = "EFDATGCCCTTBTTAADSDSADT";
        String dna = "efdatgcccttbttaadsdsadt";
        String startCodon = "ATG";
        String stopCodon = "TTA";
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene is " + gene);
    }

    public static void main(String[] args) {
        Part2 ge = new Part2();
        ge.testing();
    }
}
