package data.dal;

import data.dto.DALException;
import data.dto.ProductBatchDTO;

import java.io.Serializable;
import java.util.List;

public interface IProductBatchDAO extends Serializable {
    ProductBatchDTO getProductBatch(int productBatchId) throws DALException;

    List<ProductBatchDTO> getProductBatchList() throws DALException;

    void createProductBatch(ProductBatchDTO productBatch) throws DALException;

    void updateProductBatchAmount(ProductBatchDTO productBatch) throws DALException;

    void deleteProductBatch(int productBatchId) throws DALException;
}
