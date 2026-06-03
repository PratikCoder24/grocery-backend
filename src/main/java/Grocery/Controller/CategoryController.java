package Grocery.Controller;

import Grocery.DTO.RequestDTO.CategoryRequestDTO;
import Grocery.DTO.ResponseDTO.CategoryResponseDTO;
import Grocery.Services.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
       List <CategoryResponseDTO> responseDTO = categoryService.getCategory();
       return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDTO> addCategory(
            @Valid @RequestBody CategoryRequestDTO addCategory
    ){
        CategoryResponseDTO responseDTO = categoryService.addCategory(addCategory);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO updateCategory
    ){
        CategoryResponseDTO responseDTO = categoryService.updateCategory(id, updateCategory);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable Long id){
        CategoryResponseDTO responseDTO = categoryService.deleteCategory(id);
        return ResponseEntity.ok(responseDTO);
    }
}
