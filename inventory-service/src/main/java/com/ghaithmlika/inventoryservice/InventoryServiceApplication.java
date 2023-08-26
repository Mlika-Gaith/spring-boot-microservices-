package com.ghaithmlika.inventoryservice;

import com.ghaithmlika.inventoryservice.model.Inventory;
import com.ghaithmlika.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner loadData(InventoryRepository repo){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("test_XX_test_XX");
			inventory.setQuantity(50);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("test_XX");
			inventory1.setQuantity(0);
			repo.save(inventory1);
			repo.save(inventory);
		};
	}*/

}
