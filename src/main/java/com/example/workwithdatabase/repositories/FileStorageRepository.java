package com.example.workwithdatabase.repositories;

import com.example.workwithdatabase.models.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Integer>, CrudRepository<FileStorage, Integer> {

    FileStorage findByHashId(String hashId);

}
