package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Menu;
import com.example._20240903ordersys01.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "menus/add";
    }

    @PostMapping("/add")
    public String addMenu(@ModelAttribute Menu menu) {
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("menu", menuService.getMenuById(id).orElseThrow());
        return "menus/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMenu(@PathVariable String id, @ModelAttribute Menu menu) {
        menu.setMenuId(id);
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
        return "redirect:/menus";
    }
}