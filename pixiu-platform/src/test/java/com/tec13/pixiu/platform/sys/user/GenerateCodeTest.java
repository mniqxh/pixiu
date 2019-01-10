package com.tec13.pixiu.platform.sys.user;


import org.junit.Test;

import com.tec13.pixiu.core.utils.automation.CodeGenerationUtil;
import com.tec13.pixiu.platform.sys.user.domain.SysUser;


public class GenerateCodeTest {
	
	

	@Test
	public void testGenerate() throws Exception {
		CodeGenerationUtil util = new CodeGenerationUtil();
		util.generateCode(SysUser.class, System.out);
	}
}
