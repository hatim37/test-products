package com.ecom.testproducts;

import com.ecom.testproducts.entity.Product;
import com.ecom.testproducts.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
@Slf4j
@SpringBootApplication
public class TestProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestProductsApplication.class, args);
    }
    @Bean
    @Profile("!test")
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {

            byte[] Image = Files.readAllBytes(
                    Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("images/JO-Paris-2024.jpg")).toURI()));

            //on créer 3 offres au démarrage
            try {
                Product product = productRepository.findByName("Solo");
                if (product == null) {
                    Product solo = Product.builder()
                            .name("Solo")
                            .description("1 personne")
                            .price(50L)
                            .img(Image)
                            .quantity(1L)
                            .build();
                    productRepository.save(solo);
                }

            } catch (Exception e) {
                log.info(String.valueOf(e));
            }

            try {
                Product product = productRepository.findByName("Duo");
                if (product == null) {
                    Product duo = Product.builder()
                            .name("Duo")
                            .description("2 personnes")
                            .price(80L)
                            .img(Image)
                            .quantity(1L)
                            .build();
                    productRepository.save(duo);
                }

            } catch (Exception e) {
                log.info(String.valueOf(e));
            }

            try {
                Product product = productRepository.findByName("Famille");
                if (product == null) {
                    Product famille = Product.builder()
                            .name("Famille")
                            .description("4 personnes")
                            .price(120L)
                            .img(Image)
                            .quantity(1L)
                            .build();
                    productRepository.save(famille);
                }

            } catch (Exception e) {
                log.info(String.valueOf(e));
            }

        };
    }
}


