import data.dal.ProducerDAO;
import data.dto.DALException;
import data.dto.ProducerDTO;

public class MainTestProducer {

    public static void main(String[] args) throws DALException {
        ProducerDAO producerDAO = new ProducerDAO();

        ProducerDTO producer = new ProducerDTO("Medicin AS", "med@mail.com", 705, 78668488);

        producerDAO.createProducer(producer);
        //System.out.println(producerDAO.getProducer("NOVO AS".toString()));

        //producerDAO.updateProducer(producer);

        //System.out.println(producerDAO.getProducerList());

        //producerDAO.deleteProducer("NOVO AS");




    }
}
