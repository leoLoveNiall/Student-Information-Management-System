public class Curriculum
{

    private String name;
    private String CurID;
    private short credit;
    private short regularGrade;
    private short midExamGrade;
    private short finalExamGrade;

    double getAverageGrade()
    {
        return (regularGrade + finalExamGrade + midExamGrade) / 3.0;
    }

    public String getName()
    {
        return name;
    }

    public String getCurID()
    {
        return CurID;
    }

    public short getCredit()
    {
        return credit;
    }

    public short getRegularGrade()
    {
        return regularGrade;
    }

    public short getMidExamGrade()
    {
        return midExamGrade;
    }

    public short getFinalExamGrade()
    {
        return finalExamGrade;
    }


}