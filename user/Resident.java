package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import file.ReadWrite;

public class Resident extends AccountHolder {
    private String phoneNumber;
    private String healthStatus;
    private String assignedTo;
    public static ReadWrite rw = new ReadWrite();  // Assuming ReadWrite utility for CSV handling

    // Constructor with "None" as default for assignedTo
    public Resident(String firstName, String lastName, String email, String phoneNumber, String healthStatus, String assignedTo) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.healthStatus = healthStatus;
        this.assignedTo = (assignedTo == null || assignedTo.isEmpty()) ? "None" : assignedTo;
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    // Setters with "None" fallback for assignedTo
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = (assignedTo == null || assignedTo.isEmpty()) ? "None" : assignedTo;
    }

    // Convert Resident object to List<String> for CSV writing
    public List<String> toList() {
        List<String> residentData = new ArrayList<>();
        residentData.add(this.getFirstName());
        residentData.add(this.getLastName());
        residentData.add(this.getEmail());
        residentData.add(this.phoneNumber != null ? this.phoneNumber : "");
        residentData.add(this.healthStatus != null ? this.healthStatus : "");
        residentData.add(this.assignedTo);
        return residentData;
    }

    // Load residents from the CSV file, defaulting to "None" for unassigned
    public static List<Resident> loadResidents() {
        List<Resident> residents = new ArrayList<>();
        try {
            List<List<String>> rows = rw.readCSV("./file/residents.csv");

            for (List<String> row : rows) {
                String firstName = row.size() > 0 ? row.get(0) : "";
                String lastName = row.size() > 1 ? row.get(1) : "";
                String email = row.size() > 2 ? row.get(2) : "";
                String phoneNumber = row.size() > 3 ? row.get(3) : "";
                String healthStatus = row.size() > 4 ? row.get(4) : "";
                String assignedTo = row.size() > 5 && !row.get(5).isEmpty() ? row.get(5) : "None";

                Resident resident = new Resident(firstName, lastName, email, phoneNumber, healthStatus, assignedTo);
                residents.add(resident);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return residents;
    }

    // Update assigned caseworker in the CSV file, with "None" fallback
    public static void updateAssignedCaseworker(String residentEmail, String caseworkerEmail) {
        try {
            List<List<String>> rows = rw.readCSV("./file/residents.csv");

            for (List<String> row : rows) {
                // Check if this row matches the resident email
                if (row.size() > 2 && row.get(2).equals(residentEmail)) {
                    // Expand row size if necessary to include the 'assignedTo' index (index 5)
                    while (row.size() <= 5) {
                        row.add("");
                    }
                    // Update the 'assignedTo' field with the caseworker's email or "None"
                    row.set(5, (caseworkerEmail == null || caseworkerEmail.isEmpty()) ? "None" : caseworkerEmail);
                    break;
                }
            }
            // Write the updated data back to the CSV file
            rw.writeAll("./file/residents.csv", rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Format Resident details as a string for display
    @Override
    public String toString() {
        return String.format("| %-15s | %-30s | %-15s | %-12s | %-30s |",
                getFirstName() + " " + getLastName(),
                getEmail(),
                phoneNumber,
                healthStatus,
                assignedTo
        );
    }

    // Method to print the header for the table
    public static String getHeader() {
        return String.format("| %-15s | %-30s | %-15s | %-12s | %-30s |",
                "Name", "Email", "Phone Number", "Health Status", "Assigned To");
    }
}
