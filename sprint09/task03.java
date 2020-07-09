import java.io.*;

/*
 * Create the method writeFile(String filename, String text) that write the text to
 * file as sequence of bytes in binary format.
 * For example, the text fragment: "Hello!"
 * should be represented in the file as a sequence of 7-bit bytes without spaces between them.
 * If less than 7 bits are required to represent the character then add to binary sequence leading zeros '0'.
 */

/**
 * @author Bogdan Kurchak
 */
public static void writeFile(String filename, String text) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(filename, false)) {
        for (int i = 0; i < text.length(); i++) {
            int codeOfChar = text.charAt(i);
            StringBuilder binaryFormat = new StringBuilder();
            
            while (codeOfChar > 0) {
                binaryFormat.append(codeOfChar % 2);
                codeOfChar /= 2;
            }
            
            int length = binaryFormat.length();
            
            for (int j = 0; j < (7 - length); j++) {
                binaryFormat.append('0');
            }
            
            binaryFormat.reverse();
            
            fileOutputStream.write(binaryFormat.toString().getBytes());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
