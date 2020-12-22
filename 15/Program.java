import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));
        ArrayList<Integer> starting = new ArrayList<Integer>();
        String[] nums = scan.nextLine().split(",");
        for(String s : nums) {
            starting.add(Integer.parseInt(s));
        }
        scan.close();

        ArrayList<Integer> game = new ArrayList<Integer>(starting);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < starting.size(); i++) {
            map.put(starting.get(i), i);
        }
        for(int round = starting.size(); round < 30000000; round++) {
            int last = game.get(round - 1);

            int num1 = map.getOrDefault(last, -1);
            if(num1 == -1) {
                game.add(0);
            } else {
                game.add(Math.abs(round - num1 - 1));
            }
            map.put(last, round - 1);
        }
        System.out.println(game.get(game.size() - 1));
        
    }
}
