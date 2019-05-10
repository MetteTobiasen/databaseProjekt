package data.dto;

public class IngredientDTO {

    private int ingredientId;
    private String ingredientName;


    public IngredientDTO(int recipeId,String ressourceName){
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;


    }

    public String getRessourceName() {
        return ingredientName;
    }

    public void setRessourceName(String ingredientName) {
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
        return "IngredientDTO [ingredientId = " + ingredientId +  " ingredientName = " + ingredientId + "]";
    }

}
