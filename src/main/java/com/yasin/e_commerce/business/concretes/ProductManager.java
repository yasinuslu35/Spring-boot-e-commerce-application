package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.ProductService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.ProductDao;
import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.responses.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.responses.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {
	
	private final ProductDao productDao;
	
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public ResponseEntity<DataResult<List<Product>>> getAll() {
		
		List<Product> products = productDao.findAll();
		
		if(products.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<Product>>
					(productDao.findAll(),HttpStatus.BAD_REQUEST.value() ,"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<Product>>
				(productDao.findAll(),HttpStatus.OK.value(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<DataResult<List<Product>>> getAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<DataResult<List<Product>>> getAllSorted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Result> add(Product product) {
	    try {
	        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Marka adı boş olamaz!"));
	        }

	        // 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
	        Optional<Product> existingProductName = productDao.findByProductName
	        		(product.getProductName().trim());
	      
	        if (existingProductName.isPresent()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Bu isimde bir marka zaten mevcut"));
	        }
	        
	        productDao.save(product);
	        return ResponseEntity
	        		.status(HttpStatus.CREATED)
	        		.body(new SuccessResult(HttpStatus.CREATED.value(),
	        				"Marka başarıyla eklendi!"));

	    } catch (Exception e) {
	        // 🛑 Beklenmeyen hata varsa fırlat
	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

	@Override
	public ResponseEntity<DataResult<List<ProductWithCategoryDto>>> 
	getProductWithCategoryDetails() {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<ProductWithCategoryDto>>
		(productDao.getProductWithCategoryDetails(),HttpStatus.OK.value(),
				"Filtreli Ürünler başarıyla listelendi"));
	}

	@Override
	public ResponseEntity<DataResult<List<ProductWithBrandDto>>> 
	findAllByProductName(String productName) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<ProductWithBrandDto>>
				(productDao.findProductsByBrandName(productName), 
				HttpStatus.OK.value() ,productName + "markasına ait"));
	}

}
