package com.driver.service.impl;

import com.driver.Transformers.Transformer;
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
        UserEntity userEntity= Transformer.userDtoToUserEntity(user);

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

       UserDto userDto=Transformer.userEntityToUserDto(user);

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception
    {

        UserEntity user =userRepository.findByUserId(userId);
        if(user==null)throw new Exception();
        UserDto userDto=Transformer.userEntityToUserDto(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception
    {
            UserEntity userEntity=userRepository.findByUserId(userId);
            if(userEntity==null)throw new Exception();
            user.setId(userEntity.getId());
            userEntity=Transformer.userDtoToUserEntity(user);

           userEntity = userRepository.save(userEntity);

            //udedated .. user Dto... data

            user =Transformer.userEntityToUserDto(userEntity);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception
    {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null)throw  new Exception();
       long id= userEntity.getId();
        userRepository.deleteById(id);

        //user has been deleted successfully..
    }

    @Override
    public List<UserDto> getUsers()
    {
        List<UserDto>list=new ArrayList<>();
        for(UserEntity user:userRepository.findAll())
        {
            UserDto userDto=Transformer.userEntityToUserDto(user);
            list.add(userDto);
        }
        return list;
    }
}