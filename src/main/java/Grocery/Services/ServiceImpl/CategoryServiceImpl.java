package Grocery.Services.ServiceImpl;


import Grocery.DTO.RequestDTO.CategoryRequestDTO;
import Grocery.DTO.ResponseDTO.CategoryResponseDTO;
import Grocery.Entities.Category;
import Grocery.Exception.DuplicateResourceException;
import Grocery.Exception.ResourceNotFoundException;
import Grocery.Mapper.CategoryMapper;
import Grocery.Repository.CategoryRepository;
import Grocery.Services.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO addCategory) {
        {
            if (categoryRepository.existsByName(
                    addCategory.getCategoryName())) {

                throw new DuplicateResourceException(
                        "Category already exists"
                );
            }

            Category category = CategoryMapper.mapToCategory(addCategory);
            Category savedCategory = categoryRepository.save(category);
            return CategoryMapper.mapToResponseDTO(savedCategory);
        }
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO updateCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        category.setName(updateCategory.getCategoryName());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToResponseDTO(updatedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getCategory() {
        return  categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
        return CategoryMapper.mapToResponseDTO(category);
    }
}