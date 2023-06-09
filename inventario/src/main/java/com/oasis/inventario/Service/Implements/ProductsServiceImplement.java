package com.oasis.inventario.Service.Implements;

import com.oasis.inventario.DTO.ProductsDTO;
import com.oasis.inventario.Service.ProductsService;
import com.oasis.inventario.models.Products;
import com.oasis.inventario.repositores.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class ProductsServiceImplement implements ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public void saveProduct(Products products) {
      productsRepository.save(products);
    }

    @Override
    public List<ProductsDTO> getProductsDTO() {
        return productsRepository.findAll().stream().map(ProductsDTO::new).collect(toList());}

    @Override
    public ProductsDTO getProductDTO(Long id) {
        return new ProductsDTO(productsRepository.findById(id).orElse(null));
    }

    @Override
    public Products getProduct(Long id) {
        return productsRepository.findById(id).orElse(null);
    }
}
