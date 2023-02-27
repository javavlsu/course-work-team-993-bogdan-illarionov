package com.company.dao;

import java.util.List;

public interface DAOClass<TEntity> {

    public void create(TEntity entity);

    public void update(TEntity entity);

    public void delete(long id);

    public TEntity getById(long id);

    public List<TEntity> getAll();
}
