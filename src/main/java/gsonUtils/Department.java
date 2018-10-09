package gsonUtils;

/**
 * Created by cxq on 2018/9/29.
 */


public class Department {

    private String name;
    private int studentNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "name="+getName() +  "|studentNum=" + getStudentNum();
    }

}
