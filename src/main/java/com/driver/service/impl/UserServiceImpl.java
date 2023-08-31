package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception
    {
        //I'll have to do some check..
        if(user.getFirstName()==null || user.getFirstName().length()>20)throw new Exception("First Name is Greater than actual Length");
        if(user.getLastName()==null || user.getLastName().length()>50)throw new Exception("Last Name is Grater than 50");
        if(user.getEmail()==null || user.getEmail().length()>120)throw new Exception("Email is Greater than 120");


        UserEntity userEntity=new UserEntity();
        /*
        Set all the parameters one by one...
         */
       userEntity.setFirstName(user.getFirstName());
       userEntity.setLastName(user.getLastName());
       userEntity.setEmail(user.getEmail());
       userEntity.setUserId(user.getUserId());

       userEntity= userRepository.save(userEntity);

       user.setId(userEntity.getId());
       user.setUserId(userEntity.getUserId());
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception
    {

       UserEntity user =userRepository.findByEmail(email);
       if(user==null)throw new Exception();
       UserDto userDto=new UserDto();

       userDto.setId(user.getId());
       userDto.setFirstName(user.getFirstName());
       userDto.setLastName(user.getLastName());
       userDto.setEmail(user.getEmail());
       userDto.setUserId(user.getUserId());
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception
    {

        UserEntity user =userRepository.findByUserId(userId);
        if(user==null)throw new Exception();
        UserDto userDto=new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getUserId());
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception
    {
            UserEntity userEntity=userRepository.findByUserId(userId);
            userEntity.setEmail(user.getEmail());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userRepository.save(userEntity);

            //udedated .. user Dto... data
            user.setUserId(userEntity.getUserId());
            user.setId(userEntity.getId());
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception
    {
        UserEntity userEntity=userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers()
    {
        List<UserDto>list=new ArrayList<>();
        for(UserEntity user:userRepository.findAll())
        {
            UserDto userDto=new UserDto();

            userDto.setId(user.getId());
            userDto.setUserId(user.getUserId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());

        }
        return list;
    }
}