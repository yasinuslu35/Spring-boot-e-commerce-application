package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Category;

public interface CategoryService {
	DataResult<List<Category>> getAll();
	
	Result add(Category category);

}
