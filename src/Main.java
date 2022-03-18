public class Main {
    public static void main(String[] args) {
        Encryption encryptorTest = new Encryption(2, 4);
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

        Encryption encryptorTesterM = new Encryption(2, 4);
        String actualDecrypted6 = encryptorTesterM.decryptMessage("cdabghefAAiAAAAA");
        String expectedDecrypted6 = "ABCD EFGH ABCD EFGH ABCD EFGH ABCD EFGH!";
        if (actualDecrypted6.equals(expectedDecrypted6))
        {
            System.out.println("\nTest 5 PASSED!");
        }
        else
        {
            System.out.println("\n*** Test 5 FAILED! ***");
            System.out.println("EXPECTED: " + expectedDecrypted6);
            System.out.println("  ACTUAL: " + actualDecrypted6);
        }
    }
}
