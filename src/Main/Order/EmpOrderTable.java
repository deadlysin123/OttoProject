package Main.Order;

public class EmpOrderTable {
    String empIDCol, empFName, empLName;

    public EmpOrderTable(String empIDCol, String empFName, String empLName) {
        this.empIDCol = empIDCol;
        this.empFName = empFName;
        this.empLName = empLName;
    }

    //Getters and Setters
    public String getEmpIDCol() {
        return empIDCol;
    }
    public void setEmpIDCol(String empIDCol) {
        this.empIDCol = empIDCol;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }


}
