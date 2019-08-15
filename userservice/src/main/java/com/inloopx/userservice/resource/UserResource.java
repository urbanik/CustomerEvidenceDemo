package com.inloopx.userservice.resource;

import com.inloopx.userservice.dto.UserDto;
import com.inloopx.userservice.entity.Token;
import com.inloopx.userservice.entity.User;
import com.inloopx.userservice.repository.UserRepository;
import com.inloopx.userservice.strucutremapper.UserMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserResource {

    @EJB
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;


    @POST
    @Path("/login")
    public Token login(@Valid UserDto userDto) {

        return userRepository.checkLogin(userMapper.userDtoToUser(userDto));

    }

    @POST
    @Path("/register")
    public UserDto register(@Valid UserDto userDto) {

        User tmp = userMapper.userDtoToUser(userDto);
        userRepository.registerUser(tmp);
        return userDto;

    }

}
