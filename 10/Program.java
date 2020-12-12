import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));
        ArrayList<Integer> adaptors = new ArrayList<Integer>();
        while(scan.hasNextInt()) {
            adaptors.add(scan.nextInt());
        }
        scan.close();


        Collections.sort(adaptors);

        
        int largest = Integer.MIN_VALUE;

        int gap1 = 1, gap3 = 1;
        for(int i = 0; i < adaptors.size() - 1; i++) {
            int diff = adaptors.get(i + 1) - adaptors.get(i);
            if(diff == 1) gap1++;
            if(diff == 3) gap3++;
            largest = Math.max(Math.max(adaptors.get(i + 1), adaptors.get(i)), largest);
        }

        System.out.println("P1: " + gap1 *  gap3);

        int device = largest + 3;

        
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        map.put(device, 1L);
        for(int i = adaptors.size() - 1; i >= 0; i--) {
            int curr = adaptors.get(i);
            long count = 0;
            for(int j = curr + 1; j <= curr + 3; j++) {
                if(j == device || adaptors.contains(j)) {
                    count += map.getOrDefault(j, 0L);
                }
            }
            map.put(curr, count);
        }
        long ways = 0;
        System.out.println(map);
        for(int i = 1; i <= 3; i++) {
            ways += map.getOrDefault(i, 0L);
        }
        System.out.println("P2: " + ways);
    }
}