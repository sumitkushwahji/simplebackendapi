package com.app.simpleBackend.services.impl;

import com.app.simpleBackend.entities.Item;
import com.app.simpleBackend.repositories.ItemRepo;
import com.app.simpleBackend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;
    @Override
//    public Item createItem(Item item) {
//        return itemRepo.save(item);
//    }
    public Item createItem(Item item, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                item.setFileData(file.getBytes());
                item.setFileName(fileName);
            } catch (IOException e) {
                // Handle file upload error
            }
        }
        return itemRepo.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItem(Long id) {
        Optional<Item> item = itemRepo.findById(id);
        return item.orElse(null);
    }

    @Override
    public Item updateItem(Long id, Item itemDetails) {
        Optional<Item> optionalItem = itemRepo.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            // Update fields from itemDetails
            item.setName(itemDetails.getName());
            item.setDescription(itemDetails.getDescription());
            // ... update other fields

            return itemRepo.save(item);
        } else {
            return null;
        }
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public void uploadFile(Long id, MultipartFile file) {
        Optional<Item> optionalItem = itemRepo.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            try {
                item.setFileData(file.getBytes());
                itemRepo.save(item);
            } catch (IOException e) {
                // Handle file upload error
                e.printStackTrace();
                ;
            }
        }
    }
}
