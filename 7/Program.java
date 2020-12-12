import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Program {
    private static int count(String bag, HashMap<String, String> map) {
        if(map.get(bag).startsWith("no ")) return 0;

        Pattern pattern = Pattern.compile("\\s[^0-9 ]+");
        Matcher match = pattern.matcher(map.get(bag)); 

        String[] subbags = match.replaceAll("").split(" ");
        int[] subvalue = new int[subbags.length];

        int count = 0;

        int i = 0;
        for(String s : subbags) {
            subvalue[i] = Integer.parseInt(s);
            i++;
        }

        subbags = map.get(bag).split(" , ");

        i = 0;
        for(String s : subbags) {
            String currBag = s.substring(s.indexOf(" ")).trim();
            count += count(currBag, map) * subvalue[i] + subvalue[i];
            i++;
        }

        return count;
    }
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        HashMap<String, String> map = new HashMap<>();

        String target = "shiny gold";

        while(scan.hasNextLine()) {
            String line = scan.nextLine();

            String[] split = line.split("contain");

            String color = split[0].replace("bags", "")
                    .replace("bag", "").trim();
            String contained = split[1].replace("bags", "")
                    .replace("bag", "").replace(".", "").trim();

            map.put(color, contained);
        }

        scan.close();

        HashSet<String> all = new HashSet<String>();

        for(String key : map.keySet()) {
            if(map.get(key).contains(target)) {
                all.add(key);
            }
        }

        int startSize;

        do {
            startSize = all.size();
            HashSet<String> toAdd = new HashSet<String>();
            for(String t : all) {
                for(String key : map.keySet()) {
                    if(map.get(key).contains(t)) {
                        toAdd.add(key);
                    }
                }
            }
            all.addAll(toAdd);
        } while(all.size() != startSize);

        System.out.println("P1: " + all.size());        
        System.out.println("P2: " + count(target, map));
    }
}