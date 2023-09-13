package com.driver.Transformers;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

public class FoodTransformer
{
    public static FoodDto foodDtoFromFoodDetailRequestModel(FoodDetailsRequestModel foodDetailsRequestModel){
        FoodDto foodDto=new FoodDto();

        foodDto.setFoodCategory(foodDetailsRequestModel.getFoodCategory());
        foodDto.setFoodPrice(foodDetailsRequestModel.getFoodPrice());
        foodDto.setFoodName(foodDetailsRequestModel.getFoodName());
        return foodDto;
    }
    public static  FoodDetailsResponse foodDetailsResponseFromFoodDto(FoodDto foodDto){
        FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();

        foodDetailsResponse.setFoodId(foodDto.getFoodId());
        foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
        foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
        foodDetailsResponse.setFoodName(foodDto.getFoodName());

        return foodDetailsResponse;
    }
}
