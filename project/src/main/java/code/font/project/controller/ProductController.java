package code.font.project.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.project.controller.dto.ProductDto;
import code.font.project.controller.form.ProductForm;
import code.font.project.controller.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

	
	ProductService productService; 
	
	@Autowired	
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public Page<ProductDto> list(@PageableDefault(page = 0, size = 7) Pageable pageable){
		return productService.list(pageable);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findByid(@PathVariable Integer id){
		return productService.findById(id);
	}
	@GetMapping("/search")
	public Page<ProductDto> search(
			@RequestParam(required = false, defaultValue = "10000") String max_price, 
			@RequestParam(required = false, defaultValue = "0") String min_price, 
			@RequestParam(required = false, defaultValue = "5") String q){
			
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
