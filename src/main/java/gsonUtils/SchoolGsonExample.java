package gsonUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxq on 2018/9/29.
 */


public class SchoolGsonExample {

    private static Gson gsonCom=new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException {
        parseJsonFile("./data/school.json");
        System.out.println("\n\n>>>>>>\n\n");
        parseJsonLineFile("./data/school.txt");
    }

    public static void parseJsonFile(String filePath) throws IOException {

        // Get Gson object
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        String fileData = new String(Files.readAllBytes(Paths
                .get(filePath)));
        System.out.println(fileData);
        System.out.println(Strings.repeat("-", 36));

        // parse json string to object
        School school = gsonCom.fromJson(fileData, School.class);

        // print object data
        System.out.println("\n\nEmployee Object:\n\n" + school);

    }

    public static void parseJsonLineFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(reader);
                String lineContent = null;
                while ((lineContent = br.readLine()) != null) {
                    System.out.println(Strings.repeat("=", 36));
                    System.out.println(lineContent);
                    System.out.println(Strings.repeat("^", 16));
                    School school = parseSchool(lineContent);
                    System.out.println(school);
                    System.out.println(school.getId()+"\t"+school.getName());
                    System.out.println(Strings.repeat("=", 36));
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


    public static School parseSchool(String schoolStr) {
        School school = gsonCom.fromJson(schoolStr, School.class);
        return school;
    }

}
