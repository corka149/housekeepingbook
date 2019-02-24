package org.corka.housholdkeepingbook.domain.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllActiveCategories() {
        return this.categoryRepository.findAll().stream()
                .filter(this::isNotDeleteCategory)
                .collect(Collectors.toList());
    }

    public void addCategory(String categoryName) {
        this.addCategory(new Category(categoryName));
    }

    public void addCategory(Category newCategory) {
        this.categoryRepository.save(newCategory);
    }

    @Transactional
    public void deleteCategory(long id) {
        log.debug("Delete {} with id {}", Category.class.getName(), id);
        Category category = this.categoryRepository.getOne(id);
        category.setDeleted(true);
        this.categoryRepository.save(category);
    }

    private boolean isNotDeleteCategory(Category category) {
        return !category.isDeleted();
    }
}
