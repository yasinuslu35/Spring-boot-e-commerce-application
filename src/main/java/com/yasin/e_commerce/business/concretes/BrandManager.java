package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.BrandService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.BrandDao;
import com.yasin.e_commerce.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public DataResult<List<Brand>> getAll() {

		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(),"Markalar listelendi");
	}

	@Override
	public Result add(Brand brand) {
		this.brandDao.save(brand);
		
		return new SuccessResult("Marka eklendi");
	}

}
