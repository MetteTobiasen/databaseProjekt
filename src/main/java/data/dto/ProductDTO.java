package data.dto;

public class ProductDTO {

    private int productId;
    private String productName;
    private int productAmount;
    //private int ressourceBatchId;
    //private int recipeId;

    public ProductDTO(){
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
    }

    /*
    public ProductDTO(int productId, String productName, int productAmount, int ressourceBatchId, int recipeId){
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.ressourceBatchId = ressourceBatchId;
        this.recipeId = recipeId;
    }
    */

    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public int getProductAmount(){
        return productAmount;
    }

    public void setProductAmount(int productAmount){
        this.productAmount = productAmount;
    }

    /*
    public int getRessourceBatchId() {
        return ressourceBatchId;
    }

    public void setRessourceBatchId(int ressourceBatchId) {
        this.ressourceBatchId = ressourceBatchId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    */

    @Override
    public String toString() {
        return "ProductDTO [productId = " + productId + ", productName = " + productName + " productAmount = " + productAmount + "]";
    }



}
