package data.dto;

public class RessourceBatchDTO {

    private int ressourceBatchId;
    private int ressourceBatchAmount;
    private int isRest;
    private String producerName;
    private int ingredientId;


    public RessourceBatchDTO(int ressourceBatchId, int ressourceBatchAmount, int isRest, String producerName, int ingredientId){
        this.ressourceBatchId = ressourceBatchId;
        this.ressourceBatchAmount = ressourceBatchAmount;
        this.isRest = isRest;
        this.producerName = producerName;
        this.ingredientId = ingredientId;
    }

    public int getRessourceId(){
        return ressourceBatchId;
    }

    public void setRessourceId(int ressourceId){
        this.ressourceBatchId = ressourceId;
    }

    public int getRessourceAmount(){
        return ressourceBatchAmount;
    }

    public void setRessourceAmount(int ressourceAmount){
        this.ressourceBatchAmount = ressourceAmount;
    }

    public int getIsRest() {
        return isRest;
    }

    public void setIsRest(int isRest) {
        this.isRest = isRest;
    }

    public String getProducerName(){
        return producerName;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String toString(){
        return "RessourceBatchDTO [ressourceBatchId = " + ressourceBatchId + ", ressourceBatchAmount = " + ressourceBatchAmount + " isRest = " + isRest + ", producerName = " + producerName + ", ingredientId" + ingredientId + "]";
    }

    //mangler dato/is rest status
}

