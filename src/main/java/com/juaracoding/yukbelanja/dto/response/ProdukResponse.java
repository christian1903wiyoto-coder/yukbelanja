package com.juaracoding.yukbelanja.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProdukResponse {
    private Long id;
    private String nama;
    private Double harga;
    private Integer stok;
    private Double rating;
    private String image;
    private Long kategoriId;
    private String kategoriNama;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
