public class Main {
    public static void main(String[] args) {
        Encryption encryptorTest = new Encryption(2, 2);
        String actualEncrypted1 = encryptorTest.encryptMessage("abcdefghi");
        String expectedEncrypted1 = "Mte eati dmnitgAhA";
        if (actualEncrypted1.equals(expectedEncrypted1))
        {
            System.out.println("\nTest 1 PASSED!");
        }
        else
        {
            System.out.println("\n*** Test 1 FAILED! ***");
            System.out.println("EXPECTED: " + expectedEncrypted1);
            System.out.println("  ACTUAL: " + actualEncrypted1);
        }
    }
}
