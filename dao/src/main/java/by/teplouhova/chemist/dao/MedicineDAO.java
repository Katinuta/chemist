package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MedicineDAO extends AbstractDAO<Medicine> {
    public abstract ArrayList<Medicine> findByName(String name) throws DAOException;
    public abstract ArrayList<Medicine> findAll(int begin, int end) throws DAOException;
    public abstract ArrayList<Medicine> findAllByRelevance (int begin, int end) throws DAOException;
    public abstract int getCountByName()throws DAOException;
    public abstract int getCountByNameByRelevance() throws DAOException;
    public abstract int getBalanceById(long id)throws DAOException;
    public abstract Medicine findByIdEdit(long id) throws DAOException;
    public abstract ArrayList<String> findUnitsInPack() throws DAOException;
    public abstract HashSet<Long> findAllId()throws DAOException;
    public abstract  ArrayList<Medicine> findByPrescripNeed(boolean isNeedPrescrip) throws DAOException;

}
