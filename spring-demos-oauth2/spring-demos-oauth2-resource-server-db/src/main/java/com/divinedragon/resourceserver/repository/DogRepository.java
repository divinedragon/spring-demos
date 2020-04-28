package com.divinedragon.resourceserver.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divinedragon.resourceserver.domain.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, UUID> {

}
