package com.jwt8.service;

import com.jwt8.entity.user.AppUser;
import com.jwt8.payload.user.LoginDto;
import com.jwt8.payload.user.RUserDto;
import com.jwt8.payload.user.UserDto;
import com.jwt8.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;
private JWTService jwtService;
private ModelMapper modelMapper;
    public AppUserService(AppUserRepository appUserRepository, JWTService jwtService, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }


    public RUserDto signUp(UserDto userDto) {
        AppUser map = modelMapper.map(userDto, AppUser.class);
        String hashpw = BCrypt.hashpw(map.getPassword(), BCrypt.gensalt(5));
        map.setPassword(hashpw);
        AppUser save = appUserRepository.save(map);
      return  modelMapper.map(save,RUserDto.class);
    }

    public String login(LoginDto loginDto) {
        Optional<AppUser> user =
                appUserRepository.findByUsername(loginDto.getUsername());
        if (user.isPresent())
        {
            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), user.get().getPassword());
            if (checkpw)
            {
                String token = jwtService.generateToken(user.get().getUsername());
                return token;
            }
        }
        return null;
    }
}
