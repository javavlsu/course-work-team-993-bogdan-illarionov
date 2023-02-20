package com.company.dao;

import java.sql.*;
import java.util.ArrayList;

public class DAO implements IHistoryDAO {

    private static DAO _instance;
    private Connection _connetction;

    public DAO() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        try {
            _connetction = DriverManager.getConnection("jdbc:postgresql://localhost:5432/import","postgres", "admin");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DAO getInstance() throws SQLException, ClassNotFoundException {
        if (_instance == null)
            _instance = new DAO();
        return _instance;
    }

    public ArrayList<History> getHistories() throws SQLException {
        var list = new ArrayList<History>();
        var statement = _connetction.createStatement();
        int rows = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM histories");
            while(resultSet.next()){
                long externalId = resultSet.getLong(1);
                short internalId = resultSet.getShort(2);
                //Date date = resultSet.getDate(3);
                String metadata = resultSet.getString(4);

                var history = new History(externalId, internalId, metadata);
                list.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void Add(History history) throws SQLException {
        var statement = _connetction.createStatement();
        int rows = 0;
        try {
            var sql = "INSERT INTO histories(external_id, provider_id, product_metadata) " +
                  String.format("VALUES (%d,%d,'%s')",
                          history.getExternalId(),
                          history.getProviderId(),
                          history.getProductMetadata());

            rows = statement.executeUpdate(sql);
            System.out.printf("Added %d rows", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Update(History history) throws SQLException {
        var statement = _connetction.createStatement();
        int rows = 0;
        try {
            var sql = "UPDATE histories SET " +
                String.format("product_metadata = '%s' " +
                              "WHERE external_id = %d AND provider_id = %d",
                        history.getProductMetadata(),
                        history.getExternalId(),
                        history.getProviderId());

            rows = statement.executeUpdate(sql);
            System.out.printf("Updated %d rows", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(History history) throws SQLException {
        var statement = _connetction.createStatement();
        int rows = 0;
        try {
            var sql = "DELETE FROM histories " +
                String.format("WHERE external_id = %d AND provider_id = %d",
                        history.getExternalId(),
                        history.getProviderId());

            rows = statement.executeUpdate(sql);
            System.out.printf("deleted %d rows", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}