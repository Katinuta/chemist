package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.ProducerDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Producer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProducerDAOImpl extends ProducerDAO {

    private static final String SQL_SELECT_ALL_PRODUCERS="SELECT p_producer_id,p_name FROM chemist.producer " +
            "ORDER BY p_name";

    @Override
    public ArrayList<Producer> findAll() throws DAOException {
        PreparedStatement statement=null;
        ArrayList<Producer> producers=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SQL_SELECT_ALL_PRODUCERS);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                Producer producer=new Producer();
                producer.setProducerId(result.getLong("p_producer_id"));
                producer.setName(result.getString("p_name"));
                producers.add(producer);
            }
            if(producers.isEmpty()){
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }

        return producers;
    }

    @Override
    public Producer findById(long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Producer entity) throws DAOException {

    }

    @Override
    public void update(Producer entity) throws DAOException {

    }
}
