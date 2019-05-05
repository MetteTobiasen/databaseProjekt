package data.dto;

public class IngridientDTO {

    private int ingridientId;
    private String ingridientName;
    private int ingridientAmountInMg;
    //private int ressourceId;
    //private int recipeId;

    public IngridientDTO(int ingridientId, int ingridientAmountInMg){
        this.ingridientId = ingridientId;
        this.ingridientAmountInMg = ingridientAmountInMg;
    }

    public int getIngridientId(){ return ingridientId;
    }

    public void setIngridientId(int ingridientId) {
        this.ingridientId = ingridientId;
    }

    public String getIngridientName(){
        return ingridientName;
    }

    public void setIngridientName(String ingridientName) {
        this.ingridientName = ingridientName;
    }

    public int getIngridientAmountInMg() {
        return ingridientAmountInMg;
    }

    public void setIngridientAmountInMg(int ingridientAmountInMg) {
        this.ingridientAmountInMg = ingridientAmountInMg;
    }

    @Override
    public String toString() {
        return "IngridientDTO [ingridientId = " + ingridientId + ", ingridientName = " + ingridientName + " ingridientAmountInMg = " + ingridientAmountInMg + "]";
    }

}
