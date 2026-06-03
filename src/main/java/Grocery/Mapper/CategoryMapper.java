package Grocery.Mapper;

import Grocery.DTO.RequestDTO.CategoryRequestDTO;
import Grocery.DTO.ResponseDTO.CategoryResponseDTO;
import Grocery.Entities.Category;

public class CategoryMapper {

    public  static CategoryResponseDTO mapToResponseDTO(Category category){
           return new CategoryResponseDTO(
                   category.getId(),
                   category.getName()
           );
    }

    public static Category mapToCategory(CategoryRequestDTO categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getCategoryName());
        return category;
    }
}
