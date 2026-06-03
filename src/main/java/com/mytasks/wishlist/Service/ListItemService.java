package com.mytasks.wishlist.Service;

import com.mytasks.wishlist.Model.ListItem;
import java.util.List;
import java.util.Optional;
public interface ListItemService
{
    List<ListItem> getAllItems();
    Optional<ListItem> getItemById(Long id);
    ListItem createItem(String description);
    Optional<ListItem> updateItem(Long id, String newDescription);
    boolean deleteItem(Long id);
}
