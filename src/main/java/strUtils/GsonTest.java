package strUtils;

import com.google.gson.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxq on 2018/6/25.
 */


public class GsonTest {
    public static void main(String[] args) {
        test1("./data/some.json");
        test2("./data/word_cut_dict/word2idx.json");
        test3("./data/word_cut_dict/word2idx.json");
        test4("./data/word_cut_dict/word2idx.json");
    }

    public static void test1(String path) {
//        {
//            "title": "Mr",
//                "firstName": "Coding With Cake",
//                "age": 99,
//                "books": [
//                    "Java 101",
//                    "GSON by example",
//                    "JSON parser"
//                    ],
//            "address": {
//            "firstLine": "22 ABC street",
//            "secondLine": "London",
//            "postCode": "W1 3EE"
//        }
        Gson gson = new Gson();
        File jsonFile = Paths.get(path).toFile();
        JsonObject jsonObject = null;
        try {
            jsonObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);


            String title = jsonObject.get("title").getAsString();
            String firstName = jsonObject.get("firstName").getAsString();
            Integer age = jsonObject.get("age").getAsInt();

            JsonArray booksArray = jsonObject.getAsJsonArray("books");
            JsonObject address = jsonObject.getAsJsonObject("address");
            String firstLine = address.get("firstLine").getAsString();
            String secondLine = address.get("secondLine").getAsString();
            String postCode = address.get("postCode").getAsString();

            System.out.println("title = " + title);
            System.out.println("firstName = " + firstName);
            System.out.println("age = " + age);

            for (JsonElement book : booksArray) {
                System.out.println("book = " + book.getAsString());
            }

            System.out.println("firstLine = " + firstLine);
            System.out.println("secondLine = " + secondLine);
            System.out.println("postCode = " + postCode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test2(String path) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));


            Gson gson = new Gson();
            Object json = gson.fromJson(bufferedReader, Object.class);


            System.out.println(json.getClass());
            System.out.println(json.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test3(String path) {
        Gson gson = new Gson();
        File jsonFile = Paths.get(path).toFile();
        JsonObject jsonObject = null;
        try {
            jsonObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
            String key = "--NULL--";
            BigInteger value = jsonObject.get(key).getAsBigInteger();
            System.out.println(key.concat(" ") + value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void test4(String path) {
        Gson gson = new Gson();
        Map<String, BigInteger> map = new HashMap<String, BigInteger>();
        Map<String, BigInteger> myMap = (Map<String, BigInteger>) gson.fromJson("{'k1':1,'k2':2}", map.getClass());
        System.out.println(myMap);
        File jsonFile = Paths.get(path).toFile();
        JsonObject jsonObject = null;
        try {
            Map<String, BigInteger> map2 = new HashMap<String, BigInteger>();
            Map<String, BigInteger> myMap2 = (Map<String, BigInteger>) gson.fromJson(new FileReader(jsonFile), map2.getClass());
            System.out.println(myMap2);
            String key = "--NULL--";
            String key1 = "-NULL-";
            System.out.println(key.concat(" ") + myMap2.get(key));
            System.out.println(key1.concat(" ") + myMap2.get(key1));

            if (!myMap2.containsKey(key1)) {
                System.out.println(key1 + " is not in hashMap");
                System.out.println(key.concat(" ") + myMap2.get(key));

            }


            Gson gson2= getIntGson();
            Map<String, Long> map3 = new HashMap<>();
            Map<String, Long> myMap3 = (Map<String, Long>) gson2.fromJson(new FileReader(jsonFile), map3.getClass());

            if (!myMap2.containsKey(key1)) {
                System.out.println(key1 + " is not in hashMap");
                System.out.println(key.concat(" ") + myMap3.get(key));

            }

//            Long v=myMap3.get(key);
//            long z = (long) v;
//            System.out.println(z);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test5(){
    }

    public static Gson getIntGson() {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.intValue())
                            return new JsonPrimitive(src.longValue());
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                }).create();
        return gson;
    }
}
