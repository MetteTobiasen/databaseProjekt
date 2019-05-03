package data.dto;

public class RessourceBatchDTO {

    private int ressourceBatchId;
    private String ressourceBatchName;
    private int ressourceBatchAmount;

    public RessourceBatchDTO(int ressourceBatchId, String ressourceBatchName, int ressourceBatchAmount){
        this.ressourceBatchId = ressourceBatchId;
        this.ressourceBatchName = ressourceBatchName;
        this.ressourceBatchAmount = ressourceBatchAmount;
    }

    public int getRessourceId(){
        return ressourceBatchId;
    }

    public void setRessourceId(int ressourceId){
        this.ressourceBatchId = ressourceId;
    }

    public String getRessourceName(){
        return ressourceBatchName;
    }

    public void setRessourceName(String ressourceName){
        this.ressourceBatchName = ressourceName;
    }

    public int getRessourceAmount(){
        return ressourceBatchAmount;
    }

    public void setRessourceAmount(int ressourceAmount){
        this.ressourceBatchAmount = ressourceAmount;
    }

    public String toString(){
        return "RessourceBatchDTO [ressourceBatchId = " + ressourceBatchId + ", ressourceBatchName = " + ressourceBatchName + " resssourceBatchAmount = " + ressourceBatchAmount + "]";
    }

    //mangler dato/is rest status
}

