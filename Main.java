import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Course> courses = new HashMap<>();
        String course_name;
        String weighted;
        Scanner s = new Scanner(System.in);

        // get courses from user
        do {
            System.out.println("\nEnter a course name, or type x to exit:");
            course_name = s.nextLine();
            if (!course_name.equals("x")) {
                System.out.printf("Does %s use weighted averages? (y/n):\n", course_name);
                do {
                    weighted = s.nextLine();
                } while (!weighted.equals("y") && !weighted.equals("n"));

                Course course = new Course(course_name, weighted.equals("y"));
                courses.put(course_name, course);
                course.weight_entries();
            }
        } while (!course_name.equals("x"));

        System.out.printf("Here are your courses\n%s\n", courses);

        // add course grades
        do {
            System.out.println("\nEnter the course name for which you'd like to enter assignment info, or type x to exit:");
            do {
                course_name = s.nextLine();
            } while (!course_name.equals("x") && !courses.containsKey(course_name));

            if (!course_name.equals("x")) {
                Course course = courses.get(course_name);
                course.add_assignment();
                courses.put(course_name, course);
            }
        } while (!course_name.equals("x"));

        System.out.printf("Here are your courses\n%s\n", courses);
    }
}
