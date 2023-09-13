package com.driver.service.impl;

import com.driver.Transformers.FoodTransformer;
import com.driver.Transformers.Transformer;
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
    public FoodDto createFood(FoodDto food)
    {
        FoodEntity foodEntity=Transformer.FoodDtoToFoodEntity(food);

        foodEntity=foodRepository.save(foodEntity);

        //use of transformers..
        food=Transformer.FoodEntityToFoodDto(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception
    {
        FoodEntity foodEntity= foodRepository.findByFoodId(foodId);


        if(foodEntity==null)throw new Exception();

        FoodDto foodDto= Transformer.FoodEntityToFoodDto(foodEntity);

        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception
    {
        FoodEntity foodEntity=foodRepository.findById(foodDetails.getId()).get();

        if(foodEntity==null)throw  new Exception();


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
         long foodEntityId=foodEntity.getId();
        //deleted successFully..
        foodRepository.deleteById(foodEntityId);
        //deleted successfuylly
    }

    @Override
    public List<FoodDto> getFoods()
    {
        List<FoodDto>foodDtos=new ArrayList<>();
        for(FoodEntity foodEntity: foodRepository.findAll())
        {
            FoodDto foodDto= Transformer.FoodEntityToFoodDto(foodEntity);
            foodDtos.add(foodDto);
        }
        return foodDtos;
    }
}