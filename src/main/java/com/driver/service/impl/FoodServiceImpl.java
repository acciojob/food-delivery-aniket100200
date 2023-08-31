package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 public class FoodServiceImpl implements FoodService
{
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity=new FoodEntity();
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodPrice(food.getFoodPrice());

        foodEntity=foodRepository.save(foodEntity);

        food.setFoodId(foodEntity.getFoodId());
        food.setId(foodEntity.getId());
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception
    {
        FoodEntity foodEntity= foodRepository.findByFoodId(foodId);
        FoodDto foodDto=new FoodDto();

        foodDto.setId(foodEntity.getId());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        foodDto.setFoodName(foodDto.getFoodName());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodId(foodDto.getFoodId());
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception
    {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodEntity.getFoodCategory());

        foodEntity=foodRepository.save(foodEntity);

        foodDetails.setId(foodEntity.getId());
        foodDetails.setFoodId(foodId);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception
    {
        FoodEntity foodEntity= foodRepository.findByFoodId(id);
        if(foodEntity==null)throw new Exception();
        foodRepository.delete(foodEntity);

        //deleted successFully..
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto>foodDtos=new ArrayList<>();
        for(FoodEntity foodEntity: foodRepository.findAll()){
            FoodDto foodDto=new FoodDto();
            foodDto.setId(foodEntity.getId());
            foodDto.setFoodId(foodEntity.getFoodId());
            foodDto.setFoodCategory(foodEntity.getFoodCategory());
            foodDto.setFoodName(foodEntity.getFoodName());
            foodDto.setFoodPrice(foodEntity.getFoodPrice());
            foodDtos.add(foodDto);
        }
        return foodDtos;
    }
}