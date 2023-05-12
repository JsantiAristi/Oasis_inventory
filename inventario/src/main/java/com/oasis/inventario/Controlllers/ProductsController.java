package com.oasis.inventario.Controlllers;

import com.oasis.inventario.DTO.ProductsDTO;
import com.oasis.inventario.Service.ProductsService;
import com.oasis.inventario.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @RequestMapping("/api/clients/products")
    public List<ProductsDTO> getProducts(){
        return productsService.getProducts();
    }

    @PostMapping("/api/clients/products")
    public ResponseEntity<Object> addProduct(@RequestBody Products products){

        String description = null;
        String image = null;

        if ( products.getDescription().isBlank() ){
            description = "Sin descripción";
        } else if ( !products.getDescription().isBlank()) {
            description = products.getDescription();
        }

        if ( products.getImage().isBlank() ){
            image = "../assets/jabon.jpg";
        } else if ( !products.getImage().isBlank()) {
            image = products.getImage();
        }

        Products newproduct = new Products(products.getName(),description,products.getType(),products.getQuantity(),image,products.getPrice());
        productsService.saveProduct(newproduct);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
