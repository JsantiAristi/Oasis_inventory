package com.oasis.inventario.repositores;

import com.oasis.inventario.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductsRepository extends JpaRepository<Products , Long> {
}
