package com.excelr.RefresherClientAppl.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.excelr.RefresherClientAppl.model.Product;

@Controller
public class ClientController {
	
	@GetMapping("/getServerGreet")
	public String getServerGreet()
	{
		String url="http://localhost:8081/greet";
		RestTemplate restTemplate=new RestTemplate();
		String result=restTemplate.getForObject(url, String.class);
		return result;
	}
	
	@GetMapping("/testclient")
	public String testclient()
	{
		return "Test Client";
	}

	@GetMapping("/getSingleProduct/{pno}")
	public String getSingleProduct(@PathVariable("pno") int pno, Model model)
	{
		String url=String.format("https://fakestoreapi.com/products/%d",pno);
		RestTemplate restTemplate=new RestTemplate();
		Product product=restTemplate.getForObject(url, Product.class);
		model.addAttribute("product",product);
		return "ViewSingleProduct";
	}
	
	
//	@GetMapping("/getAllProducts")
//	public List<Product> getAllProducts()
//	{
//		String url=String.format("https://fakestoreapi.com/products");
//		RestTemplate restTemplate=new RestTemplate();
//		List<Product> result= Arrays.asList(restTemplate.getForObject(url, Product[].class));
//		return result;
//	}
	
	//
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model)
	{
		String url=String.format("https://fakestoreapi.com/products");
		RestTemplate restTemplate=new RestTemplate();
		List<Product> products= Arrays.asList(restTemplate.getForObject(url, Product[].class));
		model.addAttribute("products",products);
		return "ViewAllProducts";
	}
}
