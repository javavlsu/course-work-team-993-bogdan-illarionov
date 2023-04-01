package com.company.storage;

import com.company.storage.models.StorageLot;
import com.company.storage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface IUserRepository extends JpaRepository<User, Long> {

/*    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.nickname = :name")
    User findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);

    @Query("SELECT id FROM User u WHERE u.email = :email")
    int calculateMaxUserId(@Param("email") String email);*/
}