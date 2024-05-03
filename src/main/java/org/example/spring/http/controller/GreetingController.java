package org.example.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring.database.entity.Role;
import org.example.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        HttpServletRequest request,
                        UserReadDto userReadDto) {

        System.out.println();
        model.addAttribute("user", userReadDto);
        return "/greeting/hello";
    }

//    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    @GetMapping("/bye")
    public String bye(Model model, @SessionAttribute("user") UserReadDto user) {

        return "/greeting/bye";
    }

    //    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionid,
                              @PathVariable Integer id) {
        modelAndView.setViewName("/greeting/hello");
        String contextPath = request.getRequestURI();
        return modelAndView;
    }
}
