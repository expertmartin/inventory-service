package com.kingtan.store.inventory.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.kingtan.store.inventory.domain.Product;
import com.kingtan.store.inventory.exception.ProductNotFoundException;
import com.kingtan.store.inventory.exception.StoreServerException;
import com.kingtan.store.inventory.exception.Error;
import com.kingtan.store.inventory.services.SportStoreService;


@CrossOrigin(maxAge = 3600, origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/store")
public class SportStoreController {
	private static final Logger log = LoggerFactory.getLogger(SportStoreController.class);

	@Autowired
	private SportStoreService service;

	@GetMapping("/json")
	public JsonNode getFromWebpage() throws MalformedURLException, IOException {
		log.info("SportStoreController.getFromWebpage(), JsonNode:\n{}", service.getStoreDataJson());
		return service.getStoreDataJson();
	}

	@GetMapping("/string")
	public String getFromDatabase() throws MalformedURLException, IOException {
		log.info("SportStoreController.getFromDatabase(), string:\n{}", service.getStoreDataStr());
		return service.getStoreDataStr();
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		log.info("SportStoreController.getFromDatabase(), products list:\n{}", service.getProducts());
		return service.getProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable int id) {
		log.info("SportStoreController.getProductById()");
		return service.getProductById(id);
	}

	@PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
		log.info("SportStoreController.createProduct()， product:\n{}", product);
		service.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@PutMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody final Product product) {
		log.info("SportStoreController.updateProduct(), product:\n{}", product);
		service.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	//Error on web service: TypeNotSupportedException: Content type 'null' not supported
	// Error on Angular:  Cors Error
	// resolution: remove 'consumes = MediaType.APPLICATION_JSON_VALUE'
	@CrossOrigin(maxAge = 3600, origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/product/{id}") //, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deletProduct(@PathVariable int id) {
		log.info("SportStoreController.delectProduct(), product ID:{}", id);
		Product product = service.deleteProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Error> productNotFound(ProductNotFoundException e) {
		log.info("SportStoreController.productNotFound(), ProductNotFoundException:\n{}", e.getMessage());
		Error error = new Error(4, e.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StoreServerException.class)
	public ResponseEntity<Error> serverError(StoreServerException e) {
		log.info("SportStoreController.serverError(), StoreServerException:\n{}", e.getMessage());
		Error error = new Error(3, e.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
