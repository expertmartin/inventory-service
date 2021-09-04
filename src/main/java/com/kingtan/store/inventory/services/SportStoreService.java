package com.kingtan.store.inventory.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kingtan.store.inventory.domain.Product;
import com.kingtan.store.inventory.exception.ProductNotFoundException;
import com.kingtan.util.JsonUtil;


@Component
public class SportStoreService {
	private static final Logger log = LoggerFactory.getLogger(SportStoreService.class);
	private String data = "{\"products\": ["
			+ "{ \"id\": 1, \"name\": \"Kayak\", \"category\": \"Watersports\", \"price\": 275 },"
			+ "{ \"id\": 2, \"name\": \"Lifejacket\", \"category\": \"Watersports\", \"price\": 48.95 },"
			+ "{ \"id\": 3, \"name\": \"Soccer Ball\", \"category\": \"Soccer\", \"price\": 19.50 },"
			+ "{ \"id\": 4, \"name\": \"Corner Flags\", \"category\": \"Soccer\", \"price\": 34.95 },"
			+ "{ \"id\": 5, \"name\": \"Stadium\", \"category\": \"Soccer\", \"price\": 79500 },"
			+ "{ \"id\": 6, \"name\": \"Thinking Cap\", \"category\": \"Chess\", \"price\": 16 },"
			+ "{ \"id\": 7, \"name\": \"Unsteady Chair\", \"category\": \"Chess\", \"price\": 29.95 },"
			+ "{ \"id\": 8, \"name\": \"Human Chess Board\", \"category\": \"Chess\", \"price\": 75 },"
			+ "{ \"id\": 9, \"name\": \"Bling Bling King\", \"category\": \"Chess\", \"price\": 1200 }" + "]}";
	private List<Product> products;

	public SportStoreService() throws JsonParseException, JsonMappingException, IOException {
		products = JsonUtil.getMapSLProduct(data).get("products");
	}

	public String getStoreDataStr() {
		return data;
	}

	public JsonNode getStoreDataJson() throws JsonProcessingException, IOException {
		return JsonUtil.getJsonNodeFromString(data);
	}

	public List<Product> getProducts() {
		return products;
	}

	public Product getProductById(int id) {
		Optional<Product> product = products.stream().filter(p -> id == p.getId()).findAny();

		return product.orElseThrow(() -> new ProductNotFoundException("product[" + id + " not found"));
	}

	public Product updateProduct(Product product) {
		Optional<Product> productOpt = products.stream().filter(p -> product.getId() == p.getId()).findAny().map(u -> {
			u.setName(product.getName());
			u.setCategory(product.getCategory());
			u.setPrice(product.getPrice());
			return u;
		});

		return productOpt.orElseThrow(() -> new ProductNotFoundException("Update failed: " + product.getName() + " not exist."));
	}
	
	public Product addProduct(Product product) {
//		the while does not work because s is final and cannot be updated
//		final int s = products.size();
//		while (products.stream().anyMatch(p -> s == p.getId())) {
//			s++;
//		}
		if (product == null) {
			throw  new ProductNotFoundException("Create failed: no product received.");
		}
		List<Integer> ids = products.stream().map(p -> new Integer(p.getId())).collect(toList());
		Integer s = products.size();
		while (ids.contains(s)) {
			s++;
		}
		product.setId(s);
		products.add(product);
		return product;
	}

	public Product deleteProduct(int id) {
		log.info("SportStoreService.delectProduct(), product ID:{}", id);
		Optional<Product> productOpt = products.stream().filter(p -> id == p.getId()).findAny();
		Product product = productOpt.orElseThrow(() -> new ProductNotFoundException("Delete failed: product " + id + " not exist."));
		log.info("SportStoreService.delectProduct(), product deleted:{}", product);
//		return product;
		
		boolean success = products.removeIf(p -> id == p.getId());
		
		return product;
	}

}
