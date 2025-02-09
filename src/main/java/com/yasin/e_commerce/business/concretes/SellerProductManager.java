package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


@Service
public class SellerProductManager implements SellerProductService {
	
	private final SellerProductDao sellerProductDao;
	private final ProductDao productDao;
	private final SellerDao sellerDao;
	
	@Autowired
	public SellerProductManager(SellerProductDao sellerProductDao
			,ProductDao productDao
			,SellerDao sellerDao) {
		super();
		this.sellerProductDao = sellerProductDao;
		this.productDao = productDao;
		this.sellerDao = sellerDao;
	}

	@Override
	public ResponseEntity<DataResult<List<SellerProduct>>> getAll() {

		List<SellerProduct> sellerProduct = sellerProductDao.findAll();
		
		if(sellerProduct.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDataResult<List<SellerProduct>>
					(this.sellerProductDao.findAll(),"Hiç Ürün Bulunamadı"));
					
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<List<SellerProduct>>
				(this.sellerProductDao.findAll(),"Ürünler listelendi"));
	}

	@Override
	public ResponseEntity<Result> add(SellerProduct sellerProduct) {
	    try {
	        sellerProductDao.save(sellerProduct);
	        return ResponseEntity.status(HttpStatus.CREATED).body(
	        		new SuccessResult("Marka başarıyla eklendi!"));

	    } catch (Exception e) {

	        throw new BusinessException("Marka eklenirken bir hata oluştu: " + e.getMessage());
	    }
	}

}
