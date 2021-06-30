package DataClassAsset;

import java.util.ArrayList;

public class Student {
    //enum Degree {DataAsset.Bachelor, DataAsset.Master, DataAsset.Doctor};
    private String name;
    private String ID;
    private String gender;
    private String major;
    private String dorm;
    public ArrayList<Grade> gradeArrayList = new ArrayList<Grade>();

    public Student(String name, String ID, String gender, String major, String dorm) {
        this.name = name;
        this.ID = ID;
        this.gender = gender;
        this.major = major;
        this.dorm = dorm;
        initializeGrade();
    }

    public Student() {

    }

    public void initializeGrade() {

    }

    public String getTag() {
        return "";
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

