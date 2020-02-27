package com.atharva.detailfinder.controller;

import com.atharva.detailfinder.model.Response;
import com.atharva.detailfinder.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DataService dataService;

    @GetMapping("/batch/{batchName}/{homeStation}")
    public Response getBatchByName(@PathVariable(name = "batchName") String batchName, @PathVariable(name = "homeStation") String homeStation) {
        return dataService.getBatchDetailsWithoutStaff(batchName, homeStation);
    }
}
