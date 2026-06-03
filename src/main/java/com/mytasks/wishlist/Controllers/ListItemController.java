package com.mytasks.wishlist.Controllers;

import com.mytasks.wishlist.Model.ListItem;
import com.mytasks.wishlist.Service.ListItemService;
import com.mytasks.wishlist.dto.CreateItemRequest;
import com.mytasks.wishlist.dto.UpdateItemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/list-items")
public class ListItemController {

    private final ListItemService service;

    // Ручной конструктор вместо @RequiredArgsConstructor
    public ListItemController(ListItemService service) {
        this.service = service;
    }

    // Остальные методы остаются без изменений...
    @GetMapping
    public ResponseEntity<List<ListItem>> getAll() {
        return ResponseEntity.ok(service.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListItem> getById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ListItem> create(@RequestBody CreateItemRequest request) {
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ListItem created = service.createItem(request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListItem> update(@PathVariable Long id, @RequestBody UpdateItemRequest request) {
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return service.updateItem(id, request.getDescription())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}