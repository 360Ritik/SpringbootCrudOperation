package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserService service;




    @PostMapping("/save")
    @ResponseBody
    public String save_details(@RequestBody User user) {

        service.save_user(user);
        return "user save successfully to the System";

    }

    @GetMapping("/get")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> get_User() {
        return service.get_all_user();
    }



    @GetMapping("/{name}")
    @ResponseBody
    public Optional<User> get_user(@PathVariable String name) {
        return service.Get_User(name);
    }

}
