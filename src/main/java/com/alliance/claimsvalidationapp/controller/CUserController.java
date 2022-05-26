package com.alliance.claimsvalidationapp.controller;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.service.CUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class CUserController{
    public final CUserService userservice;
    @GetMapping("/claimsEmployee")
    public String viewClaimsEmployee(){
        return "claimsEmployee";
    }
    @GetMapping("/adminAccounting")
    public ModelAndView adminAccounting(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userservice.getAllUser();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("/adminAccounting");
        return modelAndView;
    }

    @GetMapping("")
    public String loginPage(){
        return "loginpage";
    }
    @GetMapping("/logout")
    public String logoutProcess(HttpSession httpSession){httpSession.invalidate();return "redirect:";}
    @PostMapping("/login")
    public String loginprocess(@RequestParam String email, @RequestParam String password, HttpSession httpSession){
        User user = userservice.login(email,password);
        httpSession.setAttribute("user", user);
        if(user.getUsertype().equals("Accounting")){
            return "redirect:adminAccounting";
        }
        return "redirect:claimsEmployee";
    }
    @PostMapping("/register")
    public String addUser(@ModelAttribute User user){
        userservice.addUser(user);
        return "redirect:adminAccounting";
    }
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute User user, HttpSession httpsession){
        User updatedUser = userservice.updateUser(id,user);
        httpsession.setAttribute("user", updatedUser);
        return "redirect:/adminAccounting";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userservice.deleteUser(id);
        return "redirect:/adminAccounting";
    }
    @GetMapping("/employee/delete/{id}")
    public String deleteEmployeeUser(@PathVariable("id") Long id){
        System.out.println("im gere");
        userservice.deleteUser(id);
        return "redirect:/";
    }
    @PostMapping("/employee/edit/{id}")
    public String editEmployeeUser(@PathVariable("id") Long id, @ModelAttribute User user, HttpSession httpsession){
        User updateEmployeeUser = userservice.updateEmployeeUser(id,user);
        httpsession.setAttribute("user", updateEmployeeUser);
        return "redirect:/claimsEmployee";
    }
    @PostMapping("/employee/editPass/{id}")
    public String editPass(@PathVariable("id") Long id, @RequestParam String curr_password, @RequestParam String
            password){
        userservice.editEmployeePassword(id,curr_password,password);
        return "redirect:/claimsEmployee";
    }
    @PostMapping("/accounting/changePass/{id}")
    public String changePass(@PathVariable("id") Long id, @RequestParam String curr_password, @RequestParam String
            password){
        userservice.editAccountingPassword(id,curr_password,password);
        return "redirect:/adminAccounting";
    }



}


