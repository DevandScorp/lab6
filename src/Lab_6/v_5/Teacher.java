package Lab_6.v_5;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Teacher implements Serializable {
    private String name;
    private Subject subject;
    private Random random;
    private Locale locale;
    private Date date;
    public transient ResourceBundle bundle = ResourceBundle.getBundle("resource");

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        try {
            return new String (bundle.getString("Subject").getBytes("ISO-8859-1"), "Windows-1251")+ " : " +
                    new String (bundle.getString(subject.toString()).getBytes("ISO-8859-1"), "Windows-1251") + "\n"+
                    "\t\t" + new String (bundle.getString("Teacher").getBytes("ISO-8859-1"), "Windows-1251")+ " : " +
                    new String (bundle.getString(name).getBytes("ISO-8859-1"), "Windows-1251")+ "\n" +
                    "\t\t" + new String (bundle.getString("Creation").getBytes("ISO-8859-1"), "Windows-1251")+ " : " + DateLocaleFormat.getTimeStyle(date,locale)
                    ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Teacher(String name, Subject subject, Locale locale) {
        this.locale = locale;
        this.name = name;
        this.subject = subject;
        this.date = new Date();
        random = new Random();
    }

    public void setExamMark(Exam[] exams){
        for(var exam:exams){
            if(exam.getSubject()==this.subject){
                exam.setMark(random.nextInt(10));
            }
        }
    }
}
