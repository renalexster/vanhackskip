package com.skip.orderapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skip.orderapi.utils.JsonBean;

@Entity
@Table(name="tb_product")
public class Product extends JsonBean{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="storeId")
	@JsonIgnore
	private Store store;
	
	@Transient
	private Long storeId;
	
	private String name;
	private String description;
	private Double price;
	
	
	public Product() {
	}
	
	
	
	public Product(Store store, String name, String description, Double price) {
		super();
		this.store = store;
		this.name = name;
		this.description = description;
		this.price = price;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getStoreId() {
		return this.store.getId();
	}
	
}
