package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.CategoryService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.CategoryDao;
import com.yasin.e_commerce.entities.concretes.Brand;
import com.yasin.e_commerce.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {
	
	private CategoryDao categoryDao;
	
	public CategoryManager(CategoryDao categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public ResponseEntity<DataResult<List<Category>>> getAll() {
		
		try {
			return ResponseEntity.
					status(HttpStatus.OK)
					.body(new SuccessDataResult<List<Category>>
					(categoryDao.findAll(),HttpStatus.OK.value(),"Kategori Başarıyla Eklendi."));
					
		}
		catch (Exception e) {
	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
		}
		
	}

	@Override
	public ResponseEntity<Result> add(Category category) {
	    try {
	        // 1️⃣ VALIDASYON: Marka ismi boş mu?
	        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Marka adı boş olamaz!"));
	        }

	        // 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
	        Optional<Brand> existingBrand = categoryDao.findByCategoryName(category.getCategoryName().trim());

	        if (existingBrand.isPresent()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Bu isimde bir marka zaten mevcut"));
	        }

	        // 3️⃣ Veritabanına kayıt işlemi
	        categoryDao.save(category);
	        return ResponseEntity
	        		.status(HttpStatus.CREATED)
	        		.body(new SuccessResult(HttpStatus.CREATED.value(),
	        				"Marka başarıyla eklendi!"));

	    } catch (Exception e) {
	        // 🛑 Beklenmeyen hata varsa fırlat
	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

}
