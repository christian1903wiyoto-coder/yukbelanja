package com.juaracoding.yukbelanja.service;

import com.juaracoding.yukbelanja.dto.request.KategoriRequest;
import com.juaracoding.yukbelanja.dto.response.KategoriResponse;
import com.juaracoding.yukbelanja.exception.ResourceNotFoundException;
import com.juaracoding.yukbelanja.model.Kategori;
import com.juaracoding.yukbelanja.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KategoriService {

    private final KategoriRepository kategoriRepository;

    public Page<KategoriResponse> findAll(String search, Pageable pageable) {
        Page<Kategori> categories;
        if (search != null && !search.isEmpty()) {
            categories = kategoriRepository.findByNamaContainingIgnoreCase(search, pageable);
        } else {
            categories = kategoriRepository.findAll(pageable);
        }
        return categories.map(this::mapToResponse);
    }

    public KategoriResponse findById(Long id) {
        Kategori kategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan dengan id: " + id));
        return mapToResponse(kategori);
    }

    @Transactional
    public KategoriResponse create(KategoriRequest request) {
        Kategori kategori = Kategori.builder()
                .nama(request.getNama())
                .items(request.getItems())
                .icon(request.getIcon())
                .build();
        return mapToResponse(kategoriRepository.save(kategori));
    }

    @Transactional
    public KategoriResponse update(Long id, KategoriRequest request) {
        Kategori kategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan dengan id: " + id));
        kategori.setNama(request.getNama());
        kategori.setItems(request.getItems());
        kategori.setIcon(request.getIcon());
        return mapToResponse(kategoriRepository.save(kategori));
    }

    @Transactional
    public void delete(Long id) {
        Kategori kategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori tidak ditemukan dengan id: " + id));
        kategoriRepository.delete(kategori);
    }

    private KategoriResponse mapToResponse(Kategori kategori) {
        return KategoriResponse.builder()
                .id(kategori.getId())
                .nama(kategori.getNama())
                .items(kategori.getItems())
                .icon(kategori.getIcon())
                .createdAt(kategori.getCreatedAt())
                .updatedAt(kategori.getUpdatedAt())
                .build();
    }
}
