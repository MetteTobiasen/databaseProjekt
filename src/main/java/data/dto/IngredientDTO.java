package data.dto;

public class IngredientDTO {

    private int ingredientId;
    private String ingredientName;
    private int reoder;


    public IngredientDTO(int ingredientId, String ingredientName, int reoder){
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.reoder = reoder;
    }

    public IngredientDTO(int ingredientId, String ingredientName){
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    public IngredientDTO(String ingredientName){
        this.ingredientName = ingredientName;
    }

    public IngredientDTO(int ingredientId){
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public String toString() {
        return  " ingredientName = " + ingredientName;
    }

    public int getReoder() {
        return reoder;
    }

    public void setReoder(int reoder) {
        this.reoder = reoder;
    }
}
