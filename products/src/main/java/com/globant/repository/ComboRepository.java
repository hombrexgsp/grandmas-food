package com.globant.repository;

import com.globant.domain.ComboEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComboRepository extends MongoRepository<ComboEntity, ObjectId> {

    @Override
    Optional<ComboEntity> findById(ObjectId objectId);

    @Query("{'uuid': ?0}")
    Optional<ComboEntity> findByUuid(UUID uuid);

    @Query("{'$text': {'$search': ?0}}")
    List<ComboEntity> findByFantasyName(String text);

    @Query("{'uuid': ?0}")
    @Update("{'$set': ?1}")
    void updateByUuid(UUID uuid, ComboEntity comboEntity);


    @Override
    List<ComboEntity> findAll();
}
