package com.heritage.mkheritage.controller;

import com.heritage.mkheritage.model.HeritageSite;
import com.heritage.mkheritage.service.HeritageSiteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/heritage")
public class HeritageSiteController {

    private final HeritageSiteService heritageSiteService;

    public HeritageSiteController(HeritageSiteService heritageSiteService) {
        this.heritageSiteService = heritageSiteService;
    }

    @PostMapping("/create")
    public String createHeritageSite(@RequestBody HeritageSite HeritageSite) throws InterruptedException, ExecutionException {
        return heritageSiteService.createHeritageSite(HeritageSite);
    }

    @GetMapping("/all")
    public List<HeritageSite> getAllHeritageSites() throws Exception {
        return heritageSiteService.getAllHeritageSites();
    }

    @GetMapping("/get/{id}")
    public HeritageSite getHeritageSiteById(@PathVariable String id) throws InterruptedException, ExecutionException {
        return heritageSiteService.getHeritageSiteById(id);
    }

    // put the HeritageSite name in a query string
    @GetMapping("/get")
    public List<HeritageSite> getHeritageSiteByName(@RequestParam String name) throws ExecutionException, InterruptedException {
        return heritageSiteService.getHeritageSiteByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHeritageSite(@PathVariable String id) {
        return heritageSiteService.deleteHeritageSite(id);
    }

    @PutMapping("/update/{id}")
    public String updateHeritageSite(@RequestBody HeritageSite HeritageSite) throws ExecutionException, InterruptedException {
        return heritageSiteService.updateHeritageSite(HeritageSite);
    }
}
