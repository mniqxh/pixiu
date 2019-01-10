package com.tec13.pixiu.platform.sys.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tec13.pixiu.MainApplication;
import com.tec13.pixiu.core.dto.BaseResult;
import com.tec13.pixiu.platform.sys.user.domain.SysUser;
import com.tec13.pixiu.platform.sys.user.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class MainApplicationTest {
	@Autowired
	private SysUserService sysUserService;

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void saveUser() {
		SysUser d = new SysUser();
		d.setLoginname("junittest");
		SysUser r = sysUserService.saveOrUpdate(d).getResult();
		assertNotNull(r.getId());
		
		ExampleMatcher e = ExampleMatcher.matching().withIgnorePaths("id").withMatcher("loginName",GenericPropertyMatcher.of(StringMatcher.EXACT));
	
		
		BaseResult<Page<SysUser>> t = sysUserService.findByExamplePaged(Example.of(d,e) , 0, 10);
		System.out.println(t.getResult().getTotalElements());
		assertTrue(t.getResult().getNumber() == 1);
		sysUserService.deleteById(r);
		
	}
	

}
