public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 1);
        if (stopIndex != -1 && (stopIndex - startIndex) % 3 == 0) {
            return stopIndex;
        } else {
            return dna.length();
        }
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
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

    public void testFindStopCodon() {
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        int startInd = 2;
        String stopCodon = "TAG";
        int stopIndex = findStopCodon(dna, startInd, stopCodon);
        System.out.println("The DNA is " + dna);
        System.out.println("The length of DNA is " + dna.length());
        System.out.println("The stop index is " + stopIndex);
    }

    public static void main(String[] args) {
        Part1 ge = new Part1();
        ge.testFindStopCodon();
    }
}