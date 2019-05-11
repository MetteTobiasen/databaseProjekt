package data.dto;

public class IngredientAmountsDTO {

    private int ingredientAmount;
    private int tolerance;
    private int ingredientId;
    private int recipeId;

    public IngredientAmountsDTO(int ingredientAmount, int tolerance, int ingredientId, int recipeId){
        this.ingredientAmount = ingredientAmount;
        this.tolerance = tolerance;
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String toSting(){
        return "IngredientAmountsDTO [ ingredientAmount = " + ingredientAmount + ", tolerance = " + tolerance + ", ingredientId = " + ingredientId + ", recipeId = " + recipeId + " ]";
    }
}
