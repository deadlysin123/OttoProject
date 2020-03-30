package Main.Order;

public class CustOrderTable {
    String custIDCol,custFName,custLName;

    public CustOrderTable(String custIDCol, String custFName, String custLName) {
        this.custIDCol = custIDCol;
        this.custFName = custFName;
        this.custLName = custLName;
    }

    //getters and setters
    public String getCustIDCol() {
        return custIDCol;
    }

    public void setCustIDCol(String custIDCol) {
        this.custIDCol = custIDCol;
    }

    public String getCustFName() {
        return custFName;
    }

    public void setCustFName(String custFName) {
        this.custFName = custFName;
    }

    public String getCustLName() {
        return custLName;
    }

    public void setCustLName(String custLName) {
        this.custLName = custLName;
    }
}
