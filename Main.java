import java.util.HashMap;
import java.util.Map;

import jogoson.Jogoson;

public class Main {
    public static void main(String[] args){
        System.out.println("Jogoson Jogosonimnida");

        String str = "";

        FileReadService fs = new FileReadService();
        str = fs.readFileString("./test-json-data/elq1.json");

        // long n = 10000;
        // long t0, t1;
        // t0 = System.currentTimeMillis();
        // for(long i=0; i<n; i++){
        //     Jogoson j = new Jogoson();
        //     Object o = j.parseValue(str);
        // }
        // t1 = System.currentTimeMillis();
        // System.out.println((t1-t0)/1000.0);

        Jogoson j = new Jogoson();
        Object o = j.deserialize(str);
        System.out.println(o);

        System.out.println("BYE WORLD !");
    }
}
