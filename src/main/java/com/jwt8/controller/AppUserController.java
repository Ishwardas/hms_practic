package com.jwt8.controller;

import com.jwt8.entity.user.AppUser;
import com.jwt8.payload.user.LoginDto;
import com.jwt8.payload.user.RUserDto;
import com.jwt8.payload.user.TokenDto;
import com.jwt8.payload.user.UserDto;
import com.jwt8.repository.AppUserRepository;
import com.jwt8.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private AppUserService appUserService;
private AppUserRepository appUserRepository;
    public AppUserController(AppUserService appUserService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?>createUser(@Valid  @RequestBody UserDto userDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<AppUser> appUser = appUserRepository.findByUsername(userDto.getUsername());
        if (appUser.isPresent())
        {
            return new ResponseEntity<>("Username already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byEmail = appUserRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent())
        {
            return new ResponseEntity<>("Email I'd already present",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userDto.setRole("ROLE_USER");
        RUserDto rUserDto = appUserService.signUp(userDto);
        return new ResponseEntity<>(rUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?>createPropertyOwner(@Valid  @RequestBody UserDto userDto, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<AppUser> appUser = appUserRepository.findByUsername(userDto.getUsername());
        if (appUser.isPresent())
        {
            return new ResponseEntity<>("Username already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byEmail = appUserRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent())
        {
            return new ResponseEntity<>("Email I'd already present",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userDto.setRole("ROLE_OWNER");
        RUserDto rUserDto = appUserService.signUp(userDto);
        return new ResponseEntity<>(rUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDto loginDto){
        String login = appUserService.login(loginDto);
        if (login!=null)
        {
            TokenDto tokenDto=new TokenDto();
            tokenDto.setToken(login);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Username and password not matched",HttpStatus.OK);
        }
    }
}
