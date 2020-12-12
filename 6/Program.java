import java.util.*;
import java.io.*;

public class Program {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        String current = "";
        ArrayList<String> groups = new ArrayList<String>();

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("")) {
                groups.add(current);
                current = "";
            } else {
                current += line + " ";
            }
        }
        if(!current.equals("")) {
            groups.add(current);
        }

        scan.close();        

        int p1count = 0;
        int p2count = 0;

        for(String group : groups) {
            HashSet<Character> chars = new HashSet<Character>();
            ArrayList<Character> common = new ArrayList<Character>();
            String[] split = group.split(" ");
            for(char c : split[0].toCharArray()) {
                common.add(c);
            }
            for(int i = 0; i < common.size(); i++) {
                for(int j = 0; j < split.length; j++) {
                    if(!split[j].contains(common.get(i) + "")) {
                        common.remove(i);
                        i--;
                        j = split.length;
                    }
                }
            }
            for(char c : group.toCharArray()) {
                if(!Character.isWhitespace(c)) {
                    chars.add(c);
                }
            }
            p1count += chars.size();
            p2count += common.size();
        }
        System.out.println("P1: " + p1count);
        System.out.println("P2: " + p2count);
        
    }
}