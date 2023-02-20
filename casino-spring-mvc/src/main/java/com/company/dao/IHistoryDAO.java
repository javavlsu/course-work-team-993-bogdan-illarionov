package com.company.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IHistoryDAO {
    public ArrayList<History> getHistories() throws SQLException;
    public void Add(History history) throws SQLException;
    public void Update(History history) throws SQLException;
    public void Delete(History history) throws SQLException;
}