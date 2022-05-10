package edu.miu.cs.cs544.RESTfulWebService.controller;

import edu.miu.cs.cs544.RESTfulWebService.domain.Culture;
import edu.miu.cs.cs544.RESTfulWebService.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cultures")
public class CultureController {

    @Autowired
    private CultureService cultureService;

    @GetMapping
    public List<Culture> findAll() {
        return cultureService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Culture> findById(@PathVariable String id) {
        return (cultureService.findById(id));
    }
}

