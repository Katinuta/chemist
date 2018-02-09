package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Class MedicineDAO.
 */
public abstract class MedicineDAO extends AbstractDAO<Medicine> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<Medicine> findByName(String name) throws DAOException;

    /**
     * Find all.
     *
     * @param begin the begin
     * @param end the end
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<Medicine> findAll(int begin, int end) throws DAOException;

    /**
     * Find all by relevance.
     *
     * @param begin the begin
     * @param end the end
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<Medicine> findAllByRelevance (int begin, int end) throws DAOException;

    /**
     * Gets the count by name.
     *
     * @return the count by name
     * @throws DAOException the DAO exception
     */
    public abstract int getCountByName()throws DAOException;

    /**
     * Gets the count by name by relevance.
     *
     * @return the count by name by relevance
     * @throws DAOException the DAO exception
     */
    public abstract int getCountByNameByRelevance() throws DAOException;

    /**
     * Gets the balance by id.
     *
     * @param id the id
     * @return the balance by id
     * @throws DAOException the DAO exception
     */
    public abstract int getBalanceById(long id)throws DAOException;

    /**
     * Find by id edit.
     *
     * @param id the id
     * @return the medicine
     * @throws DAOException the DAO exception
     */
    public abstract Medicine findByIdEdit(long id) throws DAOException;

    /**
     * Find units in pack.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<String> findUnitsInPack() throws DAOException;

    /**
     * Find all id.
     *
     * @return the hash set
     * @throws DAOException the DAO exception
     */
    public abstract HashSet<Long> findAllId()throws DAOException;

    /**
     * Find by prescrip need.
     *
     * @param isNeedPrescrip the is need prescrip
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract  ArrayList<Medicine> findByPrescripNeed(boolean isNeedPrescrip) throws DAOException;

}
