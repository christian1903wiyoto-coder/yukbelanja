package com.juaracoding.yukbelanja.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KategoriRequest {
    @NotBlank(message = "Nama kategori tidak boleh kosong")
    private String nama;

    private String items;
    private String icon;
}
