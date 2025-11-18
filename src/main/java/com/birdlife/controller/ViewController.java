package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.service.BirdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class ViewController {

    private final BirdService birdService;

    public ViewController(BirdService birdService) {
        this.birdService = birdService;
    }

    /** quick sanity check: http://localhost:8080/ping -> OK */
    @ResponseBody
    @GetMapping("/ping")
    public String ping() { return "OK"; }

    /** Map both / and /home. Never 500s if the repo fails — it shows a friendly error instead. */
    @GetMapping({"/", "/home"})
    public String home(@RequestParam(name = "q", required = false) String q,
                       @RequestParam(name = "color", required = false) String color,
                       @RequestParam(name = "location", required = false) String location,
                       Model model) {
        List<BirdDto> birds = Collections.emptyList();
        String loadError = null;

        try {
            // searchAdvanced accepts nulls; returns filtered list
            birds = birdService.searchAdvanced(q, color, location);
        } catch (Exception ex) {
            loadError = ex.getClass().getSimpleName() + ": " + String.valueOf(ex.getMessage());
        }

        model.addAttribute("birds", birds);
        model.addAttribute("q", q);
        model.addAttribute("color", color);
        model.addAttribute("location", location);
        model.addAttribute("loadError", loadError);
        List<String> colors = birdService.getAllColors();
        List<String> locations = birdService.getAllLocations();
        model.addAttribute("colors", colors);
        model.addAttribute("locations", locations);
        model.addAttribute("selectedColor", color);
        model.addAttribute("selectedLocation", location);
        return "home";
    }

    @GetMapping("/myWaypoints")
    public String myWaypoints() {
        return "myWaypoints";
    }

    @GetMapping("/birds/details/{id}")
    public String birdDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bird", birdService.getById(id));
        return "birdDetails";
    }

    /** Save and Delete use BirdService signatures from your codebase */
    @PostMapping("/birds/save/{id}")
    public String saveBird(@PathVariable("id") Long id,
                           @RequestParam("commonName") String commonName,
                           @RequestParam("speciesName") String speciesName,
                           @RequestParam(name = "color", required = false) String color,
                           @RequestParam(name = "defaultLocation", required = false) String defaultLocation,
                           @RequestParam(name = "description", required = false) String description,
                           @RequestParam(name = "notes", required = false) String notes) {

        BirdDto dto = new BirdDto();
        dto.setBirdId(id);
        dto.setCommonName(commonName);
        dto.setSpeciesName(speciesName);
        dto.setColor(color);
        dto.setDefaultLocation(defaultLocation);
        dto.setDescription(description);
        dto.setNotes(notes);

        birdService.update(id, dto);
        return "redirect:/birds/details/" + id;
    }

    @PostMapping("/birds/delete/{id}")
    public String deleteBird(@PathVariable("id") Long id) {
        birdService.delete(id);
        return "redirect:/home";
    }
}
