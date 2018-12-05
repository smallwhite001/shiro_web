package com.service;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {
    //???为什么没有起作用
    @RequiresRoles({"admin"})
   public void testMethod(){
       System.out.println("------------------------------->testMethod()");
   }
}
