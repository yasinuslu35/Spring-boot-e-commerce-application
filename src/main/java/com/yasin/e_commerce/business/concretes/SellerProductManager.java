package com.yasin.e_commerce.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SellerProductService;
import com.yasin.e_commerce.core.utilities.exceptions.BusinessException;
import com.yasin.e_commerce.core.utilities.mappers.ModelMapperService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.ProductDao;
import com.yasin.e_commerce.dao.abstracts.SellerDao;
import com.yasin.e_commerce.dao.abstracts.SellerProductDao;
import com.yasin.e_commerce.entities.concretes.SellerProduct;
import com.yasin.e_commerce.entities.dto.requestes.SellerProductRequestDto;
import com.yasin.e_commerce.entities.dto.responses.SellerProductResponseDto;


@Service
public class SellerProductManager implements SellerProductService {
	
	private final SellerProductDao sellerProductDao;
	private final ProductDao productDao;
	private final SellerDao sellerDao;
	private ModelMapperService modelMapperService;
	

	public SellerProductManager(SellerProductDao sellerProductDao
			,ProductDao productDao
			,SellerDao sellerDao,
			ModelMapperService modelMapperService) {
		super();
		this.sellerProductDao = sellerProductDao;
		this.productDao = productDao;
		this.sellerDao = sellerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public ResponseEntity<DataResult<List<SellerProductResponseDto>>> getAll() {

		
		
		
		List<SellerProduct> sellerProducts = sellerProductDao.findAll();
		
	    List<SellerProductResponseDto> sellerProductDtos = sellerProducts.stream()
	            .map(sellerProduct -> modelMapperService
	            .forResponse()
	            .map(sellerProduct, SellerProductResponseDto.class))
	            .collect(Collectors.toList());
		
		if(sellerProductDtos.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<SellerProductResponseDto>>
					(sellerProductDtos,HttpStatus.BAD_REQUEST.value(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<SellerProductResponseDto>>
				(sellerProductDtos,HttpStatus.OK.value(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<Result> add(SellerProductRequestDto sellerProductDto) {
	    try {
	        var seller = sellerDao.findByCompanyName(sellerProductDto.getSellerName())
	                .orElseThrow(() -> new BusinessException
	                		("Satıcı bulunamadı! ismi: " + sellerProductDto.getSellerName()));

	        var product = productDao.findByProductName(sellerProductDto.getProductName())
	                .orElseThrow(() -> new BusinessException
	                		("Ürün bulunamadı! ismi: " + sellerProductDto.getProductName()));

	        Optional<SellerProduct> existingProductName = sellerProductDao
	        		.findByProduct_ProductName(product.getProductName().trim());
	        	
	      
	        if (existingProductName.isPresent()) {
	            return ResponseEntity
	            		.status(HttpStatus.BAD_REQUEST)
	            		.body(new ErrorResult(HttpStatus.BAD_REQUEST.value(),
	            				"Bu isimde bir marka zaten mevcut"));
	        }
	        
	        
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

	@Override
	public ResponseEntity<Result> update(SellerProductRequestDto sellerProductDto,Long id) {
        var seller = sellerDao.findByCompanyName(sellerProductDto.getSellerName())
                .orElseThrow(() -> new BusinessException
                		("Satıcı bulunamadı! ismi: " + sellerProductDto.getSellerName()));

        var product = productDao.findByProductName(sellerProductDto.getProductName())
                .orElseThrow(() -> new BusinessException
                		("Ürün bulunamadı! ismi: " + sellerProductDto.getProductName()));
		
	    SellerProduct existingSellerProduct = sellerProductDao.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    existingSellerProduct.setSeller(seller);
	    existingSellerProduct.setProduct(product);
	    existingSellerProduct.setQuantityPerUnit(sellerProductDto.getQuantityPerUnit());
	    existingSellerProduct.setUnitPrice(sellerProductDto.getUnitPrice());
	    existingSellerProduct.setUnitsInStock(sellerProductDto.getUnitsInStock());

	        sellerProductDao.save(existingSellerProduct);
	        
	      return ResponseEntity.status(HttpStatus.OK)
	    		  .body(new SuccessResult(HttpStatus.OK.value(),
	    				  "Satıcının ürünü başarıyla güncellendi"));
	        
	        
	        
	}

}
