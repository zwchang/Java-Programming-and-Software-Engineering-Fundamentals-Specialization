public class Part2 {

    public int howMany(String a, String b) {
        int startIndex = b.indexOf(a);
        int count = 0;
        while (startIndex >= 0) {
            startIndex = b.indexOf(a, startIndex + a.length());
            count += 1;
        }
        return count;
    }

    public void testHowMany() {
        String a1 = "by";
        String b1 = "A story by Abby by Long";
        int result1 = howMany(a1, b1);
        String a2 = "an";
        String b2 = "banana";
        int result2 = howMany(a2, b2);
        System.out.println("The occurance of " + a1 + " in " + b1 + " is " + result1);
        System.out.println("The occurance of " + a2 + " in " + b2 + " is " + result2);
    }

    public static void main(String[] args) {
        Part2 oc = new Part2();
        oc.testHowMany();
    }
}
