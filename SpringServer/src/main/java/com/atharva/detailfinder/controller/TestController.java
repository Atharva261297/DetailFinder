package com.atharva.detailfinder.controller;

import com.atharva.detailfinder.model.BatchWithStaff;
import com.atharva.detailfinder.model.Response;
import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private DataService dataService;

    @PostMapping("/addBatch")
    public Response addBatch(@RequestBody final BatchWithStaff batchWithStaff) {
        return dataService.addNewBatchForDrivers(batchWithStaff);
    }

    @PostMapping("/addUser")
    public void addUser(final User user) {
        dataService.addUser(user);
    }

    @GetMapping("/getUser")
    public Response getUser(final String userId) {
        return dataService.getUser(userId);
    }
}
