package com.heritage.mkheritage.controller;

import com.heritage.mkheritage.model.HeritageSite;
import com.heritage.mkheritage.service.HeritageSiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/heritage")
public class HeritageSiteController {

    private final HeritageSiteService heritageSiteService;

    public HeritageSiteController(HeritageSiteService heritageSiteService) {
        this.heritageSiteService = heritageSiteService;
    }

    @PostMapping("/create")
    public HeritageSite createHeritageSite(@RequestBody HeritageSite heritageSite) {
        return heritageSiteService.createHeritageSite(heritageSite);
    }

    @GetMapping("/all")
    public List<HeritageSite> getAllHeritageSites() {
        return heritageSiteService.getAllHeritageSites();
    }

    @GetMapping("/get/{id}")
    public HeritageSite getHeritageSiteById(@PathVariable Long id) {
        return heritageSiteService.getHeritageSiteById(id);
    }

    @GetMapping("/get")
    public List<HeritageSite> getHeritageSiteByName(@RequestParam String name) {
        return heritageSiteService.getHeritageSiteByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHeritageSite(@PathVariable Long id) {
        heritageSiteService.deleteHeritageSite(id);
    }

    @PutMapping("/update/{id}")
    public HeritageSite updateHeritageSite(@RequestBody HeritageSite heritageSite) {
        return heritageSiteService.updateHeritageSite(heritageSite);
    }
}
