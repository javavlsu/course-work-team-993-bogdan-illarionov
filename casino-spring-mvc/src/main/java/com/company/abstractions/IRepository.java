package com.company.abstractions;

/**
 * Описывает репозиторий сущностей.
 * @param <TEntity> тип сущности.
 */
public interface IRepository<TEntity, TKey> {

    /**
     * Возвращет все сушности.
     * @return Коллекцию типа {@link Iterable}
     */
    public Iterable<TEntity> getAll();

    /**
     * Возвращет сушность по её идентификатору.
     * @return Сущность типа {@link TEntity}
     */
    public TEntity getById(TKey id);

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
