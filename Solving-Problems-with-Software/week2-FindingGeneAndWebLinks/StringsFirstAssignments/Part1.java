public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna = "EFDATGCCCTBTTAADSDSADT";
        String gene = findSimpleGene(dna);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene is " + gene);
    }

    public static void main(String[] args) {
        Part1 ge = new Part1();
        ge.testing();
    }
}
