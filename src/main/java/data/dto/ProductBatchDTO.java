package data.dto;

import java.util.Date;

public class ProductBatchDTO {

    private int productBatchId;
    private Date expirationDate;
    private int productBatchAmount;
    private int recipeId;

    public ProductBatchDTO(int productBatchId, Date expirationDate, int productBatchAmount, int recipeId){
        this.productBatchId = productBatchId;
        this.expirationDate = expirationDate;
        this.productBatchAmount = productBatchAmount;
        this.recipeId = recipeId;
    }

    public ProductBatchDTO() {

    }

    public int getProductBatchId(){
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId){
        this.productBatchId = productBatchId;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    public int getProductBatchAmount(){
        return productBatchAmount;
    }

    public void setProductBatchAmount(int productBatchAmount){
        this.productBatchAmount = productBatchAmount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String toString() {
        return "ProductBatchDTO [productBatchId = " + productBatchId + ", expirationDate = " + expirationDate + ", productBatchAmount = " + productBatchAmount + "]";
    }

    // manlger order status
}
