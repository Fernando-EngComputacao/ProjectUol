package code.font.store.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import code.font.store.modelo.Product;
import code.font.store.repository.ProductRepository;

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
		Product product = produtoRepository.getById(id);
		product.setname(this.name);
		product.setprice(this.price);
		product.setdescription(this.description);
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
