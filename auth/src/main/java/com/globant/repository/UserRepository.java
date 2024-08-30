package com.globant.repository;

import com.globant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.document.documentNumber =:documentNumber")
    Optional<User> findUserByDocumentNumber(@Param("documentNumber") Long documentNumber);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name%")
    Optional<User> findUserByNameContaining(@Param("name") String name);

    @Query("SELECT u FROM User u ORDER BY u.firstName")
    List<User> getAllUsersSorted();

    boolean existsByDocumentDocumentNumber(Long documentNumber);
    void deleteByDocumentDocumentNumber(Long documentNumber);

}
