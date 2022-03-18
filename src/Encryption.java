import java.util.*;
public class Encryption
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryption(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        /* to be implemented in part (a) */
        int count = 0;
        for(int i = 0; i < numRows; i++) {
            for(int c = 0; c < numCols; c++) {
                if(count < str.length()) {
                    letterBlock[i][c] = str.substring(count, count + 1);
                    count++;
                }
                else{
                    letterBlock[i][c] = "A";
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        /* to be implemented in part (b) */
    String[][] jamble = new String[numRows][numCols];
    int shift = 2;
    String encrypted ="";
    if (shift == letterBlock[0].length) {
        for(String[] wordList : letterBlock) {
            for(String letter : wordList) {
                encrypted += letter;
            }
        }
    }
    else {
        for(int rr = 0; rr < letterBlock.length; rr++) {
            for(int cc = 0; cc< letterBlock[0].length; cc++) {
                if(cc >= letterBlock[0].length - shift) {
                    jamble[rr][(cc + shift) - letterBlock[0].length] = letterBlock[rr][cc];
                }
                else {
                    jamble[rr][cc + 2] = letterBlock[rr][cc];
                }
            }
        }
        for(String[] wordList : jamble) {
            for(String letter : wordList) {
                encrypted += letter;
            }
        }
    }
    return encrypted;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        /* to be implemented in part (c) */
        String encrypted = "";
        if(message.equals("")) return "";
        int size = numCols * numRows;
        String sent = message;
        if (sent.length() > size) {
            while(!sent.equals("")) {
                String part = sent.substring(0, sent.length());
                fillBlock(part);
                encrypted += encryptBlock();
                if(sent.length() < size) sent = "";
                else {
                    sent = sent.substring(size);
                }
            }
        }
        else {
            String part = sent.substring(0, size);
            fillBlock(part);
            encrypted = encryptBlock();
        }
        return encrypted;

    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        /* to be implemented in part (d) */
        int size = numRows * numCols;
        int num = encryptedMessage.length() / size;
        String[] eWords = new String[num];
        int temp = 0;
        for(int i = 0; i < eWords.length; i++) {
            eWords[i] = encryptedMessage.substring(temp, temp + size);
            temp += size;
        }
        String encrypted ="";
        for(int ii = 0; ii < eWords.length; ii++) {
            int count = 0;
            String[][] wordList = new String[numRows][numCols];
            for (int r = 0; r < wordList.length; r++) {
                for (int c = 0; c < wordList[0].length; c++) {
                    wordList[r][c] = eWords[ii].substring(count, count + 1);
                    count++;
                }
            }
            int shift = 2;

                String[][] newDecrypt = new String[numRows][numCols];
                for (int r = 0; r < wordList.length; r++) {
                    for (int c = 0; c < wordList[0].length; c++) {
                        if (c < shift) {
                            newDecrypt[r][(c + wordList[0].length) - shift] = wordList[r][c];
                        } else {
                            newDecrypt[r][c - 2] = wordList[r][c];
                        }
                    }
                }
                if (ii == eWords.length - 1) {
                    for (int aNums = 0; aNums < newDecrypt.length; aNums++) {
                        for (int aNum = 0; aNum < newDecrypt[0].length; aNum++) {
                            if (newDecrypt[aNums][aNum].equals("A"))
                                newDecrypt[aNums][aNum] = "key";
                        }
                    }
                }
                    for (int rr = 0; rr < newDecrypt.length; rr++) {
                        for (int cc = 0; cc < newDecrypt[0].length; cc++) {
                            if (ii == eWords.length - 1) {
                                if (newDecrypt[rr][cc].equals("key")) break;
                            }
                            encrypted += newDecrypt[rr][cc];
                        }
                    }
            }

        return encrypted;
    }
}