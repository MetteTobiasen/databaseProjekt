package data.dto;

public class IngredientDTO {

    private String ressourceName;
    private int recipeId;

    public IngredientDTO(String ressourceName, int recipeId){
        this.ressourceName = ressourceName;
        this.recipeId = recipeId;

    }

    public String getRessourceName() {
        return ressourceName;
    }

    public void setRessourceName(String ressourceName) {
        this.ressourceName = ressourceName;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "IngredientDTO [ressourceName = " + ressourceName +  " recipeId = " + recipeId + "]";
    }

}
