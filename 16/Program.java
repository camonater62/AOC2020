import java.util.*;
import java.io.*;

public class Program {

    private static class Range {
        int start;
        int end;

        public Range(int s, int e) {
            start = s;
            end = e;
        }
        
        // 'start-end' format
        public Range(String s) {
            String[] split = s.trim().split("-");
            start = Integer.parseInt(split[0]);
            end = Integer.parseInt(split[1]);
        }

        public boolean contains(int x) {
            return (x >= start) && (x <= end);
        }

        public String toString() {
            return start + "-" + end;
        }
    }

    private static class DualRange {
        Range a;
        Range b;

        public DualRange(Range a, Range b) {
            this.a = a;
            this.b = b;
        }

        // 'start-end or start-end' format
        public DualRange(String s) {
            String[] split = s.split(" or ");
            a = new Range(split[0]);
            b = new Range(split[1]);
        }

        public boolean contains(int x) {
            return a.contains(x) || b.contains(x);
        }

        public String toString() {
            return a + " or " + b;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        int part = 0;

        ArrayList<DualRange> ranges = new ArrayList<DualRange>();
        ArrayList<Integer> myTicket = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> otherTickets = new ArrayList<ArrayList<Integer>>();
        
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("")) {
                part++;

                if(part > 0) {
                    line = scan.nextLine();
                }
            } else {
                String[] nums = null;
                switch(part) {
                    case 0: // Ranges
                        ranges.add(new DualRange(line.substring(line.indexOf(":") + 1)));
                        break;
                    case 1: // My ticket
                        nums = line.split(",");
                        for(String s : nums) {
                            myTicket.add( Integer.parseInt(s) );
                        }
                        break;
                    case 2: // Other tickets
                        nums = line.split(",");
                        ArrayList<Integer> ticket = new ArrayList<Integer>();
                        for(String s : nums) {
                            ticket.add( Integer.parseInt(s) );
                        }
                        otherTickets.add(ticket);
                        break;
                }
            }
        }
        
        scan.close();

        int error = 0;
        for(int k = 0; k < otherTickets.size(); k++) {
            ArrayList<Integer> ticket = otherTickets.get(k);
            
            boolean bigvalid = true;
            for(int i = 0; i < ticket.size(); i++) {
                boolean valid = false;
                for(int j = 0; j < ranges.size(); j++) {
                    if(ranges.get(j).contains(ticket.get(i))) {
                        valid = true;
                    }
                }
                if(!valid) {
                    error += ticket.get(i);
                    bigvalid = false;
                }
            }
            if(!bigvalid) {
                otherTickets.remove(k);
                k--;
            }
        }

        System.out.printf("P1: %d\n", error);

        HashMap<Integer, ArrayList<Integer>> fields = new HashMap<Integer, ArrayList<Integer>>();
        
        for(int i = 0; i < ranges.size(); i++) {
            boolean[] valid = new boolean[ranges.size()];
            fields.put(i, new ArrayList<Integer>());
            for(int j = 0; j < ranges.size(); j++) {
                valid[j] = true;
            }
            for(int j = 0; j < otherTickets.size(); j++) {
                ArrayList<Integer> ticket = otherTickets.get(j);
                for(int k = 0; k < ticket.size(); k++) {
                    if(!ranges.get(i).contains(ticket.get(k))) {
                        valid[k] = false;
                    }
                }
            }
            for(int j = 0; j < valid.length; j++) {
                if(valid[j]) {
                    fields.get(i).add(j);
                }
            }
        }
        ArrayList<Integer> vals = new ArrayList<Integer>(fields.keySet());
        Collections.sort(vals, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return fields.get(a).size() - fields.get(b).size();
            }
        });
        for(int i = 0; i < vals.size(); i++) {
            int rem = fields.get(vals.get(i)).get(0);
            for(int j = i + 1; j < vals.size(); j++) {
                fields.get(vals.get(j)).remove((Integer)rem);
            }
        }

        long mult = 1;
        for(int i = 0; i < 6; i++) {
            mult *= myTicket.get(fields.get(i).get(0));
        }
        System.out.printf("P2: %d\n", mult);
        

    }
}