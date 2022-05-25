package code.font.store.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.store.controller.dto.ProductDto;
import code.font.store.controller.form.ProductForm;
import code.font.store.controller.service.ProductService;
import code.font.store.modelo.Product;
import code.font.store.repository.ProductRepository;
import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDto> list() {
		return productService.list();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return productService.findById(id);
	}
	
			
	@GetMapping("/search")
	public List<ProductDto> search(String max_price, String min_price, String q) {
		return productService.search(max_price, min_price, q);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> creat(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		return productService.creat(form, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody @Valid ProductForm form){
		return productService.update(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> delete(@PathVariable Integer id) {
		return productService.delete(id);
	}
}
