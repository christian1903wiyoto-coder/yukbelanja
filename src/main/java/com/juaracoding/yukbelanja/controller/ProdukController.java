package com.juaracoding.yukbelanja.controller;

import com.juaracoding.yukbelanja.dto.request.ProdukRequest;
import com.juaracoding.yukbelanja.dto.response.ApiResponse;
import com.juaracoding.yukbelanja.dto.response.ProdukResponse;
import com.juaracoding.yukbelanja.service.ProdukService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/produk")
@RequiredArgsConstructor
public class ProdukController {

    private final ProdukService produkService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProdukResponse>>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProdukResponse> products = produkService.findAll(search, pageable);

        ApiResponse<Page<ProdukResponse>> response = ApiResponse.<Page<ProdukResponse>>builder()
                .success(true)
                .message("Data produk berhasil diambil")
                .data(products)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProdukResponse>> getById(@PathVariable Long id) {
        ProdukResponse product = produkService.findById(id);
        ApiResponse<ProdukResponse> response = ApiResponse.<ProdukResponse>builder()
                .success(true)
                .message("Data produk berhasil ditemukan")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProdukResponse>> create(@Valid @RequestBody ProdukRequest request) {
        ProdukResponse product = produkService.create(request);
        ApiResponse<ProdukResponse> response = ApiResponse.<ProdukResponse>builder()
                .success(true)
                .message("Produk berhasil dibuat")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProdukResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProdukRequest request) {
        ProdukResponse product = produkService.update(id, request);
        ApiResponse<ProdukResponse> response = ApiResponse.<ProdukResponse>builder()
                .success(true)
                .message("Produk berhasil diupdate")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        produkService.delete(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Produk berhasil dihapus")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
