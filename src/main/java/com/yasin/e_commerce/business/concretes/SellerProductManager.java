package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SellerProductService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.ProductDao;
import com.yasin.e_commerce.dao.abstracts.SellerDao;
import com.yasin.e_commerce.dao.abstracts.SellerProductDao;
import com.yasin.e_commerce.entities.concretes.SellerProduct;
import com.yasin.e_commerce.entities.dto.SellerProductDto;


@Service
public class SellerProductManager implements SellerProductService {
	
	private final SellerProductDao sellerProductDao;
	private final ProductDao productDao;
	private final SellerDao sellerDao;
	private ModelMapper modelMapper;
	

	public SellerProductManager(SellerProductDao sellerProductDao
			,ProductDao productDao
			,SellerDao sellerDao,
			ModelMapper modelMapper) {
		super();
		this.sellerProductDao = sellerProductDao;
		this.productDao = productDao;
		this.sellerDao = sellerDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<DataResult<List<SellerProductDto>>> getAll() {

		
		
		
		List<SellerProduct> sellerProducts = sellerProductDao.findAll();
		
	    List<SellerProductDto> sellerProductDtos = sellerProducts.stream()
	            .map(sellerProduct -> modelMapper.map(sellerProduct, SellerProductDto.class))
	            .collect(Collectors.toList());
		
		if(sellerProductDtos.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<SellerProductDto>>
					(sellerProductDtos,HttpStatus.BAD_REQUEST.value(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<SellerProductDto>>
				(sellerProductDtos,HttpStatus.OK.value(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<Result> add(SellerProductDto sellerProductDto) {
	    try {
	        var seller = sellerDao.findByCompanyName(sellerProductDto.getSellerName())
	                .orElseThrow(() -> new BusinessException
	                		("Satıcı bulunamadı! ismi: " + sellerProductDto.getSellerName()));

	        var product = productDao.findByProductName(sellerProductDto.getProductName())
	                .orElseThrow(() -> new BusinessException
	                		("Ürün bulunamadı! ismi: " + sellerProductDto.getProductName()));

	        
	            // Yeni SellerProduct nesnesi oluşturup gerekli verileri setliyoruz
	            SellerProduct sellerProduct = new SellerProduct();
	            sellerProduct.setSeller(seller);
	            sellerProduct.setProduct(product);
	            sellerProduct.setQuantityPerUnit(sellerProductDto.getQuantityPerUnit());
	            sellerProduct.setUnitPrice(sellerProductDto.getUnitPrice());
	            sellerProduct.setUnitsInStock(sellerProductDto.getUnitsInStock());
	        

	        sellerProductDao.save(sellerProduct);
	        return ResponseEntity
	        		.status(HttpStatus.CREATED)
	        		.body(new SuccessResult(HttpStatus.CREATED.value(),
	        				"Marka başarıyla eklendi!"));

	    } catch (Exception e) {

	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

}
