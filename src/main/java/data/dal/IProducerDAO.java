package data.dal;

import data.dto.DALException;
import data.dto.ProducerDTO;

import java.util.List;

public interface IProducerDAO {
    void createProducer(ProducerDTO producer) throws DALException;

    ProducerDTO getProducer(String producerName) throws DALException;

    List<ProducerDTO> getProducerList() throws DALException;

    void updateProducer(ProducerDTO producer) throws DALException;

    void deleteProducer(int producerName) throws DALException;
}
