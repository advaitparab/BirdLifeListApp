package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.service.BirdService;
import com.birdlife.service.impl.MyListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class ViewController {

    private final BirdService birdService;
    //private final MyListService myListService; //Attempt to implement user repository - non-functioning but leaving for potential help - delete when solved

    public ViewController(BirdService birdService) { //public ViewController(BirdService birdService, MyListService myListService) {
        this.birdService = birdService;
        //this.myListService = myListService; //Attempt to implement user repository - non-functioning but leaving for potential help - delete when solved
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
// Normalize empty strings from the form to null so they mean "no filter"
        if (q != null && q.isBlank()) {
            q = null;
        }
        if (color != null && color.isBlank()) {
            color = null;
        }
        if (location != null && location.isBlank()) {
            location = null;
        }
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
    public String myWaypoints(@RequestParam(name = "q", required = false) String q,
                              @RequestParam(name = "color", required = false) String color,
                              @RequestParam(name = "location", required = false) String location,
                              Model model) {// Normalize empty to null (same idea as home)
        if (q != null && q.isBlank()) q = null;
        if (color != null && color.isBlank()) color = null;
        if (location != null && location.isBlank()) location = null;

        model.addAttribute("q", q);
        model.addAttribute("color", color);
        model.addAttribute("location", location);

        List<String> colors = birdService.getAllColors();
        List<String> locations = birdService.getAllLocations();
        model.addAttribute("colors", colors);
        model.addAttribute("locations", locations);
        model.addAttribute("selectedColor", color);
        model.addAttribute("selectedLocation", location);

        return "myWaypoints";
    }

/* //Attempt to implement user repository - non-functioning but leaving for potential help - delete when solved
    @GetMapping("/myWaypoints")
    public String myWaypoints(Model model) {
        model.addAttribute("waypoints", myListService.getMyList(null));
        return "myWaypoints";
    }

    @PostMapping("/mylist/add/{birdId}")
    public String addToMyList(@PathVariable("birdId") Long birdId) {
        myListService.addToMyList(null, birdId);
        return "redirect:/home";
    }

    @PostMapping("/mylist/remove/{birdId}")
    public String removeFromMyList(@PathVariable("birdId") Long birdId) {
        myListService.removeFromMyList(null, birdId);
        return "redirect:/myWaypoints";
    }
*/


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