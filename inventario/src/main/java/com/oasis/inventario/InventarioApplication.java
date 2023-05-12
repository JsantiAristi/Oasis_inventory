package com.oasis.inventario;

import com.oasis.inventario.models.Products;
import com.oasis.inventario.repositores.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ProductsRepository productsRepository) {
		return (args) -> {
			// save a couple of customers
			Products products1 = new Products("Desengrasante", "Util para grasas","Líquidos" ,2, "../assets/desengrasante.jpg",14000);
			productsRepository.save(products1);
			Products products2 = new Products("Blanqueador en gel", "Sin descripción","Líquidos",4,"../assets/jabon.jpg",20000);
			productsRepository.save(products2);
			Products products3 = new Products("Oxyclean", "Util para ropa o superficies blancas","Polvo",0,"../assets/oxyclean.jpg",16000);
			productsRepository.save(products3);
			Products products4 = new Products("Blanqueador en polvo", "Sin descripción","Polvo",2,"../assets/blanqueador_polvo.jpg",7000);
			productsRepository.save(products4);
		};
	}

}
