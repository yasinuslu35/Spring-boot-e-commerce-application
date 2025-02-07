package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.BrandService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.BrandDao;
import com.yasin.e_commerce.entities.concretes.Brand;

import jakarta.transaction.Transactional;

@Service
public class BrandManager implements BrandService{

	private final BrandDao brandDao;
	
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public ResponseEntity<DataResult<List<Brand>>> getAll() {
		List<Brand> brands = brandDao.findAll();
		
		if(brands.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<Brand>>
					(this.brandDao.findAll(),"Hiç Marka Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<Brand>>
				(this.brandDao.findAll(),"Markalar listelendi"));
	}

	@Override
	@Transactional
	public ResponseEntity<Result> add(Brand brand) {
	    try {
	        // 1️⃣ VALIDASYON: Marka ismi boş mu?
	        if (brand.getBrandName() == null || brand.getBrandName().trim().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Marka adı boş olamaz!"));
	        }

	        // 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
	        Optional<Brand> existingBrand = brandDao.findByBrandName(brand.getBrandName().trim());

	        if (existingBrand.isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Bu isimde bir marka zaten mevcut"));
	        }

	        // 3️⃣ Veritabanına kayıt işlemi
	        brandDao.save(brand);
	        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Marka başarıyla eklendi!"));

	    } catch (Exception e) {
	        // 🛑 Beklenmeyen hata varsa fırlat
	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

}
