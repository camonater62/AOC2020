import java.util.*;
import java.io.*;

public class program {
    public static void main(String[] args) throws Exception {
        int p1count = 0;
        int p2count = 0;

        Scanner scan = new Scanner(new File("input"));

        while(scan.hasNextLine()) {
            String input = scan.nextLine();

            String[] spaced = input.split(" ");

            String[] range = spaced[0].split("-");
            int min = Integer.parseInt(range[0]);
            int max = Integer.parseInt(range[1]);

            char target = spaced[1].charAt(0);

            String pass = spaced[2];

            int chars = 0;
            for(char c : pass.toCharArray()) {
                if(c == target) chars++;
            }

            if(chars >= min && chars <= max) {
                p1count++;
            }

            if((pass.charAt(min - 1) == target) ^ (pass.charAt(max - 1) == target)) {
                p2count++;
            }
        }

        scan.close();
        
        System.out.println("P1: " + p1count);
        System.out.println("P2: " + p2count);
    }
}