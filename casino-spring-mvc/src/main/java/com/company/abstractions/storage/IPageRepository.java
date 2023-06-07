package com.company.abstractions.storage;

import java.util.List;

public interface IPageRepository<TEntity> {
    List<TEntity> getByParams(int quantity, int skipCount);
}
