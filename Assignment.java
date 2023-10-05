public class Assignment {
    String course_name;
    String assignment_name;

    public Assignment(String course_name, String assignment_name) {
        this.course_name = course_name;
        this.assignment_name = assignment_name;
    }

    public String toString() {
        return assignment_name;
    }
}
