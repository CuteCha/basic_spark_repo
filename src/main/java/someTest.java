import java.util.*;

/**
 * Created by cxq on 2018/4/23.
 */
public class someTest {

    public static void main(String[] args){
        Set<String> set_str = new HashSet<>();
        Map<String,Integer> map = new HashMap<>();
        List<String> list_str = new ArrayList<>();

        map.put("a",32);
        map.put("b",33);
        map.put("c",34);

        list_str.add("a");
        list_str.add("a");
        list_str.add("b");
        list_str.add("c");
        list_str.add("b");
        set_str.clear();

        set_str.addAll(list_str);
        for (String ele : set_str) {
            System.out.println(ele);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
