import java.util.*;
import java.io.*;

public class Program {

    private static int getAccum(ArrayList<String> ops, boolean loopVal) {
        int accum = 0;
        boolean loop = false;

        HashSet<Integer> visited = new HashSet<Integer>();

        for(int i = 0; i < ops.size(); i++) {
            if(visited.contains(i)) {
                loop = true;
                break;
            }

            visited.add(i);
            
            String[] split = ops.get(i).split(" ");
            String inst = split[0];
            int val = Integer.parseInt(split[1]);

            if(inst.equals("acc")) {
                accum += val;
            }
            if(inst.equals("jmp")) {
                i += val - 1;
            }
        }

        if(loopVal || !loop)
            return accum;
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        ArrayList<String> ops = new ArrayList<String>();
        while(scan.hasNextLine()) {
            ops.add(scan.nextLine());
        }

        scan.close();

        System.out.println("P1: " + getAccum(ops, true));

        int best = 0;
        for(int i = 0; i < ops.size(); i++) {
            String[] split = ops.get(i).split(" ");
            int val = Integer.parseInt(split[1]);
            ArrayList<String> testOps = new ArrayList<String>(ops);
            
            if(split[0].equals("jmp")) {      
                testOps.set(i, "nop " + val);
            }
            if(split[0].equals("nop")) {
                testOps.set(i, "jmp " + val);
            }
            
            best = Math.max(best, getAccum(testOps, false));
        }

        System.out.println("P2: " + best);
    }

}