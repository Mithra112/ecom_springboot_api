package com.kiran.ecom_project.controller;

import java.io.IOException; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kiran.ecom_project.model.Product;
import com.kiran.ecom_project.service.ProductService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
//	@GetMapping("/products")
//	public List<Product> getAllProducts()
//	{
//		return service.getAllProducts();
//	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		System.out.println("Hi");
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}
	
//	@GetMapping("/product/{id}")
//	public Product getProduct(@PathVariable int id)
//	{
//		return service.getProductById(id);
//	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id)
	{
		Product product = service.getProductById(id);
		
		if(product != null)
			return new ResponseEntity<>(product,HttpStatus.ALREADY_REPORTED);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}	
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,
										@RequestPart MultipartFile imageFile){
		try {
			Product product1 = service.addProduct(product,imageFile);
		return new ResponseEntity<>(product1,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		}
	
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
	{
		Product product = service.getProductById(productId);
		byte[] imageFile = product.getImageDate();
		
		return ResponseEntity.ok()
							.contentType(MediaType.valueOf(product.getImageType()))
							.body(imageFile);
	}
		
		@PutMapping("/product/{id}")
		public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
										@RequestPart MultipartFile imageFile){
			Product product1 = null;
			try {
				product1 = service.updateProduct(id,product,imageFile);
			}
			catch(IOException e) {
				return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
			}
			if(product1!= null)
			return new ResponseEntity<>("Updated" , HttpStatus.OK);
			else
				return new ResponseEntity<>("Updated" ,HttpStatus.BAD_REQUEST);
		}
		
		@DeleteMapping("/product/{id}")
		public ResponseEntity<String> deleteProduct(@PathVariable int id)
		{
			Product product = service.getProductById(id);
			if(product!= null)
			{
				service.deleteProduct(id);
				return new ResponseEntity<>("Deleted",HttpStatus.OK);
			}
			else 
				return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}
		
		@GetMapping("/products/search")
		public ResponseEntity<List<Product>> searchproducts(@RequestParam String keyword){
			System.out.println("Searching with " +keyword);
			List<Product> products = service.searchProducts(keyword);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
}


