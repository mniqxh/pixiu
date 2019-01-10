package com.tec13.pixiu.platform.sys.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec13.pixiu.core.jpa.BaseRepository;
import com.tec13.pixiu.core.jpa.BaseService;
import com.tec13.pixiu.platform.sys.user.domain.SysUser;
import com.tec13.pixiu.platform.sys.user.repository.SysUserRepository;

@Service
@Transactional
public class SysUserService extends BaseService<SysUser,Long>{

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Override
	protected BaseRepository<SysUser,Long> getRepository() {
		return sysUserRepository;
	}

}
