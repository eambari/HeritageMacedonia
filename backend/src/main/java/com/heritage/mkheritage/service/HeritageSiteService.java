package com.heritage.mkheritage.service;

import com.heritage.mkheritage.model.HeritageSite;
import com.heritage.mkheritage.repository.HeritageSiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class HeritageSiteService {
    private final HeritageSiteRepository heritageSiteRepository;

    public HeritageSiteService(HeritageSiteRepository heritageSiteRepository) {
        this.heritageSiteRepository = heritageSiteRepository;
    }

    public HeritageSite createHeritageSite(HeritageSite heritageSite) {
        return heritageSiteRepository.save(heritageSite);
    }

    public HeritageSite getHeritageSiteById(Long id) {
        Optional<HeritageSite> heritageSite = heritageSiteRepository.findById(id);
        return heritageSite.orElse(null);
    }

    public List<HeritageSite> getHeritageSiteByName(String name) {
        return heritageSiteRepository.findByNameContaining(name);
    }

    public List<HeritageSite> getAllHeritageSites() {
        return heritageSiteRepository.findAll();
    }

    public HeritageSite updateHeritageSite(HeritageSite heritageSite) {
        return heritageSiteRepository.save(heritageSite);
    }

    public void deleteHeritageSite(Long id) {
        heritageSiteRepository.deleteById(id);
    }
}
