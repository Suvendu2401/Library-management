package com.epam.learn.microservices.library_service.repository;

import com.epam.learn.microservices.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long>
{
    List<Library> findByUsername(String username);
}
