package com.cloud.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(user, null);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("profilePictureUrl") String profilePictureUrl) {
        if (result.hasErrors()) {
            return "profile";
        }
        
        User existingUser = userService.findById(user.getId());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setPaternity(user.getPaternity());
            existingUser.setSurname(user.getSurname());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setProfilePictureUrl(profilePictureUrl);

            userService.save(existingUser, profilePictureUrl);
        }
        
        return "redirect:/profile";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
