package com.cxq.factory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactoryPatternDemo {
    public void test() {
        ShapeFactory shapeFactory = new ShapeFactory();
        List<String> shapes = new ArrayList<>(Arrays.asList("CIRCLE", "RECTANGLE", "SQUARE"));
        for (String item : shapes) {
            Shape shape = shapeFactory.getShape(item);
            shape.draw();
        }
    }

    public void test2() {
        System.out.println("----------");
        try {
            Class c = Class.forName("com.cxq.factory.Circle");
            Object o = c.newInstance();
            Circle r = (Circle) o;
            r.draw();
            System.out.println(c.getName());
            System.out.println(this.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FactoryPatternDemo().test();
        new FactoryPatternDemo().test2();
    }
}
