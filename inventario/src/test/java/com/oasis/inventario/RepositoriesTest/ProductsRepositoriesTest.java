package com.oasis.inventario.RepositoriesTest;

import com.oasis.inventario.DTO.ProductsDTO;
import com.oasis.inventario.models.Products;
import com.oasis.inventario.repositores.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductsRepositoriesTest {
    @Autowired
    ProductsRepository productsRepository;

    @Test
    public void existProducts(){
        List<Products> products = productsRepository.findAll();
        assertThat(products, is(not(empty())));
    }

    @Test
    public void priceDiferentTo0(){
        List<Products> products = productsRepository.findAll();
        assertThat(products,hasItem(hasProperty("price",is(greaterThanOrEqualTo(0.0)))));
    }

    @Test
    public void quantityDiferentTo0(){
        List<Products> products = productsRepository.findAll();
        assertThat(products,hasItem(hasProperty("quantity",is(greaterThanOrEqualTo(0)))));
    }

    @Test
    public void existProductsDTO(){
        List<ProductsDTO> products = productsRepository.findAll().stream().map(ProductsDTO::new).collect(toList());
        assertThat(products, is(not(empty())));
    }

//    @Test
//    public void priceDiferentTo0DTO(){
//        List<ProductsDTO> products = productsRepository.findAll();
//        assertThat(products,hasItem(hasProperty("price",is(greaterThanOrEqualTo(0.0)))));
//    }
//
//    @Test
//    public void quantityDiferentTo0DTO(){
//        List<ProductsDTO> products = productsRepository.findAll();
//        assertThat(products,hasItem(hasProperty("quantity",is(greaterThanOrEqualTo(0)))));
//    }

}
