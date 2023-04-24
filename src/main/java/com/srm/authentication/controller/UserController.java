package com.srm.authentication.controller;

import com.srm.authentication.Entity.Login;
import com.srm.authentication.Entity.LoginResponseDTO;
import com.srm.authentication.Entity.StudentLogs;
import com.srm.authentication.Entity.UniqueEntriesEntity;
import com.srm.authentication.Entity.User;
import com.srm.authentication.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
  @Autowired UserService userService;

  @PostMapping(value = "/register")
  ResponseEntity<Boolean> registerUser(@RequestBody User user) {
    ResponseEntity<Boolean> responseEntity =
        new ResponseEntity<>(userService.registerUser(user), HttpStatus.ACCEPTED);
    return responseEntity;
  }

  @PostMapping("/login")
  ResponseEntity<LoginResponseDTO> loginUser(@RequestBody Login login) {
    ResponseEntity<LoginResponseDTO> responseEntity =
        new ResponseEntity<>(userService.loginUser(login), HttpStatus.ACCEPTED);
    return responseEntity;
  }

  @PostMapping("/saveStudentLogs")
  ResponseEntity<Boolean> logs(@RequestParam String regNo) {
    ResponseEntity<Boolean> responseEntity =
        new ResponseEntity<>(userService.saveStudentLogs(regNo), HttpStatus.ACCEPTED);
    return responseEntity;
  }

  @GetMapping("/getAllLogs")
  ResponseEntity<List<StudentLogs>> getLogs() {
    ResponseEntity<List<StudentLogs>> responseEntity =
        new ResponseEntity<>(userService.getAllLogs(), HttpStatus.ACCEPTED);
    return responseEntity;
  }

  @GetMapping("/getUniqueLogs")
  ResponseEntity<List<UniqueEntriesEntity>> getUniqueLogs() {
    ResponseEntity<List<UniqueEntriesEntity>> responseEntity =
        new ResponseEntity<>(userService.getUniqueLogs(), HttpStatus.ACCEPTED);
    return responseEntity;
  }
}
