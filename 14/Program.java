import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        String mask = "";
        HashMap<Long, Long> map = new HashMap<Long, Long>();
        Scanner scan = new Scanner(new File("input"));

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(" = ");

            if(line.startsWith("mask")) {
                mask = split[1];
            }
            if(line.startsWith("mem")) {
                long addr = Long.parseLong( line.substring(line.indexOf("[") + 1, line.indexOf("]")) );
                String val = Long.toString( Long.parseLong(split[1]), 2 );
                while(val.length() < 36) {
                    val = "0" + val;
                }

                String masked = "";

                for(int i = 0; i < val.length(); i++) {
                    if(mask.charAt(i) == 'X') {
                        masked += val.charAt(i);
                    } else {
                        masked += mask.charAt(i);
                    }
                }

                map.put(addr, Long.parseLong(masked, 2));
            }
        }

        scan.close();

        long sum = 0;

        for(long l : map.values()) {
            sum += l;
        }

        System.out.printf("P1: %d\n", sum);

        // BEGIN PART 2

        mask = "";
        map = new HashMap<Long, Long>();
        scan = new Scanner(new File("input"));

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(" = ");

            if(line.startsWith("mask")) {
                mask = split[1];
            }
            if(line.startsWith("mem")) {
                String addr = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                addr = Long.toString(Long.parseLong(addr), 2);
                long val = Long.parseLong( split[1] );
                while(addr.length() < 36) {
                    addr = "0" + addr;
                }



                String masked = "";
                for(int i = 0; i < addr.length(); i++) {
                    if(mask.charAt(i) == '0') {
                        masked += addr.charAt(i);
                    } else {
                        masked += mask.charAt(i);
                    }
                }

                ArrayList<String> masks = new ArrayList<String>();
                
                masks.add(masked);
                for(int i = 0; i < masks.size(); i++) {
                    if(masks.get(i).contains("X")) {
                        String base = masks.get(i);
                        masks.add(base.replaceFirst("X", "0"));
                        masks.add(base.replaceFirst("X", "1"));
                        masks.remove(i);
                        i--;
                    }
                }
                // System.out.println(masks);

                for(int i = 0; i < masks.size(); i++) {
                    map.put(Long.parseLong(masks.get(i), 2), val);
                }

            }
        }

        scan.close();

        sum = 0;

        for(long l : map.values()) {
            sum += l;
        }

        System.out.printf("P2: %d\n", sum);

        
    }
}
