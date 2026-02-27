package com.juaracoding.yukbelanja.controller;

import com.juaracoding.yukbelanja.dto.request.KategoriRequest;
import com.juaracoding.yukbelanja.dto.response.ApiResponse;
import com.juaracoding.yukbelanja.dto.response.KategoriResponse;
import com.juaracoding.yukbelanja.service.KategoriService;
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
@RequestMapping("/api/kategori")
@RequiredArgsConstructor
public class KategoriController {

    private final KategoriService kategoriService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<KategoriResponse>>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<KategoriResponse> categories = kategoriService.findAll(search, pageable);

        ApiResponse<Page<KategoriResponse>> response = ApiResponse.<Page<KategoriResponse>>builder()
                .success(true)
                .message("Data kategori berhasil diambil")
                .data(categories)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KategoriResponse>> getById(@PathVariable Long id) {
        KategoriResponse category = kategoriService.findById(id);
        ApiResponse<KategoriResponse> response = ApiResponse.<KategoriResponse>builder()
                .success(true)
                .message("Data kategori berhasil ditemukan")
                .data(category)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<KategoriResponse>> create(@Valid @RequestBody KategoriRequest request) {
        KategoriResponse category = kategoriService.create(request);
        ApiResponse<KategoriResponse> response = ApiResponse.<KategoriResponse>builder()
                .success(true)
                .message("Kategori berhasil dibuat")
                .data(category)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<KategoriResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody KategoriRequest request) {
        KategoriResponse category = kategoriService.update(id, request);
        ApiResponse<KategoriResponse> response = ApiResponse.<KategoriResponse>builder()
                .success(true)
                .message("Kategori berhasil diupdate")
                .data(category)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        kategoriService.delete(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Kategori berhasil dihapus")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
