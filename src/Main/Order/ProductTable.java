package Main.Order;

public class ProductTable {

    String prodIDCol, prodNameCol, prodPriceCol;

    public ProductTable(String prodIDCol, String prodNameCol, String prodPriceCol) {
        this.prodIDCol = prodIDCol;
        this.prodNameCol = prodNameCol;
        this.prodPriceCol = prodPriceCol;
    }

    public String getProdIDCol() {
        return prodIDCol;
    }

    public void setProdIDCol(String prodIDCol) {
        this.prodIDCol = prodIDCol;
    }

    public String getProdNameCol() {
        return prodNameCol;
    }

    public void setProdNameCol(String prodNameCol) {
        this.prodNameCol = prodNameCol;
    }

    public String getProdPriceCol() {
        return prodPriceCol;
    }

    public void setProdPriceCol(String prodPriceCol) {
        this.prodPriceCol = prodPriceCol;
    }
}
