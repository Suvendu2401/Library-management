package com.prasoon.library.library_service.repository;

import com.prasoon.library.library_service.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends JpaRepository<Records, Integer> {

    List<Records> findByUserName(String userName);
    List<Records> findByBookId(int id);
}
