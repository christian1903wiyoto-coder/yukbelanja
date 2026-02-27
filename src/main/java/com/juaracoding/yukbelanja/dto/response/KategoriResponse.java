package com.juaracoding.yukbelanja.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class KategoriResponse {
    private Long id;
    private String nama;
    private String items;
    private String icon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
