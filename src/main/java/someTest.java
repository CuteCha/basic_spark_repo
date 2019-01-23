import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cxq on 2018/4/23.
 */
public class someTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (true) {
            str = sc.next();
            str = str.replace("吗", "");
            str = str.replace("？", "！");
            str = str.replace("?", "！");
            System.out.println(str);
        }

    }

    @Test
    public void testStrReplace(){
        String x="aba\tde";
        System.out.println(x.replace("\t",""));
        System.out.println(StringUtils.replace(x,"\t",""));
    }

    @Test
    public void testSort() {
        List<Double> a = new ArrayList<>();
        List<String> b = new ArrayList<>();

        a.add(0.89);
        b.add("0.89");
        a.add(0.90);
        b.add("0.90");
        a.add(0.93);
        b.add("0.93");
        b.add("0.93,0.9");
        b.add("0.93,0.7");
        b.add("0.93,0.8");

        a.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });

        b.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
    }

    @Test
    public void testAddAll() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            a.add(i);
            b.add(i);
        }
        c.addAll(a);
        c.addAll(b);

        System.out.println(c.toString());

        Set<Integer> d = new HashSet<>(c);
        System.out.println(d.toString());

        System.out.println(StringUtils.replace("a|b", "|", "\t"));
        System.out.println(StringUtils.replace("a\tb", "\t", "|"));

        System.out.println(StringUtils.join(d, "\t"));

        d.remove(0);
        System.out.println(StringUtils.join(d, "\t"));
//        StringBuffer sb = new StringBuffer();
//        String outPath = "./output/tmp/xxx.txt";
//        try {
//            Files.deleteIfExists(Paths.get(outPath));
//            FileWriter writer = new FileWriter(outPath,true);
//            sb.append(StringUtils.join(d, "\t"));
//            BufferedWriter bw = new BufferedWriter(writer);
//            bw.write(sb.toString());
//            bw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    @Test
    public void testStringInit() {
        String[] esMappingTypeNames = {"precision", "zuoyebang", "zhidao", "wenwen"};
        for (String i : esMappingTypeNames) {
            System.out.println(i);
        }
    }

    @Test
    public void testSet() {
        Set<String> set_str = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        List<String> list_str = new ArrayList<>();

        map.put("a", 32);
        map.put("b", 33);
        map.put("c", 34);

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
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        System.out.println(StringUtils.repeat("-", 36));
        Stream.of("hello", "world").collect(Collectors.toList()).forEach(System.out::println);

        System.out.println(StringUtils.repeat("-", 36));
        List<String> al = Arrays.asList("a", "b", "c", "d");
        al.forEach(System.out::println);

        System.out.println(StringUtils.repeat("-", 36));
        Consumer<String> methodParam = System.out::println;
        al.forEach(x -> methodParam.accept(x));

        String a = "a b c d e f g";
        List<String> k = new ArrayList<>();
        k.add("b");
        k.add("f");
        k.add("l");

        for (String each : k) {
            if (a.contains(each)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    @Test
    public void testFlatMap() {
        ArrayList<List<String>> group = new ArrayList<List<String>>();
        group.add(Arrays.asList("a", "b", "c"));
        group.add(Arrays.asList("e", "f", "g"));

        List<List<String>> e = group;
        for (List<String> x : e) {
            for (String y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }

        e.stream().flatMap(x -> x.stream()).forEach(System.out::print);
        System.out.println();
        List<String> l = e.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(l.size());

    }

    @Test
    public void testStrContains() {
        String x = "洛杉矶快船队";
        System.out.println(x.indexOf("快船"));
        System.out.println(x.indexOf("湖人"));
        System.out.println(x.indexOf("湖人") == -1);
        System.out.println(x.contains("湖人"));
        System.out.println(x.contains("快船"));
    }

    class TestClsList {
        String name;
        int id;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    @Test
    public void testClsList() {
        List<TestClsList> arr = new ArrayList<>();
        TestClsList ele = new TestClsList();
        arr.add(ele);
        ele.setName("a");
        ele.setId(1);
        System.out.println(arr.get(0).getName() + "\t" + arr.get(0).getId());

        System.out.println("ns".startsWith("n"));
    }
}
