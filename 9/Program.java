import java.util.*;
import java.io.*;
public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        int preamb = 25;
        ArrayList<Long> avail = new ArrayList<Long>();
        while(avail.size() < preamb) {
            avail.add(scan.nextLong());
        }

        long p1 = 0;
        while(scan.hasNextLong()) {
            long i = scan.nextLong();

            boolean valid = false;
            for(int y = 0; y < avail.size() - 1; y++) {
                for(int x = y + 1; x < avail.size(); x++) {
                    if(avail.get(y) != avail.get(x)) {
                        if(avail.get(y) + avail.get(x) == i) {
                            valid = true;
                        }
                    }
                }
            }
            if(!valid) {
                System.out.println(i);
                p1 = i;
                break;
            } else {
                avail.add(i);
                avail.remove(0);
            }
        }

        scan.close();
        scan = new Scanner(new File("input"));

        avail.clear();
        while(scan.hasNextLong()) {
            avail.add(scan.nextLong());
        }

        for(int start = 0; start < avail.size(); start++) {
            long sum = avail.get(start);
            int end = 0;
            for(end = start + 1; sum < p1; end++) {
                sum += avail.get(end);
            }
            end -= 1;
            if(sum == p1) {
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;

                for(int i = start; i <= end; i++) {
                    min = Math.min(min, avail.get(i));
                    max = Math.max(max, avail.get(i));
                }

                System.out.println(min);
                System.out.println(max);
                System.out.println(min + max + "\n\n\n");
            }
        }
    }
}