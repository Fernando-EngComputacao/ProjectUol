package code.font.store.controller.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.store.controller.dto.ProductDto;
import code.font.store.controller.form.ProductForm;
import code.font.store.modelo.Product;
import code.font.store.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository produtoRepository;
	
	//Get
	@Override
	public Page<ProductDto> list(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return ProductDto.converterPage(produtoRepository.findAll(pageable).getContent()); 
	}

	//Get
	@Override
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new ProductDto(produtoRepository.findById(id).orElseThrow()));
	}

	//Get
	@Override
	public Page<ProductDto> search(String max_price, String min_price, String q) {
//		Pageable pageable = PageRequest.of(page, size);
		List<Product> products = produtoRepository.findMinMax(
				Double.parseDouble(max_price), 
				Double.parseDouble(min_price), 
				Integer.parseInt(q));
		return ProductDto.converterPage(products);
	}

	//Post
	@Override
	public ResponseEntity<ProductDto> creat(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		Product produto = form.converter();
		produtoRepository.save(produto);

		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(produto));
	}

	//Put
	@Override
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody @Valid ProductForm form){
		return ResponseEntity.ok(new ProductDto((Product)form.newDate(id, produtoRepository)));
	}
	
	//Delete
	@Override
	public ResponseEntity<ProductDto> delete(@PathVariable Integer id) {
		Optional<Product> optional = produtoRepository.findById(id);
 		
		if (optional.isPresent()) 
			produtoRepository.deleteById(id);
		
		return ResponseEntity.ok(new ProductDto(optional.get()));
		
		
	}
	
	
}
