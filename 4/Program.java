import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        String[] required = { "byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:" };
        ArrayList<String> passports = new ArrayList<String>();
        String current = "";

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("")) {
                passports.add(current);
                current = "";
            } else {
                current += line + " ";
            }
        }

        scan.close();
        
        if(!current.equals("")) {
            passports.add(current);
        }

        int p1count = 0;
        int p2count = 0;

        for(String pp : passports) {
            int segCount = 0;
            for(String r : required) {
                if(pp.contains(r)) {
                    segCount++;
                }
            }
            if(segCount == required.length) {
                p1count++;

                boolean valid = true;

                try {
                    String[] split = pp.split(" ");
                    HashMap<String, String> map = new HashMap<String, String>();
                    for(String s : split) {
                        String[] colsplit = s.split(":");
                        map.put(colsplit[0], colsplit[1]);
                    }

                    int byr = Integer.parseInt(map.get("byr"));
                    if(byr < 1920 || byr > 2002) valid = false;
                    int iyr = Integer.parseInt(map.get("iyr"));
                    if(iyr < 2010 || iyr > 2020) valid = false;
                    int eyr = Integer.parseInt(map.get("eyr"));
                    if(eyr < 2020 || eyr > 2030) valid = false;

                    String height = map.get("hgt");
                    int hgt = Integer.parseInt(height.substring(0, height.length() - 2));
                    if(height.substring(height.length() - 2).equals("cm")) {
                        if(hgt < 150 || hgt > 193) valid = false;
                    }
                    if(height.substring(height.length() - 2).equals("in")) {
                        if(hgt < 59 || hgt > 76) valid = false;
                    }

                    Pattern hair = Pattern.compile("#[0-9a-f]{6}");
                    Matcher matcher = hair.matcher(map.get("hcl"));
                    if(!matcher.matches()) valid = false;

                    String eyes = "amb blu brn gry grn hzl oth";
                    if(!eyes.contains(map.get("ecl"))) valid = false;

                    int notusedlol = Integer.parseInt(map.get("pid"));
                    if(map.get("pid").length() != 9) valid = false;
                } catch(Exception e) {
                    valid = false;
                }

                

                if(valid) {
                    p2count++;
                }
            }
        }

        System.out.println("P1: " + p1count);
        System.out.println("P2: " + p2count);
    }
}