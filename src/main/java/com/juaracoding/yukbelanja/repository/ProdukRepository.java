package com.juaracoding.yukbelanja.repository;

import com.juaracoding.yukbelanja.model.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<Produk, Long> {
    Page<Produk> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
