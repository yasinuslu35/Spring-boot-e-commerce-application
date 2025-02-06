package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.CategoryService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.CategoryDao;
import com.yasin.e_commerce.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {
	
	private CategoryDao categoryDao;
	
	public CategoryManager(CategoryDao categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public DataResult<List<Category>> getAll() {
		
		return new SuccessDataResult<List<Category>>(categoryDao.findAll(),
				"Kategori başarıyla listelendi");
	}

	@Override
	public Result add(Category category) {
		this.categoryDao.save(category);

		return new SuccessResult("Kategori başarıyla eklendi");
	}

}
