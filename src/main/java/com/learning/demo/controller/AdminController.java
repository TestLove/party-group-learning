package com.learning.demo.controller;

import com.learning.demo.entity.Administrator;
import com.learning.demo.entity.Result;
import com.learning.demo.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员控制类")
public class AdminController {

    @Autowired
    AdminService adminService;

    @ApiOperation(value = "获取用户列表",notes = "返回json，成功返回true，失败返回false。",
            produces = "application/json",
            httpMethod = "GET"
    )
    @GetMapping(value = "/getAdmins",produces = "application/json")
    List<Administrator> getAdmins(){
        return adminService.getAdmins();
    }

    @ApiOperation(value = "增加管理员",
            produces = "application/json",
            httpMethod = "POST"
    )
    @PostMapping(value = "/addAdmin")
    Result addAdmin(@RequestBody Administrator administrator){
        return adminService.addAdmin(administrator);
    }

    @ApiOperation(value = "删除管理员",notes = "通过管理员账号删除管理员",httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteAdmin")
    Result deleteAdmin(@RequestParam String adminAccount){
        return adminService.deleteAdmin(adminAccount);
    }

    @ApiOperation(value = "管理员登录！")
    @PostMapping(value = "/login")
    Result login(String account, String pwd, Model model, HttpSession httpSession){
        httpSession.setAttribute("userLoginInfo",account);
        return adminService.login(account,pwd);
    }

    @ApiOperation(value="管理员登出")
    @PostMapping(value = "/logOut")
    Result logOut(HttpSession httpSession){
        httpSession.removeAttribute("userLoginInfo");
        return adminService.logOut();
    }
}
