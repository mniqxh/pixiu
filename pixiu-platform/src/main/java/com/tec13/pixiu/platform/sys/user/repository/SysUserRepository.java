package com.tec13.pixiu.platform.sys.user.repository;

import org.springframework.stereotype.Repository;

import com.tec13.pixiu.core.jpa.BaseRepository;
import com.tec13.pixiu.platform.sys.user.domain.SysUser;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Long>{

}
