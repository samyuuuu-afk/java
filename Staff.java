
public class Staff {

    private int staffId;
    private String fullName;
    private double basePay;

    // Default Constructor
    public Staff() {
    }

    // Parameterized Constructor
    public Staff(int staffId, String fullName, double basePay) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.basePay = basePay;
    }

    // Getter Methods
    public int getStaffId() {
        return staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBasePay() {
        return basePay;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    // Salary Calculations
    public double computeHouseAllowance() {
        return basePay * 0.20;
    }

    public double computeDearnessPay() {
        return basePay * 0.10;
    }

    public double computeGrossSalary() {
        return basePay + computeHouseAllowance() + computeDearnessPay();
    }

    // Method Overloading
    public void showDetails() {
        System.out.println(staffId + " | " + fullName + " | " + basePay);
    }

    public void showDetails(boolean detailed) {
        if (detailed) {
            System.out.println("\n------ Employee Pay Slip ------");
            System.out.println("ID        : " + staffId);
            System.out.println("Name      : " + fullName);
            System.out.println("Base Pay  : " + basePay);
            System.out.println("HRA (20%) : " + computeHouseAllowance());
            System.out.println("DA (10%)  : " + computeDearnessPay());
            System.out.println("Gross Pay : " + computeGrossSalary());
            System.out.println("--------------------------------");
        }
    }
}
