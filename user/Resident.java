package user;

import java.time.LocalDate;

public class Resident extends AccountHolder {
    // Fields
    private String phoneNumber;
    private LocalDate dob;
    private String healthStatus;
    private String gender;
    private EmergencyContact emergencyContact;

    // Constructor
    public Resident(String firstName, String lastName, String email, String phoneNumber, 
                    LocalDate dob, String healthStatus, String gender, 
                    String emergencyContactName, String emergencyContactPhone) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.healthStatus = healthStatus;
        this.gender = gender;
        this.emergencyContact = new EmergencyContact(emergencyContactName, emergencyContactPhone);
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getGender() {
        return gender;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    // Setters
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return String.format("| %-15s  %-15s  %-30s  %-15s  %-12s  %-12s  %-10s  %-15s  %-15s |\n",
                getFirstName() + " " + getLastName(), // Name
                getEmail(),                             // Email
                phoneNumber,                            // Phone Number
                dob.toString(),                         // Date of Birth
                healthStatus,                           // Health Status
                gender,                                 // Gender
                emergencyContact.getName(),             // Emergency Contact Name
                emergencyContact.getPhoneNumber()       // Emergency Contact Phone
        );
    }

    // Method to print the header for the table
    public static String getHeader() {
        return String.format("| %-15s | %-15s | %-30s | %-15s | %-12s | %-12s | %-10s | %-15s | %-15s |\n",
                "Name", "Email", "Phone Number", "Date of Birth", "Health Status", "Gender", "Emergency Contact", "Emergency Phone");
    }


    // Inner class for Emergency Contact
    public static class EmergencyContact {
        private String name;
        private String phoneNumber;

        public EmergencyContact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
