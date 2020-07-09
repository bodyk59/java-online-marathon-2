import java.io.*;

/*
 * Create the method readFile(String filename) that read from file a sequence of bytes
 * in binary format from previous task and return ridable string.
 * For example, the sequence of 7-bit bytes
 * 100100011001011101100110110011011110100001
 * should be represented as text fragment:
 * Hello!
 */

/**
 * @author Bogdan Kurchak
 */
public static String readFile(String filename) {
    StringBuilder answer = new StringBuilder();
    try (FileInputStream fileInputStream = new FileInputStream(filename);
         java.util.Scanner scanner = new java.util.Scanner(fileInputStream)) {
        StringBuilder binaryLine = new StringBuilder(scanner.nextLine());
            
        int start = 0;
        int finish = 7;
        int result = 0;

        for (int i = 0; i < (binaryLine.length() / 7); i++) {
            String symbol = binaryLine.substring(start, finish);
            int length = symbol.length() - 1;
            
            for (int j = 0; j < symbol.length(); j++) {
                result = (int)(result + (Integer.parseInt(String.valueOf(symbol.charAt(j))) * Math.pow(2, length--)));
            }

            answer.append((char)result);
                
            start += 7;
            finish += 7;
            result = 0;
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    return answer.toString();
}
