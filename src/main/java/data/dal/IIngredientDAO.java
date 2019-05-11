package data.dal;

import data.dto.DALException;
import data.dto.IngredientDTO;

import java.io.Serializable;
import java.util.List;

public interface IIngredientDAO extends Serializable {
    void createIngredient(IngredientDTO ingredient) throws DALException;

    IngredientDTO getIngredient(int ingredientId) throws DALException;

    IngredientDTO getIngredientName(int ingredientId) throws DALException;

    List<IngredientDTO> getUserList() throws DALException;

    void updateIngredientName(IngredientDTO ingredient) throws DALException;

    void deleteIngredient(int ingredientId) throws DALException;
}
