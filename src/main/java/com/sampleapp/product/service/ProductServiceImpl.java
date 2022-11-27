/**
 * 
 */
package com.sampleapp.product.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleapp.product.model.Order;
import com.sampleapp.product.model.Orders;
import com.sampleapp.product.model.Product;
import com.sampleapp.product.model.Products;

/**
 * @author user
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public Products getAllProducts() {
		Products products = new Products();

		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new ClassPathResource("/products.json").getFile();

			products = mapper.readValue(file, Products.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public String addProducts(Products products) {
		String message = "products added successful:";
		try {
			File file = new ClassPathResource("/products.json").getFile();

			FileWriter fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(products);
			fileWriter.write(jsonStr);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String updateProduct(Product product) {
		String message = "products updated successful:";
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new ClassPathResource("/products.json").getFile();

			Products products = mapper.readValue(file, Products.class);
			List<Product> list = products.getProducts();

			List<Product> updatedList = list.stream().filter(pr -> pr.getProductId() == 100).map(pr -> product)
					.collect(Collectors.toList());

			FileWriter fileWriter = new FileWriter(file);
			String jsonStr = mapper.writeValueAsString(updatedList);
			fileWriter.write(jsonStr);
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String createOrder(Order order) {
		String message = "order created successful:";
		try {
			File file = new ClassPathResource("/products.json").getFile();

			FileWriter fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(order);
			fileWriter.write(jsonStr);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Order> getOrder(Date startDate, Date endDate) {
		List<Order> orders = new ArrayList<Order>();

		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new ClassPathResource("/orders.json").getFile();
			Orders order = mapper.readValue(file, Orders.class);
			List<Order> orderList = order.getOrders();
			orders = orderList.stream().filter(ord -> ord.getDate().after(startDate) && ord.getDate().before(endDate))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return orders;
	}

}
