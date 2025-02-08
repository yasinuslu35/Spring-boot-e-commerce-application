package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SupplierService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.SupplierDao;
import com.yasin.e_commerce.entities.concretes.Supplier;

@Service
public class SupplierManager implements SupplierService {
	
	private SupplierDao supplierDao;
	
	public SupplierManager(SupplierDao supplierDao) {
		super();
		this.supplierDao = supplierDao;
	}

	@Override
	public ResponseEntity<DataResult<List<Supplier>>> getAll() {
		
		List<Supplier> suppliers = supplierDao.findAll();
		
		if(suppliers.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<Supplier>>
					(this.supplierDao.findAll(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<Supplier>>
				(this.supplierDao.findAll(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<Result> add(Supplier supplier) {
	    try {
	        // 1️⃣ VALIDASYON: Marka ismi boş mu?
	        if (supplier.getCompanyName() == null || supplier.getCompanyName().trim().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Marka adı boş olamaz!"));
	        }

	        // 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
	        Optional<Supplier> existingBrand = supplierDao.findByCompanyName(supplier.getCompanyName().trim());

	        if (existingBrand.isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Bu isimde bir marka zaten mevcut"));
	        }

	        // 3️⃣ Veritabanına kayıt işlemi
	        supplierDao.save(supplier);
	        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Marka başarıyla eklendi!"));

	    } catch (Exception e) {
	        // 🛑 Beklenmeyen hata varsa fırlat
	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

}
