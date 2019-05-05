package data.dto;

public class ProductOrderDTO {

    private int productOrderId;
    private String productOrderName;
    //private int productId;
    private int productOrderDate;

    public ProductOrderDTO(int productOrderId, String productOrderName, int productOrderDate){
        this.productOrderId = productOrderId;
        this.productOrderName = productOrderName;
        this.productOrderDate = productOrderDate;
    }

    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getProductOrderName(){
        return productOrderName;
    }

    public void setProductOrderName(String productOrderName) {
        this.productOrderName = productOrderName;
    }

    public int getProductOrderDate() {
        return productOrderDate;
    }

    public void setProductOrderDate(int productOrderDate) {
        this.productOrderDate = productOrderDate;
    }

    @Override
    public String toString() {
        return "ProductOrderDTO [productOrderId = " + productOrderId + ", productOrderName = " + productOrderName + ", productOrderDate = " + productOrderDate + "]";
    }

    // mangler status
}
