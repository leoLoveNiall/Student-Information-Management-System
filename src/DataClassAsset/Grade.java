package DataClassAsset;

public class Grade {
    private String stuID;
    private String courseName;
    private String courseID;
    private byte credit;
    private int reg;
    private int mid;
    private int fin;

    public Grade(String stuID, String courseName, String courseID, byte credit, int reg, int mid, int fin) {
        this.stuID = stuID;
        this.courseName = courseName;
        this.courseID = courseID;
        this.credit = credit;
        this.reg = reg;
        this.mid = mid;
        this.fin = fin;
    }


    public String getStuID() {
        return stuID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public byte getCredit() {
        return credit;
    }

    public int getReg() {
        return reg;
    }

    public int getMid() {
        return mid;
    }

    public int getFin() {
        return fin;
    }

    public void setGrade(int reg,int mid,int fin){
        this.reg = reg;
        this.mid = mid;
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "DataAsset.Grade{" +
                "stuID='" + stuID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseID='" + courseID + '\'' +
                ", credit='" + credit + '\'' +
                ", reg=" + reg +
                ", mid=" + mid +
                ", fin=" + fin +
                '}';
    }
}
