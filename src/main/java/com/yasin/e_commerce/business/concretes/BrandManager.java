package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.BrandService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.mappers.ModelMapperService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.BrandDao;
import com.yasin.e_commerce.entities.concretes.Brand;
import com.yasin.e_commerce.entities.dto.requestes.BrandRequestDto;
import com.yasin.e_commerce.entities.dto.responses.BrandResponseDto;

import jakarta.transaction.Transactional;

@Service
public class BrandManager implements BrandService {

	private final BrandDao brandDao;
	private final ModelMapperService modelMapperService;

	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public ResponseEntity<DataResult<List<BrandResponseDto>>> getAll() {
		List<Brand> brands = brandDao.findAll();

		List<BrandResponseDto> brandResponseDto = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, BrandResponseDto.class))
				.collect(Collectors.toList());

		if (brands.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<BrandResponseDto>>
					(brandResponseDto,HttpStatus.BAD_REQUEST.value() ,"Hiç Marka Bulunamadı"));

		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<BrandResponseDto>>
				(brandResponseDto,HttpStatus.OK.value() ,"Markalar listelendi"));
	}

	@Override
	@Transactional
	public ResponseEntity<Result> add(BrandRequestDto brandRequestDto) {
		try {
			// 1️⃣ VALIDASYON: Marka ismi boş mu?
			if (brandRequestDto.getBrandName() == null || brandRequestDto.getBrandName().trim().isEmpty()) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
								"Marka adı boş olamaz!"));
			}

			// 2️⃣ İŞ KURALI: Aynı isimde marka var mı kontrol et
			Optional<Brand> existingBrand = brandDao.findByBrandName(brandRequestDto.getBrandName().trim());

			if (existingBrand.isPresent()) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
								"Bu isimde bir marka zaten mevcut"));
			}

			Brand brand = this.modelMapperService.forRequest().map(brandRequestDto, Brand.class);

			brandDao.save(brand);
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
