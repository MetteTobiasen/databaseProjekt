package data.dto;

public class ProductBatchDTO {

    private int productBatchId;
    private int productBatchAmount;
    private int expirationDate;
    //private int productId;
    //private int recipeId;

    public ProductBatchDTO(int productBatchId, int productBatchAmount, int expirationDate){
        this.productBatchId = productBatchId;
        this.productBatchAmount = productBatchAmount;
        this.expirationDate = expirationDate;
    }

    public int getProductBatchId(){
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId){
        this.productBatchId = productBatchId;
    }

    public int getProductBatchAmount(){
        return productBatchAmount;
    }

    public void setProductBatchAmount(int productBatchAmount){
        this.productBatchAmount = productBatchAmount;
    }

    public int getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate){
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "ProductBatchDTO [productBatchId = " + productBatchId + ", productBatchAmount = " + productBatchAmount + " expirationDate = " + expirationDate + "]";
    }

    // manlger order status
}
