package code.font.project.controller.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import code.font.project.controller.dto.ProductDto;
import code.font.project.controller.form.ProductForm;
import code.font.project.model.Product;
import code.font.project.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// Get
	@Override
	public Page<ProductDto> list(Pageable pageable) {
		List<Product> products = productRepository.findAll(pageable).getContent();
		return ProductDto.converterPage(products);
	}

	@Override
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new ProductDto(productRepository.findById(id).orElseThrow()));
	}

	@Override
	public List<ProductDto> search(String max_price, String min_price, String q) {
		List<Product> products = productRepository.findMinMax(Double.parseDouble(max_price),
				Double.parseDouble(min_price), Integer.parseInt(q));
		return ProductDto.converter(products);
	}

	// Post
	@Override
	public ResponseEntity<ProductDto> creat(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		Product produto = form.converter();
		productRepository.save(produto);

		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(produto));
	}

	// Put
	@Override
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody @Valid ProductForm form) {
		return ResponseEntity.ok(new ProductDto((Product) form.newDate(id, productRepository)));
	}

	// Delete
	@Override
	public ResponseEntity<ProductDto> delete(@PathVariable Integer id) {
		Optional<Product> optional = productRepository.findById(id);

		if (optional.isPresent())
			productRepository.deleteById(id);

		return ResponseEntity.ok(new ProductDto(optional.get()));

	}
}
