package com.app.simpleBackend.controller;


import com.app.simpleBackend.entities.Item;
import com.app.simpleBackend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


//    @PostMapping
//    public ResponseEntity<Item> createItem(@RequestBody Item item) {
//        Item newItem = itemService.createItem(item);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Item> createItem(@RequestPart("name") String name,
                                           @RequestPart("description") String description,
                                           @RequestPart("email") String email,
                                           @RequestPart("file") MultipartFile file) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setEmail(email);
        return ResponseEntity.ok(itemService.createItem(item, file));
    }

    @GetMapping("")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item updatedItem = itemService.updateItem(id, itemDetails);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        System.out.println("Received file: " + file.getOriginalFilename());
        itemService.uploadFile(id, file);
        return ResponseEntity.ok("File uploaded successfully");
    }
}
