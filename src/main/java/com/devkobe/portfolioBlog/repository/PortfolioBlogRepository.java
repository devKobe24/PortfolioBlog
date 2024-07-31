package com.devkobe.portfolioBlog.repository;

import com.devkobe.portfolioBlog.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioBlogRepository extends JpaRepository<Content, Long> {
}
