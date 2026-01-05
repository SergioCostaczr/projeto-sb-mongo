package com.educandoweb.workshopmongo.resources;

import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.UserDto;
import com.educandoweb.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = service.findAll();
        List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDto userDto){
        User user = service.fromDTO(userDto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}