import data.dal.IProductBatchDAO;
import data.dal.IRoleDAO;
import data.dal.ProductBatchDAO;
import data.dal.RoleDAO;
import data.dto.DALException;
import data.dto.ProductBatchDTO;
import data.dto.RoleDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class DALTestProductBatch {

    /*IProductBatchDAO productBatchDAO = new ProductBatchDAO();

    @Test
    public void testCreateProductBatch() throws DALException {

        String dateString = "2019-05-08";
        String date = dateString;
        ProductBatchDTO testProductBatch = new ProductBatchDTO(Date.valueOf(dateString),25, 2);

        productBatchDAO.createProductBatch(testProductBatch);
        ProductBatchDTO receivedProductBatch = productBatchDAO.getProductBatch(1);
        int actual = receivedProductBatch.getProductBatchAmount();
        int expected = testProductBatch.getProductBatchAmount();
        assertEquals(expected, actual);

    }
    */
}
