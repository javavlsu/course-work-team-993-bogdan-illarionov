package com.company.abstractions;

import java.util.List;
import java.util.Optional;

/**
 * Описывает репозиторий сущностей.
 * @param <TEntity> тип сущности.
 */
public interface IRepository<TEntity, TKey> {

    /**
     * Возвращет все сушности.
     * @return Коллекцию типа {@link Iterable}
     */
    public List<TEntity> getAll();

    /**
     * Возвращет сушность по её идентификатору.
     * @return Сущность типа {@link TEntity}
     */
    public Optional<TEntity> getById(TKey id);

    /**
     * Добавляет сущность в хранилище.
     * @param entity сущность, которую нужно добавить.
     */
    public void add(TEntity entity);

    /**
     * Удаляет сущность из хранилища.
     * @param entity сущность, которую нужно удалить.
     */
    public void remove(TEntity entity);

    /**
     * Обновляет сущность из хранилища.
     * @param entity сущность, которую нужно обновить.
     */
    public void update(TEntity entity);
}
