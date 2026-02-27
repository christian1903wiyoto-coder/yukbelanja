package com.juaracoding.yukbelanja.service;

import com.juaracoding.yukbelanja.dto.request.ProdukRequest;
import com.juaracoding.yukbelanja.dto.response.ProdukResponse;
import com.juaracoding.yukbelanja.exception.ResourceNotFoundException;
import com.juaracoding.yukbelanja.model.Kategori;
import com.juaracoding.yukbelanja.model.Produk;
import com.juaracoding.yukbelanja.repository.KategoriRepository;
import com.juaracoding.yukbelanja.repository.ProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdukService {

    private final ProdukRepository produkRepository;
    private final KategoriRepository kategoriRepository;

    public Page<ProdukResponse> findAll(String search, Pageable pageable) {
        Page<Produk> products;
        if (search != null && !search.isEmpty()) {
            products = produkRepository.findByNamaContainingIgnoreCase(search, pageable);
        } else {
            products = produkRepository.findAll(pageable);
        }
        return products.map(this::mapToResponse);
    }

    public ProdukResponse findById(Long id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk tidak ditemukan dengan id: " + id));
        return mapToResponse(produk);
    }

    @Transactional
    public ProdukResponse create(ProdukRequest request) {
        Kategori kategori = kategoriRepository.findById(request.getKategoriId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Kategori tidak ditemukan dengan id: " + request.getKategoriId()));

        Produk produk = Produk.builder()
                .nama(request.getNama())
                .harga(request.getHarga())
                .stok(request.getStok())
                .rating(request.getRating())
                .image(request.getImage())
                .kategori(kategori)
                .build();
        return mapToResponse(produkRepository.save(produk));
    }

    @Transactional
    public ProdukResponse update(Long id, ProdukRequest request) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk tidak ditemukan dengan id: " + id));

        Kategori kategori = kategoriRepository.findById(request.getKategoriId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Kategori tidak ditemukan dengan id: " + request.getKategoriId()));

        produk.setNama(request.getNama());
        produk.setHarga(request.getHarga());
        produk.setStok(request.getStok());
        produk.setRating(request.getRating());
        produk.setImage(request.getImage());
        produk.setKategori(kategori);

        return mapToResponse(produkRepository.save(produk));
    }

    @Transactional
    public void delete(Long id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk tidak ditemukan dengan id: " + id));
        produkRepository.delete(produk);
    }

    private ProdukResponse mapToResponse(Produk produk) {
        Kategori kategori = produk.getKategori();

        return ProdukResponse.builder()
                .id(produk.getId())
                .nama(produk.getNama())
                .harga(produk.getHarga())
                .stok(produk.getStok())
                .rating(produk.getRating())
                .image(produk.getImage())
                .kategoriId(kategori.getId())
                .kategoriNama(kategori.getNama())
                .createdAt(produk.getCreatedAt())
                .updatedAt(produk.getUpdatedAt())
                .build();
    }
}
