package com.skip.orderapi.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skip.orderapi.utils.JsonBean;

@Entity
@Table(name="tb_order")
public class Order extends JsonBean{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Timestamp date;

	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	private String deliveryAddress;
	private String contact;
	private Long storeId;
	
	@OneToMany
	private List<OrderItem> listOrderItem;

	private Double total;
	private String status;
	private Timestamp lastUpdate;
	
}
