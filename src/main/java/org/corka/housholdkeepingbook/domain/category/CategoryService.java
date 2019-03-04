package org.corka.housholdkeepingbook.domain.category;

import lombok.extern.slf4j.Slf4j;
import org.corka.housholdkeepingbook.domain.user.User;
import org.corka.housholdkeepingbook.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    private UserService userService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public List<Category> getAllActiveCategories() {
        return this.categoryRepository.findAll().stream()
                .filter(this::isNotDeleteCategory)
                .collect(Collectors.toList());
    }

    public void addCategory(String categoryName, String userName) {
        User creator = this.userService.findUserByName(userName);
        this.categoryRepository.save(new Category(categoryName, creator, LocalDateTime.now()));
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
