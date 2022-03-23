package com.emreblbl.springbootthymeleaf.demo.controller;



import com.emreblbl.springbootthymeleaf.demo.entity.User;
import com.emreblbl.springbootthymeleaf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private List<User> userList = new ArrayList<>();
    @Autowired
    public UserController(UserService theUserService){
        this.userService = theUserService;
    }

    @GetMapping("/list")
    public String listUsers(Model model){
        userList = userService.findAll();
        // add to the spring model
        model.addAttribute("listUser",userList);

        return "users/list-users";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        User tempUser = new User();
        theModel.addAttribute("user",tempUser);

        return "users/user-form";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user")User user){
        // save the user
        userService.save(user);
        // use a redirect to prevent duplicate submission

        return "redirect:/users/list";
    }
    // add mapping for "/list"
    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId")int theId,Model theModel){

        theModel.addAttribute("user",userService.findById(theId));


        return "users/user-form";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("userId")int theId){
        userService.delete(theId);
        return "redirect:/users/list";
    }




//    @RequestMapping("/searchEveryWhere")
//    public String searchEveryWhere(Model model,@RequestParam(value = "search",required = false) String keyword) {
//        userList = userService.searchWhereAny(keyword);
//        model.addAttribute("listUser", userList);
//        model.addAttribute("keyword", keyword);
//
//        return "list-users";
//    }
}
