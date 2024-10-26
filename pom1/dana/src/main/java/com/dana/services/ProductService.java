package com.dana.services;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private List<ProductEntity> productList = new ArrayList<>();

    public ProductService() {
        // Productos de ejemplo
        productList.add(new ProductEntity("Lipstick", "Cosmetics", 15.99, 100));
        productList.add(new ProductEntity("Foundation", "Cosmetics", 25.99, 50));
        productList.add(new ProductEntity("Mascara", "Cosmetics", 12.99, 75));
    }

    public List<ProductEntity> getAllProducts() {
        return productList;
    }

    public Optional<ProductEntity> getProductById(UUID id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public ProductEntity createProduct(ProductEntity product) {
        productList.add(product);
        return product;
    }

    public Optional<ProductEntity> updateProduct(UUID id, ProductEntity newProductData) {
        Optional<ProductEntity> existingProduct = getProductById(id);
        existingProduct.ifPresent(product -> {
            product.setName(newProductData.getName());
            product.setCategory(newProductData.getCategory());
            product.setPrice(newProductData.getPrice());
            product.setStock(newProductData.getStock());
        });
        return existingProduct;
    }

    public boolean deleteProduct(UUID id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }
}
