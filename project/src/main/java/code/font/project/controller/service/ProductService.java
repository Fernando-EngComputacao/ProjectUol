package code.font.project.controller.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.project.controller.dto.ProductDto;
import code.font.project.controller.form.ProductForm;

public interface ProductService {

	//Get
	Page<ProductDto> list(Pageable pageable);
	ResponseEntity<ProductDto> findById(@PathVariable Integer id);
	List<ProductDto> search(String max_price, String min_price, String q);
	//Post
	ResponseEntity<ProductDto> creat(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder);
	//Put
	ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody @Valid ProductForm form);
	//Delete
	ResponseEntity<ProductDto> delete(@PathVariable Integer id);
}
