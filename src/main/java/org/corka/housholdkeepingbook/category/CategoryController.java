package org.corka.housholdkeepingbook.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private static final String CATEGORY_BINDING = "/category";
    private static final String CATEGORIES = "categories";

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(CATEGORY_BINDING)
    public String viewCategories(Model model) {
        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categories);
        model.addAttribute("newCategory", new Category());
        return CATEGORIES;
    }

    @PostMapping(CATEGORY_BINDING)
    public String addCategory(@ModelAttribute Category newCategory, Model model) {
        this.categoryService.addCategory(newCategory);
        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categories);
        model.addAttribute("newCategory", new Category());
        return CATEGORIES;
    }
}
