package com.atharva.detailfinder.controller;

import com.atharva.detailfinder.model.Response;
import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.service.AuthService;
import com.atharva.detailfinder.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DataService dataService;

    @GetMapping("/auth/login")
    public Response goLogin(@RequestHeader String auth) {
        final String[] split = auth.split(":");
        final String userId = new String(Base64.getDecoder().decode(split[0]));
        final int code = authService.verifyUser(userId, split[1]);
        if (code == 200) {
            return new Response(200, auth);
        } else {
            return new Response(code, null);
        }
    }

    @PostMapping("/auth/register")
    public Response goRegister(@RequestHeader final String auth, @RequestBody final User user) {
        final String[] split = auth.split(":");
        final String userId = new String(Base64.getDecoder().decode(split[0]));
        user.setUserId(userId);
        return dataService.addUser(user, split[1]);
    }
}
