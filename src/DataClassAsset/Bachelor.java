package DataClassAsset;

public class Bachelor extends Student {
    public Bachelor(String name, String ID, String gender, String major, String dorm) {
        super(name, ID, gender, major, dorm);
    }

    @Override
    public byte getTag() {
        return BA;
    }

    @Override
    public String getVali() {
        return "本科";
    }
}
