package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SellerService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.mappers.ModelMapperService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.SellerDao;
import com.yasin.e_commerce.entities.concretes.Seller;
import com.yasin.e_commerce.entities.dto.responses.SellerResponseDto;

@Service
public class SellerManager implements SellerService {
	
	private SellerDao sellerDao;
	private final ModelMapperService modelMapperService;
	
	public SellerManager(SellerDao sellerDao, ModelMapperService modelMapperService) {
		super();
		this.sellerDao = sellerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public ResponseEntity<DataResult<List<SellerResponseDto>>> getAll() {
		
		List<Seller> suppliers = sellerDao.findAll();
		
		List<SellerResponseDto> sellerResponseDtos = suppliers.stream()
				.map(t -> modelMapperService.forResponse().map(t, SellerResponseDto.class))
				.collect(Collectors.toList());
		
		if(suppliers.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<SellerResponseDto>>
					(sellerResponseDtos,HttpStatus.BAD_REQUEST.value(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<SellerResponseDto>>
				(sellerResponseDtos,HttpStatus.OK.value(),"Ürünler listelendi"));
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
