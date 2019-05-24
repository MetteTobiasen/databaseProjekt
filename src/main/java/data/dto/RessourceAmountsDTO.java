package data.dto;

public class RessourceAmountsDTO {

    private double ressourceBatchUsedAmount;
    private int ressourceBatchId;
    private int productBatchId;

    public RessourceAmountsDTO(double ressourceBatchUsedAmount, int ressourceBatchId, int productBatchId){
        this.ressourceBatchUsedAmount = ressourceBatchUsedAmount;
        this.ressourceBatchId = ressourceBatchId;
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

    public int getRessourceBatchId() {
        return ressourceBatchId;
    }

    public void setRessourceBatchId(int ressourceBatchId) {
        this.ressourceBatchId = ressourceBatchId;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    @Override
    public String toString() {
        return "RessourceAmountsDTO [ ressourceBatchUsedAmount = " + ressourceBatchUsedAmount + ", ressourceBatchId = " + ressourceBatchId + ", productBatchId = " + productBatchId + " ]";
    }

}
