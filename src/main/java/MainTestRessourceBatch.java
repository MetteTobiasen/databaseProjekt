import data.dal.RessourceBatchDAO;
import data.dto.DALException;
import data.dto.RessourceBatchDTO;

public class MainTestRessourceBatch {

    public static void main(String[] args) throws DALException {

        RessourceBatchDAO ressourceBatchDAO = new RessourceBatchDAO();

        //RessourceBatchDTO ressourceBatchDTO = new RessourceBatchDTO(2, 40, 0);

        //ressourceBatchDAO.createRessourceBatchDTO(ressourceBatchDTO,2,"NOVO AS");

        //ressourceBatchDAO.deleteRessourceBatch(1);

        //System.out.println(ressourceBatchDAO.getRessourceBatchList());

        //ressourceBatchDAO.updateRessourceBatch(ressourceBatchDTO,3, "NOVO AS" );

        System.out.println(ressourceBatchDAO.getRessourceBatch(2));




    }
}
