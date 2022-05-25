package code.font.store.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.font.store.modelo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM Product p WHERE p.price >= :pMin AND p.price <= :pMax ORDER BY p.price ASC LIMIT :q") 
	List<Product> findMinMax(@Param("pMax") double pMax, @Param("pMin") double pMin, @Param("q") int q);
	
}
