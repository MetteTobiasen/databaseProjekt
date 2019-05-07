package data.dto;

import java.util.Date;

public class ProductBatchDTO {

    private int productBatchId;
    private int productBatchAmount;
    private Date expirationDate;
    private int recipeId;

    public ProductBatchDTO(int productBatchId, int productBatchAmount, Date expirationDate, int recipeId){
        this.productBatchId = productBatchId;
        this.productBatchAmount = productBatchAmount;
        this.expirationDate = expirationDate;
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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String toString() {
        return "ProductBatchDTO [productBatchId = " + productBatchId + ", productBatchAmount = " + productBatchAmount + ", expirationDate = " + expirationDate + "]";
    }

    // manlger order status
}
