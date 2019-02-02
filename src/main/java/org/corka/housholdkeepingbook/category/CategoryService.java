package org.corka.housholdkeepingbook.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public void addCategory(String categoryName) {
        this.addCategory(new Category(categoryName));
    }

    public void addCategory(Category newCategory) {
        this.categoryRepository.save(newCategory);
    }

    public void deleteCategory(long id) {
        log.info("Delete {} with id {}", Category.class.getName(), id);
        this.categoryRepository.deleteById(id);
    }
}
