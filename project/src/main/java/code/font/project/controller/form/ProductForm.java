package code.font.project.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import code.font.project.model.Product;
import code.font.project.repository.ProductRepository;

public class ProductForm {

	@NotNull @NotEmpty @Length(min = 4)
	private String name;
	@NotNull
	private Double price;
	@NotNull @NotEmpty @Length(min = 4)
	private String description;
	
	
	//Methods
	public Product converter() {
		return new Product(name, price, description);
	}
	public Product newDate(int id, ProductRepository produtoRepository) {
		@SuppressWarnings("deprecation")
		Product product = produtoRepository.getOne(id);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setDescription(this.description);
		return product;
	}	
	
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}