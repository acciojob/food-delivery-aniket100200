package com.driver.Transformers;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;

public class Transformer
{
    public static FoodDto FoodEntityToFoodDto(FoodEntity foodEntity)
    {
        FoodDto foodDto=new FoodDto();
        //set price..
        foodDto.setFoodPrice(foodDto.getFoodPrice());

        //name..
        foodDto.setFoodName(foodEntity.getFoodName());

        //categroy
        foodDto.setFoodCategory(foodEntity.getFoodCategory());

        //FoodId
        foodDto.setFoodId(foodEntity.getFoodId());

        //id
        foodDto.setId(foodDto.getId());

        return foodDto;

    }

    public static  FoodEntity FoodDtoToFoodEntity(FoodDto foodDto){
        FoodEntity foodEntity=new FoodEntity();

        //set Id..
        foodEntity.setId(foodDto.getId());

        //foood Id
        foodEntity.setFoodId(foodDto.getFoodId());

        //food name
        foodEntity.setFoodName(foodDto.getFoodName());

        //food categroy
        foodEntity.setFoodCategory(foodDto.getFoodCategory());

        //food Tyep
        foodEntity.setFoodPrice(foodDto.getFoodPrice());

        //food
        return foodEntity;
    }


    public static UserResponse userDtoToUserResponce(UserDto userDto){
        UserResponse userResponse=new UserResponse();

        userResponse.setUserId(userDto.getUserId());
        userResponse.setEmail(userDto.getEmail());
        userResponse.setFirstName(userDto.getFirstName());
        userResponse.setLastName(userDto.getLastName());
        return userResponse;
    }

    public  static UserDto userDetailsRequestModelToUserDto(UserDetailsRequestModel userDetailsRequestModel)
    {
        UserDto userDto=new UserDto();
        userDto.setEmail(userDetailsRequestModel.getEmail());
        userDto.setFirstName(userDetailsRequestModel.getFirstName());
        userDto.setLastName(userDetailsRequestModel.getLastName());

        return userDto;

    }

    public static UserEntity userDtoToUserEntity(UserDto user)
    {
        UserEntity userEntity=new UserEntity();

        user.setLastName(user.getLastName());
        user.setFirstName(userEntity.getFirstName());
        user.setId(user.getId());
        user.setUserId(user.getUserId());
        user.setEmail(user.getEmail());

        return userEntity;
    }

    public static UserDto userEntityToUserDto(UserEntity user)
    {
        UserDto userDto=new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        user.setUserId(user.getUserId());
        return userDto;
    }


}
