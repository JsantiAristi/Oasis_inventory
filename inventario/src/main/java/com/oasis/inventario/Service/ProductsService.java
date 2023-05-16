package com.oasis.inventario.Service;

import com.oasis.inventario.DTO.ProductsDTO;
import com.oasis.inventario.models.Products;
import java.util.List;

public interface ProductsService {
    void saveProduct(Products products);
    List<ProductsDTO> getProducts();
    ProductsDTO getProduct(Long id);
}
