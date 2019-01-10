package com.tec13.pixiu.core.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.tec13.pixiu.core.domain.BaseDomain;
import com.tec13.pixiu.core.dto.BaseResult;

public abstract class BaseService<D extends BaseDomain<ID>, ID extends Serializable> {

	public BaseResult<D> findById(ID id) {
		Optional<D> dbObj = getRepository().findById(id);

		BaseResult<D> result = new BaseResult<D>();
		if (dbObj.isPresent()) {
			result.setResult(dbObj.get());
		}

		return result;
	}

	public BaseResult<Page<D>> findByExamplePaged(Example<D> e, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<D> pageData = getRepository().findAll(e,pageRequest);
		return new BaseResult<Page<D>>().setResult(pageData);
	}

	public BaseResult<List<D>> findAll() {
		List<D> list = Lists.newArrayList(getRepository().findAll());
		return new BaseResult<List<D>>().setResult(list);
	}

	public BaseResult<D> saveOrUpdate(D d) {
		D saved = getRepository().save(d);
		return new BaseResult<D>().setResult(saved);
	}
	
	public BaseResult<D> deleteById(D d) {
		getRepository().deleteById(d.getId());
		return new BaseResult<D>().setSuccess(true);
	}
	

	protected abstract BaseRepository<D, ID> getRepository();
}
