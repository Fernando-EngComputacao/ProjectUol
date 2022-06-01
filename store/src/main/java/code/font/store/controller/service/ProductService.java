package code.font.store.controller.service;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.store.controller.dto.ProductDto;
import code.font.store.controller.form.ProductForm;

public interface ProductService {
	
	//Get
	//Page<Product> list(Pageable page);
	Page<ProductDto> list(int page, int size);
	ResponseEntity<ProductDto> findById(@PathVariable Integer id);
	Page<ProductDto> search(@PathVariable String max_price,@PathVariable String min_price,@PathVariable String q);
	//Post
	ResponseEntity<ProductDto> creat(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder);
	//Put
	ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody @Valid ProductForm form);
	//Delete
	ResponseEntity<ProductDto> delete(@PathVariable Integer id);
}
