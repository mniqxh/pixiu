package com.tec13.pixiu.platform.sys.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tec13.pixiu.core.dto.BaseResult;
import com.tec13.pixiu.platform.sys.user.domain.SysUser;
import com.tec13.pixiu.platform.sys.user.service.SysUserService;

@Controller
@RequestMapping("/sys/users")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<List<SysUser>> readersBooks() {
		BaseResult<List<SysUser>> userlist = sysUserService.findAll();
		return userlist;
	}
}
