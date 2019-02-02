package org.corka.housholdkeepingbook.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categories);
        model.addAttribute("newCategory", new Category());
        return CATEGORIES;
    }

    @PostMapping
    public String addCategory(@ModelAttribute Category newCategory, Model model) {
        this.categoryService.addCategory(newCategory);
        return this.viewCategories(model);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteCategory(@PathVariable long id, ModelMap model) {
        this.categoryService.deleteCategory(id);
        return new ModelAndView("redirect:..", model);
    }
}
