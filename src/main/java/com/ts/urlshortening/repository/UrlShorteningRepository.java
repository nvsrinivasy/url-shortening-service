package com.ts.urlshortening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ts.urlshortening.entity.RequestUrl;

@Repository
public interface UrlShorteningRepository extends JpaRepository<RequestUrl, Long> {
}
