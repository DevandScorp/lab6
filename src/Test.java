import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void writeData(String[] args) {

    }
    public static void main(String[] args) throws IOException {
        System.out.println("Enter your region(RU, BY, GB)");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String st = bufferedReader.readLine();
        Locale locale = Locale.getDefault();
        switch (st) {
            case "BY": {
                locale = new Locale("be", "BY");
                break;
            }
            case "RU": {
                locale = new Locale("ru", "RU");
                break;
            }
            case "GB": {
                locale = Locale.ENGLISH;
                break;
            }
            default: {
                System.out.println("Incorrect inpit");
                break;
            }
        }
        Locale.setDefault(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resource");
        Exam[] exams = {
                new Exam(Subject.MATH,"Naumovich",locale),
                new Exam(Subject.PROGRAMMING,"Rubashko",locale),
                new Exam(Subject.ENGLISH,"Shevaldysheva",locale)};
        Connector.writeExams("exams.dat",exams);
        Teacher[] teachers = {
                new Teacher("Naumovich",Subject.MATH,locale),
                new Teacher("Rubashko",Subject.PROGRAMMING,locale),
                new Teacher("Shevaldysheva",Subject.ENGLISH,locale)
        };
        Connector.writeTeachers("teachers.dat",teachers);
        Enrollee[] enrollees = {
                new Enrollee("Artem",locale),
                new Enrollee("Max",locale),
                new Enrollee("Anya",locale)
        };
        Connector.writeEnrollee("enrolees.dat",enrollees);
        Exam[] exam = Connector.readExams("exams.dat");
        Arrays.stream(exam).forEach(a -> {
            a.setBundle(bundle);
        });
        System.out.println();
        System.out.println("\t\t\t" + new String (bundle.getString("Exams").getBytes("ISO-8859-1"), "Windows-1251"));
        System.out.println();
        Arrays.stream(exam).forEach(System.out::println);
        Teacher[] teacher = Connector.readTeachers("teachers.dat");
        Arrays.stream(teacher).forEach(a -> {
            a.setBundle(bundle);
        });
        System.out.println();
        System.out.println("\t\t\t" + new String (bundle.getString("Teachers").getBytes("ISO-8859-1"), "Windows-1251"));
        System.out.println();
        Arrays.stream(teacher).forEach(System.out::println);
        Enrollee[] enrollee = Connector.readEnrollee("enrolees.dat");
        Arrays.stream(enrollee).forEach(a -> {
            a.setBundle(bundle);
        });
        System.out.println();
        Arrays.stream(teacher).forEach(t -> {
            t.setExamMark(exam);
        });
        System.out.println();
        enrollee[0].setExams(Arrays.stream(exam).collect(Collectors.toList()));
        Exam[] exams_1 = Connector.readExams("exams.dat");
        Arrays.stream(exams_1).forEach(a -> {
            a.setBundle(bundle);
        });
        Arrays.stream(teacher).forEach(t -> {
            t.setExamMark(exams_1);
        });
        enrollee[1].setExams(Arrays.stream(exams_1).collect(Collectors.toList()));

        Exam[] exams_2 = Connector.readExams("exams.dat");
        Arrays.stream(exams_2).forEach(a -> {
            a.setBundle(bundle);
        });
        Arrays.stream(teacher).forEach(t -> {
            t.setExamMark(exams_2);
        });
        enrollee[2].setExams(Arrays.stream(exams_2).collect(Collectors.toList()));
        Arrays.stream(enrollee).forEach(Enrollee::setMark);
        Arrays.stream(enrollee).forEach(System.out::println);
        System.out.println("__________________________________________________");
        Comparator<Enrollee> enrolleeComparator = (Enrollee a, Enrollee b) -> {
            if(a.getMark()<b.getMark())return 1;
            if(a.getMark()>b.getMark())return -1;
            return 0;
        };
        Arrays.sort(enrollee,enrolleeComparator);
        Faculty faculty = new Faculty(Arrays.stream(enrollee).collect(Collectors.toList()),2);
        faculty.setStudents();
        faculty.getEnrollees().forEach(System.out::println);

    }
}
