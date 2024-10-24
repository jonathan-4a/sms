package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Personnel {

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);

    }

    public void loginAdmin(Scanner scanner, List < Staff > staffs) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (email.equals(this.email) && password.equals(this.password)) {
            System.out.println("Logged in Successfuly!!");
            adminMenuHandler(scanner, staffs);
        } else {
            System.out.println("Incorrect email or passowrd!");
        }
    }

    public AdminMenu printAdminMenu(Scanner scanner) {
        int input;

        while (true) {
            System.out.println("\n===========================\n" +
                "ADMIN MENU\n" +
                "---------------------------\n" +
                "1. Approve Staff\n" +
                "2. Revoke Staff\n" +
                "3. View Staff\n" +
                "4. Back\n" +
                "5. Exit\n" +
                "===========================\n");

            System.out.print("Enter your choice: ");
            input = scanner.nextInt();
            scanner.nextLine();

            if (input >= 1 && input <= 5) {
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }

        AdminMenu choice;

        if (input == 1) {
            choice = AdminMenu.APPROVE_STAFF;
        } else if (input == 2) {
            choice = AdminMenu.REVOKE_STAFF;
        } else if (input == 3) {
            choice = AdminMenu.VIEW_APPROVED_STAFF;
        } else if (input == 4) {
            choice = AdminMenu.BACK;
        } else if (input == 5) {
            choice = AdminMenu.EXIT;
        } else {
            choice = AdminMenu.NONE;
        }

        return choice;
    }

    public void adminMenuHandler(Scanner scanner, List < Staff > staffs) {
        while (true) {
            AdminMenu choice = printAdminMenu(scanner);
            switch (choice) {
                case APPROVE_STAFF:
                    approveStaff(scanner, staffs);
                    break;
                case REVOKE_STAFF:
                    revokeStaff(scanner, staffs);
                    break;
                case VIEW_APPROVED_STAFF:
                    viewStaff(staffs);
                    break;
                case BACK:
                    return;
                case EXIT:
                    System.exit(0);
                default:
                    System.out.println("");
                    break;
            }
        }
    }



    public static Admin getAdmin() {
        try {
            List < String > adminData = rw.readCSV("./file/admin.csv").get(0);
            Admin admin = new Admin(
                adminData.get(1),
                adminData.get(2),
                adminData.get(3),
                adminData.get(4));
            return admin;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("null");
            return null;
        }
    }

    public void approveStaff(Scanner scanner, List < Staff > staffs) {
        staffHelper(scanner, staffs, true);
        System.out.println("Selected Account Approved!!!");
    }


    public void revokeStaff(Scanner scanner, List < Staff > staffs) {
        staffHelper(scanner, staffs, false);
        System.out.println("Selected Account Revoked!!!");
    }

    public void viewStaff(List < Staff > staffs) {
        System.out.print("\n" + Staff.getHeader());
        for (int i = 0; i < staffs.size(); i++) {
            Staff staff = staffs.get(i);
            System.out.print(Integer.toString(i + 1) + ". " + staff);
        }
    }

    private void staffHelper(Scanner scanner, List< Staff > staffs, boolean approve) {
        viewStaff(staffs);
        System.out.print("\n\nEnter Id of Staff: ");
        String id = scanner.nextLine();
        Staff staff = staffs.get(Integer.parseInt(id) - 1);
        staff.isVerified = approve;

        List < List < String >> rowData = new ArrayList < > ();
        for (Staff s: staffs) {
            rowData.add(s.staffToList());
        }

        try {
            rw.writeAll(Staff.filePath, rowData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}