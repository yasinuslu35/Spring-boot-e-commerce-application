package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SellerService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.SellerDao;
import com.yasin.e_commerce.entities.concretes.Seller;

@Service
public class SellerManager implements SellerService {
	
	private SellerDao sellerDao;
	
	public SellerManager(SellerDao sellerDao) {
		super();
		this.sellerDao = sellerDao;
	}

	@Override
	public ResponseEntity<DataResult<List<Seller>>> getAll() {
		
		List<Seller> suppliers = sellerDao.findAll();
		
		if(suppliers.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<Seller>>
					(sellerDao.findAll(),HttpStatus.BAD_REQUEST.value(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<Seller>>
				(sellerDao.findAll(),HttpStatus.OK.value(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<Result> add(Seller supplier) {
	    try {
	        // 1️⃣ VALIDASYON: Marka ismi boş mu?
	        if (supplier.getCompanyName() == null || supplier.getCompanyName().trim().isEmpty()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Marka adı boş olamaz!"));
	        }

	        // 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
	        Optional<Seller> existingBrand = sellerDao.findByCompanyName(supplier.getCompanyName().trim());

	        if (existingBrand.isPresent()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Bu isimde bir marka zaten mevcut"));
	        }

	        // 3️⃣ Veritabanına kayıt işlemi
	        sellerDao.save(supplier);
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
