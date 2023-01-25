package com.workshop.awscognitoidp.controllers.practice;


import com.workshop.awscognitoidp.models.practice.Bootcamp;
import com.workshop.awscognitoidp.services.practice.BootcampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bootcamps")
public class BootcampController {

    @Autowired
    private BootcampService bootcampService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB')")
    public Bootcamp createBootcamp(@RequestBody Bootcamp bootcamp) {
        return bootcampService.saveBootcamp(bootcamp);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('STUDENT_AGALVISB')")
    public List<Bootcamp> getAllBootcamps() {
        return bootcampService.getAllBootcamps();
    }
}
