package com.juaracoding.yukbelanja.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdukRequest {
    @NotBlank(message = "Nama produk tidak boleh kosong")
    private String nama;

    @NotNull(message = "Harga tidak boleh kosong")
    @Min(value = 0, message = "Harga tidak boleh kurang dari 0")
    private Double harga;

    @NotNull(message = "Stok tidak boleh kosong")
    @Min(value = 0, message = "Stok tidak boleh kurang dari 0")
    private Integer stok;

    private Double rating;
    private String image;

    @NotNull(message = "ID Kategori tidak boleh kosong")
    private Long kategoriId;
}
