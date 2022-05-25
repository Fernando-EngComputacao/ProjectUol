package code.font.store.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import code.font.store.modelo.Product;

public class ProductDto {

	private Integer id;
	private String name;
	private Double price;
	private String description;
	
	//Constructor
	public ProductDto(Product produto) {
		this.id = produto.getId(); 
		this.name = produto.getname();
		this.price = produto.getprice();
		this.description = produto.getdescription();
	}
	public ProductDto() {}
	
	//Methods
	public static List<ProductDto> converter(Optional<Product> products){
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	public static List<ProductDto> converter(List<Product> products){
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public Double getprice() {
		return price;
	}
	public void setprice(Double price) {
		this.price = price;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	

	
}
