public class CaesarCipherOO {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOO(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(i, newChar);
                }
            }
            if (Character.isLowerCase(currChar)) {
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx != -1) {
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    sb.setCharAt(i, newChar);
                }
            }
        }
        return sb.toString();
    }

    public String decrypt(String input) {
        CaesarCipherOO cc = new CaesarCipherOO(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
