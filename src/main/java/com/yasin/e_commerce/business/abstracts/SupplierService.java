package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Supplier;

public interface SupplierService {
	DataResult<List<Supplier>> getAll();
	
	Result add(Supplier supplier);

}
