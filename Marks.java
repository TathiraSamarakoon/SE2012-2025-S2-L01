import java.util.Scanner;

public class Marks {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;

        System.out.print("Enter number of students: ");
        n = scan.nextInt();
        int[][] marks = new int[n][3]; // [studentID][subjectID]
        boolean[] studentEntered = new boolean[n]; // tracks which students' marks have been added

        System.out.println("\nAvailable commands:");
        System.out.println("add [studentID]");
        System.out.println("update [studentID] [subjectID]");
        System.out.println("average [studentID]");
        System.out.println("average_s [subjectID]");
        System.out.println("total [studentID]");
        System.out.println("grades");
        System.out.println("exit\n");

        scan.nextLine(); // consume leftover newline

        while (true) {
            System.out.print("Enter command: ");
            String input = scan.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equalsIgnoreCase("add")) {
                if (parts.length != 2) {
                    System.out.println("Usage: add [studentID]");
                    continue;
                }

                int studentID = Integer.parseInt(parts[1]);
                if (studentID < 1 || studentID > n) {
                    System.out.println("Invalid student ID.");
                    continue;
                }

                System.out.println("Enter marks for Student " + studentID + ":");
                System.out.print("Mathematics: ");
                marks[studentID - 1][0] = scan.nextInt();
                System.out.print("Chemistry: ");
                marks[studentID - 1][1] = scan.nextInt();
                System.out.print("Physics: ");
                marks[studentID - 1][2] = scan.nextInt();
                studentEntered[studentID - 1] = true;
                scan.nextLine(); // consume newline
                System.out.println("Marks added.\n");

            } else if (parts[0].equalsIgnoreCase("update")) {
                if (parts.length != 3) {
                    System.out.println("Usage: update [studentID] [subjectID]");
                    continue;
                }

                int studentID = Integer.parseInt(parts[1]);
                int subjectID = Integer.parseInt(parts[2]);
                if (studentID < 1 || studentID > n || subjectID < 1 || subjectID > 3) {
                    System.out.println("Invalid IDs.");
                    continue;
                }

                System.out.print("Enter new mark: ");
                marks[studentID - 1][subjectID - 1] = scan.nextInt();
                scan.nextLine(); // consume newline
                System.out.println("Mark updated.\n");

            } else if (parts[0].equalsIgnoreCase("average")) {
                if (parts.length != 2) {
                    System.out.println("Usage: average [studentID]");
                    continue;
                }

                int studentID = Integer.parseInt(parts[1]);
                if (studentID < 1 || studentID > n || !studentEntered[studentID - 1]) {
                    System.out.println("Invalid student or marks not entered.");
                    continue;
                }

                int total = marks[studentID - 1][0] + marks[studentID - 1][1] + marks[studentID - 1][2];
                System.out.printf("Average for Student %d: %.2f\n\n", studentID, total / 3.0);

            } else if (parts[0].equalsIgnoreCase("average_s")) {
                if (parts.length != 2) {
                    System.out.println("Usage: average_s [subjectID]");
                    continue;
                }

                int subjectID = Integer.parseInt(parts[1]);
                if (subjectID < 1 || subjectID > 3) {
                    System.out.println("Invalid subject ID.");
                    continue;
                }

                int total = 0;
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (studentEntered[i]) {
                        total += marks[i][subjectID - 1];
                        count++;
                    }
                }

                if (count == 0) {
                    System.out.println("No marks entered yet.");
                } else {
                    System.out.printf("Average for Subject %d: %.2f\n\n", subjectID, total / (double) count);
                }

            } else if (parts[0].equalsIgnoreCase("total")) {
                if (parts.length != 2) {
                    System.out.println("Usage: total [studentID]");
                    continue;
                }

                int studentID = Integer.parseInt(parts[1]);
                if (studentID < 1 || studentID > n || !studentEntered[studentID - 1]) {
                    System.out.println("Invalid student or marks not entered.");
                    continue;
                }

                int total = marks[studentID - 1][0] + marks[studentID - 1][1] + marks[studentID - 1][2];
                System.out.println("Total marks for Student " + studentID + ": " + total + "\n");

            } else if (parts[0].equalsIgnoreCase("grades")) {
                System.out.printf("%-10s%-15s%-15s%-15s\n", "Student", "Mathematics", "Chemistry", "Physics");
                for (int i = 0; i < n; i++) {
                    if (!studentEntered[i]) continue;
                    System.out.printf("%-10s", "Student " + (i + 1));
                    for (int j = 0; j < 3; j++) {
                        String grade = getGrade(marks[i][j]);
                        System.out.printf("%-15s", grade);
                    }
                    System.out.println();
                }
                System.out.println();

            } else if (parts[0].equalsIgnoreCase("exit")) {
                System.out.println("Exiting.");
                break;

            } else {
                System.out.println("Unknown command.");
            }
        }

        scan.close();
    }

    static String getGrade(int mark) {
        if (mark >= 90) return "Grade A";
        else if (mark >= 80) return "Grade B";
        else if (mark >= 70) return "Grade C";
        else if (mark >= 60) return "Grade D";
        else return "Fail";
    }
}