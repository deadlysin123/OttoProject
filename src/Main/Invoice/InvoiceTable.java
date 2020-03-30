package Main.Invoice;

public class InvoiceTable {
    String invIDCol, invDateCol, invCustIDCol, invEmpIDCol, invProdIDCol, invProdPriceCol;

    public InvoiceTable(String invIDCol, String invDateCol, String invCustIDCol, String invEmpIDCol, String invProdIDCol, String invProdPriceCol) {
        this.invIDCol = invIDCol;
        this.invDateCol = invDateCol;
        this.invCustIDCol = invCustIDCol;
        this.invEmpIDCol = invEmpIDCol;
        this.invProdIDCol = invProdIDCol;
        this.invProdPriceCol = invProdPriceCol;
    }

    public String getInvIDCol() {
        return invIDCol;
    }

    public void setInvIDCol(String invIDCol) {
        this.invIDCol = invIDCol;
    }

    public String getInvDateCol() {
        return invDateCol;
    }

    public void setInvDateCol(String invDateCol) {
        this.invDateCol = invDateCol;
    }

    public String getInvCustIDCol() {
        return invCustIDCol;
    }

    public void setInvCustIDCol(String invCustIDCol) {
        this.invCustIDCol = invCustIDCol;
    }

    public String getInvEmpIDCol() {
        return invEmpIDCol;
    }

    public void setInvEmpIDCol(String invEmpIDCol) {
        this.invEmpIDCol = invEmpIDCol;
    }

    public String getInvProdIDCol() {
        return invProdIDCol;
    }

    public void setInvProdIDCol(String invProdIDCol) {
        this.invProdIDCol = invProdIDCol;
    }

    public String getInvProdPriceCol() {
        return invProdPriceCol;
    }

    public void setInvProdPriceCol(String invProdPriceCol) {
        this.invProdPriceCol = invProdPriceCol;
    }
}
