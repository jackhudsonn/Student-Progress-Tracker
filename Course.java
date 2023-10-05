import java.util.*;

public class Course {
    private static final int ASSIGNMENT_NAME_INDEX = 0;
    private static final int POINTS_RECEIVED_INDEX = 1;
    private static final int POSSIBLE_POINTS_INDEX = 2;

    private String name;
    private boolean is_weighted;
    private Map<String, Integer> weights;
    private List<String[]> assignments;
    private Scanner scanner;

    public Course(String name, boolean is_weighted) {
        this.name = name;
        this.is_weighted = is_weighted;

        weights = new HashMap<>();
        assignments = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public String get_name(String name) {
        return name;
    }

    public void change_name(String name) {
        this.name = name;
    }

    public void weight_entries() {
        // no weight entries needed
        if (!is_weighted) {
            return;
        }

        int total_weight_percentage = 0;

        while (total_weight_percentage != 100) {
            System.out.printf("\nYou have entered %d%% of %s's weights\n", total_weight_percentage, name);

            // assignment type inquiry
            String assignment_type;
            System.out.println("Enter the assignment type (i.e. Homework, Quizzes, Tests, ect):");
            assignment_type = scanner.nextLine();
            assignment_type = Character.toUpperCase(assignment_type.charAt(0)) + assignment_type.substring(1).toLowerCase();
            assignment_type = assignment_type.strip();

            // weight inquiry
            int weight;
            String weight_string;
            do {
                System.out.printf("Enter %s's weight for %s as a percentage (Ex: 75, 50, 5):\n", name, assignment_type);
                weight_string = scanner.nextLine();
                try {
                    weight = Integer.parseInt(weight_string);
                } catch (Exception e) {
                    weight = 0;
                }
            } while (weight <= 0 || weight > 100);

            total_weight_percentage += weight;
            if (weights.containsKey(assignment_type)) {
                total_weight_percentage -= weights.get(assignment_type);
            }
            weights.put(assignment_type, weight);

            // if the user inputs weights totalling over 100% --> reset
            if (total_weight_percentage > 100) {
                System.out.println("restarting...");
                total_weight_percentage = 0;
                weights.clear();
            } else if (total_weight_percentage == 100) {
                String correct_info;
                do {
                    System.out.printf("%s\nDoes this information look correct? (y/n):\n", this);
                    correct_info = scanner.nextLine();
                } while (!correct_info.equals("y") && !correct_info.equals("n"));
                if (correct_info.equals("n")) {
                    System.out.println("restarting...");
                    total_weight_percentage = 0;
                    weights.clear();
                }
            }
        }
    }

    public void add_assignment() {
        // assignment name inquiry
        System.out.printf("What %s assignment would you like to add?\n", name);
        String assignment_name = scanner.nextLine();

        // assignment score inquiry
        int possible_points;
        String possible_points_string;
        do {
            System.out.printf("How many possible points were available for the '%s' assignment?\n", name);
            possible_points_string = scanner.nextLine();
            try {
                possible_points = Integer.parseInt(possible_points_string);
            } catch (Exception e) {
                possible_points = 0;
            }
        } while (possible_points <= 0);

        // assignment score inquiry
        int earned_points;
        String earned_points_string;
        do {
            System.out.printf("How many possible points were earned for the '%s' assignment?\n", name);
            earned_points_string = scanner.nextLine();
            try {
                earned_points = Integer.parseInt(earned_points_string);
            } catch (Exception e) {
                earned_points = -1;
            }
        } while (earned_points == -1);

        // validate information
        String correct_info;
        do {
            System.out.printf("%s %s/%s\nDoes this information look correct? (y/n):\n", assignment_name, earned_points_string, possible_points_string);
            correct_info = scanner.nextLine();
        } while (!correct_info.equals("y") && !correct_info.equals("n"));
        if (correct_info.equals("n")) {
            System.out.println("Your current assignment information was not saved.");
            return;
        }

        assignments.add(new String[]{assignment_name, earned_points_string, possible_points_string});
    }

    public float get_course_average() {
        if (assignments.size() == 0) {
            return 0;
        }

        int points_earned = 0;
        int points_possible = 0;
        for (String[] assignment : assignments) {
            points_earned += Integer.parseInt(assignment[POINTS_RECEIVED_INDEX]);
            points_possible += Integer.parseInt(assignment[POSSIBLE_POINTS_INDEX]);
        }

        return (float) points_earned / points_possible;
    }

    public String toString() {
        String ret = "(";

        if (is_weighted) {
            ret += weights.toString() + ":";
        }

        int i;
        for (i = 0; i < assignments.size(); i++) {
            ret += assignments.get(i)[ASSIGNMENT_NAME_INDEX] + " " + assignments.get(i)[POINTS_RECEIVED_INDEX] + "/" + assignments.get(i)[POSSIBLE_POINTS_INDEX] + ", ";
        }

        if (i != 0) {
            return ret.substring(0, ret.length() - 2) + ")";
        }
        return ret + ")";
    }

    public static String print_courses(Map<String, Course> courses) {
        return "";
    }

    public static void main(String[] args) {

    }
}
