import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Exam implements Serializable {
    private Subject subject;
    private String teacher;
    private int mark;
    private Date date;
    private Locale locale;
    public transient ResourceBundle bundle=ResourceBundle.getBundle("resource");
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        try {
            return "\n["+
                    new String (bundle.getString("Exam").getBytes("ISO-8859-1"), "Windows-1251")+  "\n" +
                    "\t" + new String (bundle.getString("Subject").getBytes("ISO-8859-1"), "Windows-1251")+ ":" +
                    "\t" + new String (bundle.getString(subject.toString()).getBytes("ISO-8859-1"), "Windows-1251") + "\n"+
                    "\t" + new String (bundle.getString("Teacher").getBytes("ISO-8859-1"), "Windows-1251")+ ":" +
                    "\t" + new String (bundle.getString(teacher).getBytes("ISO-8859-1"), "Windows-1251") + '\n' +
                    "\t" + new String (bundle.getString("Mark").getBytes("ISO-8859-1"), "Windows-1251")+ ":" + mark + '\n' +
                    "\t" +  new String (bundle.getString("Creation").getBytes("ISO-8859-1"), "Windows-1251")+ ":" + DateLocaleFormat.getTimeStyle(date,locale)
                    +"\n\t]";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Exam(Subject subject, String teacher,Locale locale) {
        this.locale = locale;
        this.date = new Date();
        this.subject = subject;
        this.teacher = teacher;
        mark = 0;
    }
}
