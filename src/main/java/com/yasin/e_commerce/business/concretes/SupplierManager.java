package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.SupplierService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
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
	public DataResult<List<Supplier>> getAll() {

		return new SuccessDataResult<List<Supplier>>(supplierDao.findAll(), 
				"Ürün başarıyla listelendi");
	}

	@Override
	public Result add(Supplier supplier) {
		supplierDao.save(supplier);
		return new SuccessResult("Ürün başarıyla eklendi");
	}

}
