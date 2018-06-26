//import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cxq on 2017/5/31.
 */

class StaticNN{
    public static String NN="abc";
    public void tell(){
        System.out.println(NN);
    }

}

public class HelloWorld {
    public static void main(String[] args){
        StaticNN nn = new StaticNN();
        nn.tell();
//        System.out.println("hello world! ~~java~~"+args[0]);
//        System.out.println("hello world! ~~java~~ ");
//        List<String> A= new ArrayList<>();
//        A.add("a1");
//        A.add("a2");
//        List<String> B= new ArrayList<>();
//        B.addAll(A);
//
//        List<Double> C= new ArrayList<>();
//        C.add(0.3);
//        C.add(0.8);
//
//        System.out.println("A:");
//        for(String item:A){
//            System.out.println("\t"+item);
//        }
//
//        System.out.println("B:");
//        for(String item:B){
//            System.out.println("\t"+item);
//        }
//
//        System.out.println("C:");
//        for(Double item:C){
//            System.out.println("\t"+item);
//        }
//
//        List<List<String>> listTest = new ArrayList<List<String>>();
//
//
//        String json_str="{\"total\":920,\"data\":[{\"ID\":\"634\",\"Name\":\"于东\"},{\"ID\":\"822\",\"Name\":\"于祎\"},{\"ID\":\"782\",\"Name\":\"于燕\"},{\"ID\":\"636\",\"Name\":\"于玲\"},{\"ID\":\"841\",\"Name\":\"于浩\"},{\"ID\":\"383\",\"Name\":\"于娟\"}]}";
//        JSONObject jsonObject= JSONObject.fromObject(json_str);
    }
}
