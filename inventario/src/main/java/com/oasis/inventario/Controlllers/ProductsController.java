package com.oasis.inventario.Controlllers;

import com.oasis.inventario.DTO.ProductsDTO;
import com.oasis.inventario.Service.ProductsService;
import com.oasis.inventario.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/api/clients/products")
    public List<ProductsDTO> getProducts(){
        return productsService.getProductsDTO();
    }

    @GetMapping("/api/clients/products/{id}")
    public ProductsDTO getProduct( @PathVariable Long id ){
        return productsService.getProductDTO(id);}

    @PostMapping("/api/clients/products")
    public ResponseEntity<Object> addProduct(@RequestBody Products products){

        String description = null;
        String image = null;
        List<ProductsDTO> productsDTO = productsService.getProductsDTO();

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

        if( !(productsDTO.stream().filter( productsDB -> productsDB.getName().equalsIgnoreCase(products.getName())).collect(toList()).size() == 0) ){
            return new ResponseEntity<>("Este nombre ya existe en otro producto" , HttpStatus.BAD_REQUEST);
        }

        Products newProduct = new Products(products.getName(),description,products.getType(),products.getQuantity(),image,products.getPrice());
        productsService.saveProduct(newProduct);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/clients/products")
    public ResponseEntity<Object> changeInfo(@RequestBody Products products){

        Products productToChange = productsService.getProduct(products.getId());

        if( productToChange != null ){
            if ( !products.getImage().equalsIgnoreCase(productToChange.getImage()) ){
                productToChange.setImage( products.getImage() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("La foto fué cambiada" , HttpStatus.ACCEPTED);
            } else if ( !products.getName().equalsIgnoreCase(productToChange.getName()) ) {
                productToChange.setName( products.getName() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("El nombre fué cambiado" , HttpStatus.ACCEPTED);
            } else if ( !products.getDescription().equalsIgnoreCase(productToChange.getDescription()) ){
                productToChange.setDescription( products.getDescription() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("La descripción fué cambiada" , HttpStatus.ACCEPTED);
            } else if ( !products.getType().equalsIgnoreCase(productToChange.getType()) ) {
                productToChange.setType( products.getType() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("El tipo fué cambiado" , HttpStatus.ACCEPTED);
            }  else if ( !(products.getPrice() == productToChange.getPrice()) ) {
                productToChange.setPrice( products.getPrice() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("El precio fué cambiado" , HttpStatus.ACCEPTED);
            } else if ( !(products.getQuantity() == productToChange.getQuantity()) ) {
                productToChange.setQuantity( products.getQuantity() );
                productsService.saveProduct( productToChange );
                return new ResponseEntity<>("La cantidad fué cambiada" , HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("No hubo cambios", HttpStatus.ACCEPTED);}
        } else {
            return new ResponseEntity<>("Este producto no existe" , HttpStatus.BAD_REQUEST);}
    }
}
