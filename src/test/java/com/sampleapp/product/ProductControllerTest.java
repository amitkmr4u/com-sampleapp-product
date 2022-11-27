package com.sampleapp.product;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleapp.product.model.Order;
import com.sampleapp.product.model.Product;
import com.sampleapp.product.model.Products;
import com.sampleapp.product.service.ProductService;

@WebMvcTest
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductService productService;
	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void testGetAllProducts() throws Exception {
		Products products = new Products();
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProductId(100);
		product.setPrice(1200);
		product.setProductCategory("cloths");
		product.setProductName("shirt");
		product.setRating("good");
		productList.add(product);
		products.setProducts(productList);

		Mockito.when(productService.getAllProducts()).thenReturn(products);
		mockMvc.perform(get("/products/getAllProducts")).andExpect(status().isOk())
				.andExpect(jsonPath("$.products", Matchers.hasSize(1)))
				.andExpect(jsonPath("$.products[0].productId", Matchers.equalTo(100)));
	}

	@Test
	void testAddProducts() throws Exception {
		Products products = new Products();
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProductId(100);
		product.setPrice(1200);
		product.setProductCategory("cloths");
		product.setProductName("shirt");
		product.setRating("good");
		productList.add(product);
		products.setProducts(productList);
		String json = mapper.writeValueAsString(products);
		Mockito.when(productService.addProducts(ArgumentMatchers.any())).thenReturn("products added successful:");
		mockMvc.perform(post("/products/addProducts").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setProductId(100);
		product.setPrice(1200);
		product.setProductCategory("cloths");
		product.setProductName("shirt");
		product.setRating("bad");
		String json = mapper.writeValueAsString(product);
		Mockito.when(productService.updateProduct(ArgumentMatchers.any())).thenReturn("products updated successful:");
		mockMvc.perform(put("/products/updateProduct").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testCreateOrder() throws Exception {

		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProductId(100);
		product.setPrice(1200);
		product.setProductCategory("cloths");
		product.setProductName("shirt");
		product.setRating("good");
		productList.add(product);
		Order order = new Order();
		order.setDate(new Date());
		order.setId(10);
		order.setEmail("abc@email.com");
		order.setOrderStatus("pending");
		order.setProduct(productList);

		String json = mapper.writeValueAsString(order);
		Mockito.when(productService.addProducts(ArgumentMatchers.any())).thenReturn("order created successful:");
		mockMvc.perform(post("/products/order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetOrder() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProductId(100);
		product.setPrice(1200);
		product.setProductCategory("cloths");
		product.setProductName("shirt");
		product.setRating("good");
		productList.add(product);
		List<Order> orderList = new ArrayList<>();
		Order order = new Order();
		order.setDate(new Date());
		order.setId(10);
		order.setEmail("abc@email.com");
		order.setOrderStatus("pending");
		order.setProduct(productList);
		orderList.add(order);

		Mockito.when(productService.getOrder(any(Date.class), any(Date.class))).thenReturn(orderList);

		mockMvc.perform(get("/products/getOrder?startDate=2022-11-20&&endDate=2022-11-30")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].email", Matchers.equalTo("abc@email.com")));
	}

}
