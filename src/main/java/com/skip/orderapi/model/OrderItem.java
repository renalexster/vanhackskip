package com.skip.orderapi.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skip.orderapi.utils.JsonBean;

@Entity
@Table(name="tb_order_item")
public class OrderItem extends JsonBean{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;
	
	private Timestamp date;

	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	private String deliveryAddress;
	private String contact;
	private Double total;
	private String status;
	private Timestamp lastUpdate;
	
}
