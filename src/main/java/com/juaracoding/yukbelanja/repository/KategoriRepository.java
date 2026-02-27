package com.juaracoding.yukbelanja.repository;

import com.juaracoding.yukbelanja.model.Kategori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    Page<Kategori> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
