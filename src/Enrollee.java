import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Enrollee implements Serializable {
    private String name;
    private List<Exam> exams;
    private double mark;
    private Locale locale;
    private Date date;
    public String getName() {
        return name;
    }
    transient public ResourceBundle bundle = ResourceBundle.getBundle("resource");
    public void setName(String name) {
        this.name = name;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        try {
            return "\n" + new String (bundle.getString("enrollee").getBytes("ISO-8859-1"), "Windows-1251") + "\n"+
                    "\t\t" + new String (bundle.getString(name).getBytes("ISO-8859-1"), "Windows-1251") + "\n"+
                    "\t\t" + exams +"\n"+
                    "\t\t" + new String (bundle.getString("Mark").getBytes("ISO-8859-1"), "Windows-1251") +":" + mark + "\n"+
                    "\t\t" + new String (bundle.getString("Creation").getBytes("ISO-8859-1"), "Windows-1251")+" : " +
                    "\t\t"
                    + DateLocaleFormat.getTimeStyle(date,locale)+"]\n";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getMark() {
        return mark;
    }

    public Enrollee(String name,Locale locale) {
        this.name = name;
        this.locale = locale;
        this.date = new Date();
    }

    public void setMark() {
        int sum = 0;
        for(var exam : exams){
            sum+=exam.getMark();
        }
        this.mark = (double)sum/exams.size();
    }



    public Enrollee(String name, List<Exam> exams) {
        this.name = name;
        this.exams = exams;
        this.date = new Date();
        mark = 0;
    }
}
