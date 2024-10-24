package app;

import java.util.List;
import java.util.Scanner;

import user.Admin;
import user.Staff;

class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Admin admin;
    public static List < Staff > staffs;

    public static void main(String[] args) {
        staffs = Staff.getAllStaff();

        while (true) {
            SingUpMenu choice = printMainMenu();
            switch (choice) {
                case LOGIN:
                    loginCaseHandler();
                    break;
                case SIGNUP:
                    Staff.signUpStaff(scanner);
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    return;
            }
        }

    }


    private static void loginCaseHandler() {
        while (true) {
            LogInMenu choice = printLoginMenu();
            switch (choice) {
                case ADMIN:
                    admin = Admin.getAdmin();
                    admin.loginAdmin(scanner, staffs);
                    break;
                case STAFF:
                    loginStaff();
                    break;
                case BACK:
                    return;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }


    public static void loginStaff() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (Staff staff : staffs) {
            if (staff.getEmail().equals(email) && staff.getPassword().equals(password)) {
                if (!staff.isVerified) {
                    System.out.println("You are not verified! please wait untill the Admin Approves you!");
                    return;
                }

                System.out.println("Login successful. Welcome, " + staff.getFirstName() + "!");
                staff.actionMenu(scanner);
            }
        }

        System.out.println("\nInvalid credentials. Please try again.");
    }





    private static SingUpMenu printMainMenu() {
        int input;

        while (true) {
            System.out.println("\n===========================\n" +
                "MAIN MENU\n" +
                "---------------------------\n" +
                "1. Login\n" +
                "2. Sign Up (Staff Members Only)\n" +
                "3. Exit\n" +
                "===========================\n");

            System.out.print("Enter your choice: ");

            input = scanner.nextInt();
            scanner.nextLine();

            if (input >= 1 && input <= 3) {
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }

        SingUpMenu choice = SingUpMenu.NONE;

        if (input == 1) {
            choice = SingUpMenu.LOGIN;
        } else if (input == 2) {
            choice = SingUpMenu.SIGNUP;
        } else {
            choice = SingUpMenu.EXIT;
        }

        return choice;
    }


    private static LogInMenu printLoginMenu() {
        int input;

        while (true) {
            System.out.println("\n===========================\n" +
                "LOGIN MENU\n" +
                "---------------------------\n" +
                "1. Admin\n" +
                "2. Staff\n" +
                "3. Back\n" +
                "4. Exit\n" +
                "===========================\n");

            System.out.print("Enter your choice: ");
            input = scanner.nextInt();
            scanner.nextLine();

            if (input >= 1 && input <= 4) {
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }

        LogInMenu choice = LogInMenu.NONE;

        if (input == 1) {
            choice = LogInMenu.ADMIN;
        } else if (input == 2) {
            choice = LogInMenu.STAFF;
        } else if (input == 3) {
            choice = LogInMenu.BACK;
        } else {
            choice = LogInMenu.EXIT;
        }

        return choice;
    }
}