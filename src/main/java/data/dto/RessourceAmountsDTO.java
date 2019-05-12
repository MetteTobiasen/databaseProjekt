package data.dto;

public class RessourceAmountsDTO {

    private double ressourceBatchUsedAmount;
    private int ingredientId;
    private int productBatchId;

    public RessourceAmountsDTO(double ressourceBatchUsedAmount, int ingredientId, int productBatchId){
        this.ressourceBatchUsedAmount = ressourceBatchUsedAmount;
        this.ingredientId = ingredientId;
        this.productBatchId = productBatchId;
    }

    public RessourceAmountsDTO(int ressourceBatchUsedAmount){
        this.ressourceBatchUsedAmount = ressourceBatchUsedAmount;
    }

    public double getRessourceBatchUsedAmount() {
        return ressourceBatchUsedAmount;
    }

    public void setRessourceBatchUsedAmount(double ressourceBatchUsedAmount) {
        this.ressourceBatchUsedAmount = ressourceBatchUsedAmount;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ressourceBatchId) {
        this.ingredientId = ressourceBatchId;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    @Override
    public String toString() {
        return "RessourceAmountsDTO [ ressourceBatchUsedAmount = " + ressourceBatchUsedAmount + ", ressourceBatchId = " + ingredientId + ", productBatchId = " + productBatchId + " ]";
    }
}
