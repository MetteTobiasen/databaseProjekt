package data.dto;

public class RessourceDTO {

    private int ressourceId;
    private String ressourceName;
    private int ressourceAmount;

    public RessourceDTO(int ressourceId, String ressourceName, int ressourceAmount){
        this.ressourceId = ressourceId;
        this.ressourceName = ressourceName;
        this.ressourceAmount = ressourceAmount;
    }

    public int getRessourceId(){
        return ressourceId;
    }

    public void setRessourceId(int ressourceId){
        this.ressourceId = ressourceId;
    }

    public String getRessourceName(){
        return ressourceName;
    }

    public void setRessourceName(String ressourceName){
        this.ressourceName = ressourceName;
    }

    public int getRessourceAmount(){
        return ressourceAmount;
    }

    public void setRessourceAmount(){
        this.ressourceAmount = ressourceAmount;
    }

    public String toString (){
        return "RessourceBatchDTO [ressourceId = " + ressourceId + ", ressourceName = " + ressourceName + " resssourceAmount = " + ressourceAmount + "]";
    }

    // mangler ordrestatus
}
