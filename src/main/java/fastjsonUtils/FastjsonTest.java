package fastjsonUtils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

/**
 * Created by cxq on 2018/6/26.
 */
public class FastjsonTest {
    public static void main(String[] args) {
        test1();
        test2("./data/word_cut_dict/word2idx.json");
    }

    public static void test1() {
        String jsonStr = "{'k1':1,'k2':2}";
        Map map1 = JSON.parseObject(jsonStr, Map.class);
        System.out.println(map1);
        System.out.println(map1.get("k1"));
    }

    public static void test2(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            String text = IOUtils.toString(inputStream, "utf8");
            Map map2 = JSON.parseObject(text, Map.class);
            System.out.println(map2);
            System.out.println(map2.get("--NULL--"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
