package gsonUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by cxq on 2018/9/29.
 */


public class EmployeeGsonExample {

    private static Gson gsonCom=new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException {
        parseJsonFile("./data/employee.json");
        System.out.println("\n\n>>>>>>\n\n");
        parseJsonLineFile("./data/employee.txt");
    }

    public static void parseJsonFile(String filePath) throws IOException {
        Employee emp = createEmployee();

        // Get Gson object
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        String fileData = new String(Files.readAllBytes(Paths
                .get(filePath)));
        System.out.println(fileData);
        System.out.println(Strings.repeat("-", 36));

        // parse json string to object
        Employee emp1 = gsonCom.fromJson(fileData, Employee.class);

        // print object data
        System.out.println("\n\nEmployee Object:\n\n" + emp1 + "\n\n");

        // create JSON String from Object
        String jsonEmp = gsonCom.toJson(emp);
        System.out.print(jsonEmp);
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
                    Employee employee = parseEmployee(lineContent);
                    System.out.println(employee);
                    System.out.println(employee.getId()+"\t"+employee.getName());
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


    public static Employee parseEmployee(String employeeStr) {
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Employee emp = gson.fromJson(employeeStr, Employee.class);
        Employee emp = gsonCom.fromJson(employeeStr, Employee.class);
        return emp;
    }

    public static Employee createEmployee() {

        Employee emp = new Employee();
        emp.setId(101);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[]{123456, 987654});
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        List<String> cities = new ArrayList<String>();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);

        Map<String, String> props = new HashMap<String, String>();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);

        return emp;
    }
}
