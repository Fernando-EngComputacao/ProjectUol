package code.font.project.repository;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import code.font.project.model.Product;

@DataJpaTest
public class TestRepository {

	@Autowired
	private ProductRepository repository;
	
//	@Autowired
//	private ProductService service;
	
	@Test
	public void testFindAllElementsFromProduct() {
		List<Product> products = repository.findAll();
		assertNotNull(products);
		assertTrue(!products.isEmpty());
	}
	
	@Test
	public void testFindProductById() {
		Optional<Product> product = repository.findById(1);
		assertNotNull(product.get());
		
	}
	
	@Test
	public void testCreateProduct() {
		Product product = repository.save(new Product("Café",1.2,"Goiano"));
		assertNotNull(product);
		assertTrue(product.getId() > 0);
	}	
}