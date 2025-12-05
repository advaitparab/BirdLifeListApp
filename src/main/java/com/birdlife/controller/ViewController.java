package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.dto.MyListEntryDto;
import com.birdlife.service.BirdService;
import com.birdlife.service.MyListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class ViewController {

    private final BirdService birdService;
    private final MyListService myListService;

    public ViewController(BirdService birdService, MyListService myListService) {
        this.birdService = birdService;
        this.myListService = myListService;
    }

    @ResponseBody
    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(name = "q", required = false) String q,
                       @RequestParam(name = "color", required = false) String color,
                       @RequestParam(name = "location", required = false) String location,
                       Model model) {

        if (q != null && q.isBlank()) q = null;
        if (color != null && color.isBlank()) color = null;
        if (location != null && location.isBlank()) location = null;

        String loadError = null;
        List<BirdDto> birds = Collections.emptyList();

        try {
            birds = birdService.searchAdvanced(q, color, location);
        } catch (Exception ex) {
            loadError = ex.getMessage();
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
    public String myWaypoints(Model model) {
        List<MyListEntryDto> waypoints = myListService.getMyList();
        model.addAttribute("waypoints", waypoints);
        return "myWaypoints";
    }

    // ✅ NEW – handles the Delete button
    @PostMapping("/mylist/remove/{birdId}")
    public String removeFromMyWaypoints(@PathVariable("birdId") Long birdId) {
        myListService.removeFromMyList(birdId);
        return "redirect:/myWaypoints";
    }


    @GetMapping("/birds/details/{id}")
    public String birdDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bird", birdService.getById(id));
        return "birdDetails";
    }

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
