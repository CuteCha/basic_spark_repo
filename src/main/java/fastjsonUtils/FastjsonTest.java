package fastjsonUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

/**
 * Created by cxq on 2018/6/26.
 */
public class FastjsonTest {
    public static void main(String[] args) {
//        test1();
//        test2("./data/word_cut_dict/word2idx.json");
//        readFile1("./data/some.conf");
//        readFile2("./data/some.conf");
//        writeFile1("./data/some.conf", "./data/some.conf.bak");
        test3("./data/some.conf");
        System.out.println(Strings.repeat("-",36));
//        test3("./data/some.json");
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

    public static void test3(String filaPath){
        InputStream inputStream=null;
        try {
            inputStream = new FileInputStream(filaPath);
            String text = IOUtils.toString(inputStream, "utf8");
            System.out.println(text);
            JSONObject object = JSON.parseObject(text);
            System.out.println(object);
            System.out.println(object.get("QRratio"));
            JSONObject QRratio = JSON.parseObject(object.get("QRratio").toString());
            System.out.println(QRratio.get("x"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readFile1(String filePath){
        System.out.println("------first method-------");
        File file = new File(filePath);
        if(file.exists()){
            InputStreamReader reader;
            BufferedReader br;
            try {
                reader = new InputStreamReader(new FileInputStream(file));
                br = new BufferedReader(reader);
                String lineContent = null;
                while((lineContent = br.readLine())!=null){
                    System.out.println(lineContent);
                }
                br.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("no this file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("io exception");
                e.printStackTrace();
            }
        }
    }

    public static void readFile2(String filePath){
        System.out.println("------second method-------");
        File file = new File(filePath);
        if(file.exists()){
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String lineContent = null;
                while((lineContent = br.readLine())!=null){
                    System.out.println(lineContent);
                }
                br.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("no this file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("io exception");
                e.printStackTrace();
            }
        }
    }

    public static void writeFile1(String filePath, String outPath){
        System.out.println("------first method-------");
        File file = new File(filePath);

        StringBuffer sb= new StringBuffer("");

        if(file.exists()){
            InputStreamReader reader;
            BufferedReader br;
            try {
                reader = new InputStreamReader(new FileInputStream(file));
                br = new BufferedReader(reader);
                String lineContent = null;
                while((lineContent = br.readLine())!=null){
                    System.out.println(lineContent);
                    sb.append(lineContent.concat("\n"));
                }
                br.close();
                reader.close();

//                new OutputStreamWriter(new FileOutputStream(new File(outPath)));


                FileWriter writer = new FileWriter(outPath);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(sb.toString());
                bw.close();

            } catch (FileNotFoundException e) {
                System.out.println("no this file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("io exception");
                e.printStackTrace();
            }




        }
    }


}
