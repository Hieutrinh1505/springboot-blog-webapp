package net.javaguide.springboot.controller;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.RegistrationDto;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle user registration request

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

    // handler method to handle user registration
    @PostMapping("/register/save")
    public String register(@Valid  @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result,Model model){
        User existingUser = userService.findByEmail(registrationDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty())
        {
            result.rejectValue("email",null,"There is already a user with this email!");
        }
        if (result.hasErrors())
        {
            model.addAttribute("user",registrationDto);
            return "register";
        }
        userService.saveUser(registrationDto);
        return "redirect:/register?success";
    }
}
