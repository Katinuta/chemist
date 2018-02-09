package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Dosage;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * The Class DosageDAO.
 */
public abstract class DosageDAO extends AbstractDAO<Dosage> {

    /**
     * Find dosage units.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<String> findDosageUnits() throws DAOException;

    /**
     * Find id by size unit.
     *
     * @param dosageSize the dosage size
     * @param dosageUnit the dosage unit
     * @return the dosage
     * @throws DAOException the DAO exception
     */
    public abstract Dosage findIdBySizeUnit(BigDecimal dosageSize, String dosageUnit) throws DAOException;
}
