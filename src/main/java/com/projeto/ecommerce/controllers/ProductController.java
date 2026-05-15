package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.requests.ProductRequestDTO;
import com.projeto.ecommerce.responses.ProductResponseDTO;
import com.projeto.ecommerce.services.PhotoService;
import com.projeto.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private final PhotoService photoService;

    public ProductController(ProductService productService, PhotoService photoService) {
        this.productService = productService;
        this.photoService = photoService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam MultipartFile photo) throws IOException {

        String pathPhoto = photoService.savePhoto(photo);
        ProductRequestDTO prodReq = new ProductRequestDTO(name, description, price, pathPhoto);
        ProductResponseDTO prodRes = productService.createProduct(prodReq);
        return ResponseEntity.ok(prodRes);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<ProductResponseDTO> showById(@PathVariable UUID id) {
        ProductResponseDTO productDTO = productService.showProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/show/all")
    public List<ProductResponseDTO> showAllProducts() {
        return productService.showAllProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @PathVariable UUID id, @RequestBody ProductRequestDTO prodReq) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, prodReq);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("produto deletado com sucesso!");
    }
}
