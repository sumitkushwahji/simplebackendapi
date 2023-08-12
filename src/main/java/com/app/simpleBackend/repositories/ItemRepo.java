package com.app.simpleBackend.repositories;

import com.app.simpleBackend.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository <Item, Long> {
}
