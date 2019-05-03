package data.dto;

public class RecipeDTO {

    private int recipeId;
    private String recipeName;
    private int endDate;

    public RecipeDTO(int recipeId, String recipeName, int endDate){
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.endDate = endDate;
    }

    public int getRecipeId(){
        return recipeId;
    }

    public void setRecipeId(int recipeId){
        this.recipeId = recipeId;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "RecipeDTO [recipeId = " + recipeId + ", recipeName = " + recipeName + " endDate = " + endDate + "]";
    }
}
