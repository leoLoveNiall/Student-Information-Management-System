package DataClassAsset;

public class Doctor extends Master {
    private String lab;

    public Doctor(String name, String ID, String gender, String major, String dorm, String tutor, String lab) {
        super(name, ID, gender, major, dorm, tutor);
        this.lab = lab;
    }

    @Override
    public String getTag() {
        return "DO";
    }

    public String getTutor() {
        return super.getTutor();
    }

    public String getLab() {
        return lab;
    }
}
