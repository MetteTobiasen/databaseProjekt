package data.dto;

import java.util.Date;

public class ProductBatchDTO {

    private int productBatchId;
    private int productId;
    private String producerName;
    private int productBatchAmount;
    private Date expirationDate;
    //private int recipeId;

    public ProductBatchDTO(){
        this.productBatchId = productBatchId;
        this.productId = productId;
        this.producerName = producerName;
        this.productBatchAmount = productBatchAmount;
        this.expirationDate = expirationDate;
    }

    public int getProductBatchId(){
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId){
        this.productBatchId = productBatchId;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProducerName(){
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public int getProductBatchAmount(){
        return productBatchAmount;
    }

    public void setProductBatchAmount(int productBatchAmount){
        this.productBatchAmount = productBatchAmount;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    public String toString() {
        return "ProductBatchDTO [productBatchId = " + productBatchId + ", productId = " + productId + ", producerName = " + producerName + ", productBatchAmount = " + productBatchAmount + ", expirationDate = " + expirationDate + "]";
    }

    // manlger order status
}
