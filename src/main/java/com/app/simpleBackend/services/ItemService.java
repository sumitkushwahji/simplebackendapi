package com.app.simpleBackend.services;

import com.app.simpleBackend.entities.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    Item createItem(Item item, MultipartFile file);

    List<Item> getAllItems();
    Item getItem(Long id);
    Item updateItem(Long id, Item itemDetails);
    void deleteItem(Long id);
    void uploadFile(Long id, MultipartFile file);
}
