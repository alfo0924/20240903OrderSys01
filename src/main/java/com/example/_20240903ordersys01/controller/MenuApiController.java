package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Menu;
import com.example._20240903ordersys01.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuApiController {

    @Autowired
    private MenuService menuService;

    // 獲取所有菜單
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    // 根據ID獲取菜單
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable String id) {
        return menuService.getMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新菜單
    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    // 更新菜單信息
    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable String id, @RequestBody Menu menuDetails) {
        return menuService.getMenuById(id)
                .map(menu -> {
                    menu.setRestaurantId(menuDetails.getRestaurantId());
                    menu.setMenuName(menuDetails.getMenuName());
                    menu.setMenuDescribe(menuDetails.getMenuDescribe());
                    menu.setMenuStatus(menuDetails.getMenuStatus());
                    return ResponseEntity.ok(menuService.saveMenu(menu));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除菜單
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        return menuService.getMenuById(id)
                .map(menu -> {
                    menuService.deleteMenu(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}