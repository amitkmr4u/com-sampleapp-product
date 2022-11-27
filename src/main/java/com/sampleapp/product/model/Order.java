/**
 * 
 */
package com.sampleapp.product.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author user
 *
 */

public class Order {
	private int id;
	private List<Product> product;
	private Date date;
	private String orderStatus;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
