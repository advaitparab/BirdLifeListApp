package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.entity.Bird;
import com.birdlife.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @Autowired
    private BirdService birdService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/myWaypoints")
    public String myWaypoints() {
        return "myWaypoints";
    }

    @GetMapping("/birds/details/{id}")
    public String birdDetails(@PathVariable Long id, Model model) {
        BirdDto bird = birdService.getById(id);
        model.addAttribute("bird", bird);
        return "birdDetails";
    }

}
