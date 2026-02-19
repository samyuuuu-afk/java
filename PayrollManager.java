import java.io.*;
import java.util.*;

public class PayrollManager {

    private static final String DATA_FILE = "payroll_records.dat";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        char option;

        do {
            System.out.println("\n==== SMART PAYROLL SYSTEM ====");
            System.out.println("A - Add Staff");
            System.out.println("V - View All Records");
            System.out.println("S - Search by ID");
            System.out.println("U - Update Salary");
            System.out.println("T - Total Payroll Expense");
            System.out.println("X - Exit");
            System.out.print("Select Option: ");

            option = input.next().toUpperCase().charAt(0);

            switch (option) {
                case 'A': addStaff(); break;
                case 'V': displayAll(); break;
                case 'S': searchStaff(); break;
                case 'U': updateSalary(); break;
                case 'T': totalPayroll(); break;
                case 'X': System.out.println("System Closing..."); break;
                default: System.out.println("Invalid Option!");
            }

        } while (option != 'X');
    }

    private static void addStaff() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE, true))) {

            System.out.print("Enter ID: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Enter Name: ");
            String name = input.nextLine();

            System.out.print("Enter Base Pay: ");
            double pay = input.nextDouble();

            Staff s = new Staff(id, name, pay);

            bw.write(s.getStaffId() + "|" + s.getFullName() + "|" + s.getBasePay());
            bw.newLine();

            System.out.println("Record Saved Successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    private static void displayAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {

            String record;

            while ((record = br.readLine()) != null) {
                String[] data = record.split("\\|");

                Staff s = new Staff(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                );

                s.showDetails(true);
            }

        } catch (IOException e) {
            System.out.println("No records available.");
        }
    }

    private static void searchStaff() {
        System.out.print("Enter ID to search: ");
        int searchId = input.nextInt();

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {

            String record;

            while ((record = br.readLine()) != null) {
                String[] data = record.split("\\|");

                if (Integer.parseInt(data[0]) == searchId) {
                    Staff s = new Staff(
                            Integer.parseInt(data[0]),
                            data[1],
                            Double.parseDouble(data[2])
                    );

                    s.showDetails(true);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }

        } catch (IOException e) {
            System.out.println("File read error.");
        }
    }

    // Advanced Feature 1
    private static void updateSalary() {
        System.out.print("Enter ID to update: ");
        int updateId = input.nextInt();

        List<String> records = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {

            String record;

            while ((record = br.readLine()) != null) {
                String[] data = record.split("\\|");

                if (Integer.parseInt(data[0]) == updateId) {
                    System.out.print("Enter new salary: ");
                    double newPay = input.nextDouble();
                    record = data[0] + "|" + data[1] + "|" + newPay;
                    updated = true;
                }

                records.add(record);
            }

        } catch (IOException e) {
            System.out.println("Error updating file.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (String rec : records) {
                bw.write(rec);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving updates.");
        }

        if (updated)
            System.out.println("Salary Updated Successfully.");
        else
            System.out.println("Employee not found.");
    }

    // Advanced Feature 2
    private static void totalPayroll() {

        double total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {

            String record;

            while ((record = br.readLine()) != null) {
                String[] data = record.split("\\|");

                Staff s = new Staff(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                );

                total += s.computeGrossSalary();
            }

            System.out.println("Total Monthly Payroll Expense: " + total);

        } catch (IOException e) {
            System.out.println("Unable to calculate payroll.");
        }
    }
}
