/**
 * 
 */
package com.sampleapp.product.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sampleapp.product.model.Order;
import com.sampleapp.product.model.Product;
import com.sampleapp.product.model.Products;
import com.sampleapp.product.service.ProductService;

/**
 * @author user
 *
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Implementing a GET method to get the list of all the products
	 * 
	 * @return
	 */
	@GetMapping(path = "/getAllProducts", produces = "application/json")
	public ResponseEntity<Products> getAllProducts() {
		Products products = productService.getAllProducts();

		return new ResponseEntity<Products>(products, HttpStatus.OK);
	}

	/**
	 * Created a POST method to add products
	 * 
	 * @param products
	 * @return
	 */
	@PostMapping(path = "/addProducts", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addProducts(@RequestBody Products products) {

		String message = productService.addProducts(products);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	/**
	 * Created a PUT method to add products
	 * 
	 * @param products
	 * @return
	 */

	@PutMapping(path = "/updateProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {

		String message = productService.updateProduct(product);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	/**
	 * @param order
	 * @return
	 */
	@PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createOrder(@RequestBody Order order) {

		String message = productService.createOrder(order);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	/**
	 * Implementing a GET method to get the list of all the products
	 * 
	 * @return
	 */
	@GetMapping(path = "/getOrder", produces = "application/json")
	public ResponseEntity<List<Order>> getOrder(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		List<Order> orders = productService.getOrder(startDate, endDate);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
}
