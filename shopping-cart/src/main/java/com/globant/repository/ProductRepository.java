package com.globant.repository;

import com.globant.domain.combo.ComboEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<ComboEntity, ObjectId> {
    Optional<ComboEntity> findByUuid(UUID uuid);
}
