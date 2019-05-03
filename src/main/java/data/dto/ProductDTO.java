package data.dto;

public class ProductDTO {

    private int productId;
    private String productName;
    private int productAmount;
    //private int ressourceBatchId;
    //private int recipeId;

    public ProductDTO(int productId, String productName, int productAmount){
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
    }

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

    @Override
    public String toString() {
        return "ProductDTO [productId = " + productId + ", productName = " + productName + " productAmount = " + productAmount + "]";
    }

}
