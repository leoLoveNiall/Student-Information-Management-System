package DataClassAsset;

public class Master extends Bachelor {
    private String tutor;

    public Master(String name, String ID, String gender, String major, String dorm, String tutor) {
        super(name, ID, gender, major, dorm);
        this.tutor = tutor;
    }

    @Override
    public String getTag() {
        return "MA";
    }

    public String getTutor() {
        return tutor;
    }
}
