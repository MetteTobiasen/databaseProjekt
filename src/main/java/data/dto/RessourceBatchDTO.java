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

    public RessourceBatchDTO(int ressourceBatchAmount, int isRest, String producerName, int ingredientId){
        this.ressourceBatchAmount = ressourceBatchAmount;
        this.isRest = isRest;
        this.producerName = producerName;
        this.ingredientId = ingredientId;
    }

    public RessourceBatchDTO(int ressourceBatchAmount, int isRest){
        this.ressourceBatchAmount = ressourceBatchAmount;
        this.isRest = isRest;
    }

    public RessourceBatchDTO(int ressourceBatchId, int ressourceBatchAmount, int isRest){
        this.ressourceBatchId = ressourceBatchId;
        this.ressourceBatchAmount = ressourceBatchAmount;
        this.isRest = isRest;
    }

    public int getRessourceBatchId(){
        return ressourceBatchId;
    }

    public void setRessourceBatchId(int ressourceBatchId){
        this.ressourceBatchId = ressourceBatchId;
    }

    public int getRessourceBatchAmount(){
        return ressourceBatchAmount;
    }

    public void setRessourceBatchAmount(int ressourceBatchAmount){
        this.ressourceBatchAmount = ressourceBatchAmount;
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
        return "RessourceBatchDTO [ressourceBatchId = " + ressourceBatchId + ", ressourceBatchAmount = " + ressourceBatchAmount + " isRest = " + isRest + ", producerName = " + producerName + ", ingredientId = " + ingredientId + "]";
    }

    //mangler dato/is rest status
}

