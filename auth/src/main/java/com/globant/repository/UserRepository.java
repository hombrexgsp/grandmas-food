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

    //… where x.firstname like ?1
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
    List<User> findUserByNameContaining(@Param("firstName") String firstName);

    @Query("SELECT u FROM User u ORDER BY u.firstName")
    List<User> getAllUsersSorted();

    boolean existsByDocumentDocumentNumber(Long documentNumber);
    void deleteByDocumentDocumentNumber(Long documentNumber);

}
