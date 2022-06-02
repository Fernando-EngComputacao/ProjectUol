package code.font.project.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


import code.font.project.model.Product;

public class ProductDto {

	private Integer id;
	private String name;
	private Double price;
	private String description;
	
	//Constructor
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
	}

	//Methods
	public static Page<ProductDto> converter(List<Product> product) {
		return new PageImpl<ProductDto>(product.stream().map(ProductDto::new).collect(Collectors.toList()));
	}
//	public static List<ProductDto> converter(List<Product> product) {
//		return (product.stream().map(ProductDto::new).collect(Collectors.toList()));
//	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
