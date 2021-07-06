package DataClassAsset;

import java.util.ArrayList;

public abstract class Student {
    /**
     * 这是基础的学生抽象类，包括五个基本参数
     *
     * @param name 姓名
     * @param ID 学号
     * @param gender 性别
     * @param major 专业及班级
     * @param dorm 寝室号
     *
     */
    private String name;
    private String ID;
    private String gender;
    private String major;
    private String dorm;
    //以下是学士（本科）、硕士、博士的 final int 代号
    public static final byte BA = 0;
    public static final byte MA = 1;
    public static final byte DO = 2;
    //学生类使用 ArrayList<Grade> 存储学生成绩表
    public ArrayList<Grade> gradeArrayList = new ArrayList<>();

    public Student(String name, String ID, String gender, String major, String dorm) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.major = major;
        this.dorm = dorm;
    }

    public Student() {
    }

    public byte getTag() {
        return -1;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getGender() {
        return gender;
    }

    public String getMajor() {
        return major;
    }

    public String getDorm() {
        return dorm;
    }

    public String getTutor() {
        return null;
    }

    public String getLab() {
        return null;
    }

    public String getVali(){
        return "";
    }

    
    @Override
    public String toString() {
        return "DataAsset.Student{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", gender='" + gender + '\'' +
                ", major='" + major + '\'' +
                ", dorm='" + dorm + '\'' +
                ", gradeArrayList=" + gradeArrayList +
                '}';
    }
}

