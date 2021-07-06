package DataClassAsset;

public class Doctor extends Master {
    private String lab;

    public Doctor(String name, String ID, String gender, String major, String dorm, String tutor, String lab) {
        super(name, ID, gender, major, dorm, tutor);
        this.lab = lab;
    }

    //对抽象方法的具体化
    public String getLab() {
        return lab;
    }

    public String getTutor() {
        return super.getTutor();
    }

    @Override
    public byte getTag() {
        return DO;
    }

    @Override
    public String getVali() {
        return "博士";
    }
}
