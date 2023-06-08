package com.company.storage.jpa;

import com.company.storage.models.StorageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJpaRepository extends JpaRepository<StorageUser, Long> {

    @Query("SELECT u FROM StorageUser u WHERE u.login = :name")
    public StorageUser findByUserLogin(@Param("name") String login);

/*    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.nickname = :name")
    User findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);

    @Query("SELECT id FROM User u WHERE u.email = :email")
    int calculateMaxUserId(@Param("email") String email);*/
}