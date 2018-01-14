package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class DosageDAO extends AbstractDAO<Dosage> {
    public abstract ArrayList<String> findDosageUnits() throws DAOException;

    public abstract Dosage findIdBySizeUnit(BigDecimal dosageSize, String dosageUnit) throws DAOException;
}
