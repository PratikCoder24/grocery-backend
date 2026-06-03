package Grocery.Services.Service;

import Grocery.DTO.RequestDTO.CategoryRequestDTO;
import Grocery.DTO.ResponseDTO.CategoryResponseDTO;
import Grocery.Entities.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO addCategory);

    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO updateCategory);

    List<CategoryResponseDTO> getCategory();

    CategoryResponseDTO deleteCategory(Long id);
}
