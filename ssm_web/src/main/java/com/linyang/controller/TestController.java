package com.linyang.controller;


import com.linyang.service.TestService;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/Test")
public class TestController {



    @RequestMapping("/userName")
    public void test(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        Enumeration attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements())
        {
            System.out.println(attributeNames.nextElement());
        }

        SecurityContext spring_security_context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
        System.out.println(spring_security_context.getAuthentication());
        Authentication authentication = spring_security_context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal.getUsername());
    }
}
