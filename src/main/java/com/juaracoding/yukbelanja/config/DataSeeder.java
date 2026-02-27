package com.juaracoding.yukbelanja.config;

import com.juaracoding.yukbelanja.model.Kategori;
import com.juaracoding.yukbelanja.model.Produk;
import com.juaracoding.yukbelanja.repository.KategoriRepository;
import com.juaracoding.yukbelanja.repository.ProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final KategoriRepository kategoriRepository;
    private final ProdukRepository produkRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (kategoriRepository.count() == 0) {
            seedCategories();
        }
        if (produkRepository.count() == 0) {
            seedProducts();
        }
    }

    private void seedCategories() {
        List<Kategori> categories = Arrays.asList(
                Kategori.builder().nama("Fashion").items("2.4k items").icon("shirt").build(),
                Kategori.builder().nama("Electronics").items("1.1k items").icon("monitor").build(),
                Kategori.builder().nama("Home & Living").items("850 items").icon("sofa").build(),
                Kategori.builder().nama("Beauty").items("3.1k items").icon("sparkles").build(),
                Kategori.builder().nama("Sports").items("640 items").icon("dumbbell").build(),
                Kategori.builder().nama("Groceries").items("Harian Segar").icon("shopping-basket").build());
        kategoriRepository.saveAll(categories);
        System.out.println("Kategori seeder executed successfully!");
    }

    private void seedProducts() {
        Kategori electronics = kategoriRepository.findByNamaContainingIgnoreCase("Electronics", null)
                .getContent().stream().findFirst()
                .orElse(null);

        if (electronics != null) {
            List<Produk> products = Arrays.asList(
                    Produk.builder()
                            .nama("Bose QuietComfort 45")
                            .harga(329.0)
                            .rating(4.7)
                            .stok(50)
                            .image("https://images.unsplash.com/photo-1546435770-a3e426bf472b?q=80&w=865&auto=format&fit=crop")
                            .kategori(electronics)
                            .build(),
                    Produk.builder()
                            .nama("Apple AirPods Max")
                            .harga(549.0)
                            .rating(4.8)
                            .stok(30)
                            .image("https://images.unsplash.com/photo-1613040809024-b4ef7ba99bc3?auto=format&fit=crop&q=80&w=400")
                            .kategori(electronics)
                            .build(),
                    Produk.builder()
                            .nama("Sennheiser Momentum 4")
                            .harga(299.0)
                            .rating(4.6)
                            .stok(40)
                            .image("https://images.unsplash.com/photo-1590658268037-6bf12165a8df?auto=format&fit=crop&q=80&w=400")
                            .kategori(electronics)
                            .build(),
                    Produk.builder()
                            .nama("JBL Live 660NC")
                            .harga(199.0)
                            .rating(4.5)
                            .stok(100)
                            .image("https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?auto=format&fit=crop&q=80&w=400")
                            .kategori(electronics)
                            .build());
            produkRepository.saveAll(products);
            System.out.println("Produk seeder executed successfully!");
        }
    }
}
