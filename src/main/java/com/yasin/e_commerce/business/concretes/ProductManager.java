package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.ProductService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.ProductDao;
import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {
	
	private ProductDao productDao;
	
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		
		return new SuccessDataResult<List<Product>>(productDao.findAll(),"Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(Product product) {
		System.out.println("product = " + product.getProductName());
		System.out.println("product suppliers = " + product.getSuppliers());
		this.productDao.save(product);
		return new SuccessResult("urun basariyla eklendi");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		
		return new SuccessDataResult<List<ProductWithCategoryDto>>(productDao.getProductWithCategoryDetails(), 
				"Filtreli Ürünler başarıyla listelendi");
	}

	@Override
	public DataResult<List<ProductWithBrandDto>> findAllByProductName(String productName) {
		return new SuccessDataResult<List<ProductWithBrandDto>>
		(productDao.findProductsByBrandName(productName),productName + "markasına ait");
	}

}
