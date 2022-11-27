package com.sampleapp.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sampleapp.product.model.Order;
import com.sampleapp.product.model.Product;
import com.sampleapp.product.model.Products;

@Service
public interface ProductService {

	Products getAllProducts();

	String addProducts(Products products);

	String updateProduct(Product product);

	String createOrder(Order order);

	List<Order> getOrder(Date startDate, Date endDate);
}
