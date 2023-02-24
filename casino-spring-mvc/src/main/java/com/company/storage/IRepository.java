package com.company.storage;

/**
 * Описывает репозиторий сущностей.
 * @param <TEntity> тип сущности.
 */
public interface IRepository<TEntity> {

    /**
     * Возвращет все сушности.
     * @return Коллекцию типа {@link Iterable}
     */
    public Iterable<TEntity> getAll();

    /**
     * Возвращет сушность по её идентификатору.
     * @return Сущность типа {@link TEntity}
     */
    public TEntity getById(long id);

    /**
     * Добавляет сущность в хранилище.
     * @param entity сущность, которую нужно добавить.
     */
    public void add(TEntity entity);
}
