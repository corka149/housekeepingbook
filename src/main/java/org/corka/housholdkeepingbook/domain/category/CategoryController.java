package org.corka.housholdkeepingbook.domain.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("category")
public class CategoryController {

    private static final String CATEGORIES = "categories";

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String viewCategories(Model model) {
        List<Category> categories = this.categoryService.getAllActiveCategories();
        model.addAttribute("categoryList", categories);
        model.addAttribute("newCategory", new Category());
        return CATEGORIES;
    }

    @PostMapping
    public String addCategory(@ModelAttribute Category newCategory, Model model, Principal principal) {
        log.debug("User '{}' tries to add a new category", principal.getName());
        this.categoryService.addCategory(newCategory.getName(), principal.getName());
        return this.viewCategories(model);
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategory(id);
        return "redirect:..";
    }
}
