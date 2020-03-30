package Main.Customer;


// Customer table model
public class CustomerTable {

    String custIDCol,custFNameCol,custLNameCol,custAddressCol,custCityCol,custStateCol,custZipCol,custPhoneCol;


    public CustomerTable(String custIDCol, String custFNameCol, String custLNameCol, String custAddressCol, String custCityCol, String custStateCol, String custZipCol, String custPhoneCol) {
        this.custIDCol = custIDCol;
        this.custFNameCol = custFNameCol;
        this.custLNameCol = custLNameCol;
        this.custAddressCol = custAddressCol;
        this.custCityCol = custCityCol;
        this.custStateCol = custStateCol;
        this.custZipCol = custZipCol;
        this.custPhoneCol = custPhoneCol;
    }

    public CustomerTable() {

    }


    // getters and setters

    public String getCustIDCol() {
        return custIDCol;
    }

    public void setCustIDCol(String custIDCol) {
        this.custIDCol = custIDCol;
    }

    public String getCustFNameCol() {
        return custFNameCol;
    }

    public void setCustFNameCol(String custFNameCol) {
        this.custFNameCol = custFNameCol;
    }

    public String getCustLNameCol() {
        return custLNameCol;
    }

    public void setCustLNameCol(String custLNameCol) {
        this.custLNameCol = custLNameCol;
    }

    public String getCustAddressCol() {
        return custAddressCol;
    }

    public void setCustAddressCol(String custAddressCol) {
        this.custAddressCol = custAddressCol;
    }

    public String getCustCityCol() {
        return custCityCol;
    }

    public void setCustCityCol(String custCityCol) {
        this.custCityCol = custCityCol;
    }

    public String getCustStateCol() {
        return custStateCol;
    }

    public void setCustStateCol(String custStateCol) {
        this.custStateCol = custStateCol;
    }

    public String getCustZipCol() {
        return custZipCol;
    }

    public void setCustZipCol(String custZipCol) {
        this.custZipCol = custZipCol;
    }

    public String getCustPhoneCol() {
        return custPhoneCol;
    }

    public void setCustPhoneCol(String custPhoneCol) {
        this.custPhoneCol = custPhoneCol;
    }

}
