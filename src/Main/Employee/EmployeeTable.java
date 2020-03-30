package Main.Employee;

public class EmployeeTable {

    String empIDCol,empFNameCol,empLNameCol,empPassCol,empStreetCol,empCityCol,empStateCol,empZipCol,empPhoneCol;



    // Constructor for Employee Table
    public EmployeeTable(String empIDCol, String empFNameCol, String empLNameCol, String empPassCol, String empStreetCol, String empCityCol, String empStateCol, String empZipCol, String empPhoneCol) {
        this.empIDCol = empIDCol;
        this.empFNameCol = empFNameCol;
        this.empLNameCol = empLNameCol;
        this.empPassCol = empPassCol;
        this.empStreetCol = empStreetCol;
        this.empCityCol = empCityCol;
        this.empStateCol = empStateCol;
        this.empZipCol = empZipCol;
        this.empPhoneCol = empPhoneCol;
    }

    //Getters and Setters
    public String getEmpIDCol() {
        return empIDCol;
    }

    public void setEmpIDCol(String empIDCol) {
        this.empIDCol = empIDCol;
    }

    public String getEmpFNameCol() {
        return empFNameCol;
    }

    public void setEmpFNameCol(String empFNameCol) {
        this.empFNameCol = empFNameCol;
    }

    public String getEmpLNameCol() {
        return empLNameCol;
    }

    public void setEmpLNameCol(String empLNameCol) {
        this.empLNameCol = empLNameCol;
    }

    public String getEmpPassCol() {
        return empPassCol;
    }

    public void setEmpPassCol(String empPassCol) {
        this.empPassCol = empPassCol;
    }

    public String getEmpStreetCol() {
        return empStreetCol;
    }

    public void setEmpStreetCol(String empStreetCol) {
        this.empStreetCol = empStreetCol;
    }

    public String getEmpCityCol() {
        return empCityCol;
    }

    public void setEmpCityCol(String empCityCol) {
        this.empCityCol = empCityCol;
    }

    public String getEmpStateCol() {
        return empStateCol;
    }

    public void setEmpStateCol(String empStateCol) {
        this.empStateCol = empStateCol;
    }

    public String getEmpZipCol() {
        return empZipCol;
    }

    public void setEmpZipCol(String empZipCol) {
        this.empZipCol = empZipCol;
    }

    public String getEmpPhoneCol() {
        return empPhoneCol;
    }

    public void setEmpPhoneCol(String phone) {
        empPhoneCol = phone;
    }
}
