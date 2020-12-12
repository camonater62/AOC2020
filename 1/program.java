import java.io.*;
import java.util.*;

class program {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input"));
            ArrayList<Integer> nums = new ArrayList<Integer>();
            while(scan.hasNextLine()) {
                nums.add(scan.nextInt());
            }
            scan.close();

            for(int i = 0; i < nums.size() - 1; i++) {
                for(int j = i + 1; j < nums.size(); j++) {
                    for(int k = j + 1; k < nums.size(); k++) {
                        if(nums.get(i) + nums.get(j) + nums.get(k) == 2020) {
                            System.out.println(nums.get(i) * nums.get(j) * nums.get(k));
                        }
                    }
                    
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }        
    }

}