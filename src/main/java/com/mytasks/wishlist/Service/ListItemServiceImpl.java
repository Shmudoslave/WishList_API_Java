package com.mytasks.wishlist.Service;

import com.mytasks.wishlist.Model.ListItem;
import com.mytasks.wishlist.Repository.ListItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ListItemServiceImpl implements ListItemService
{
    private final ListItemRepository repository;

    /*public ListItemServiceImpl(ListItemRepository repository)
    {
        this.repository = repository;
    }*/
    @Override
    public List<ListItem> getAllItems()
    {
        return repository.findAllByOrderByCreatedAtDesc();
    }
    @Override
    public Optional<ListItem> getItemById(Long id)
    {
        return repository.findById(id);

    }
    @Override
    public ListItem createItem(String description)
    {
        ListItem item = new ListItem();
        item.setDescription(description);
        item.setCreatedAt(LocalDateTime.now());
        return repository.save(item);
    }
    @Override
    public Optional<ListItem> updateItem(Long id, String newDescription)
    {
        return repository.findById(id).map(item ->
        {
            item.setDescription(newDescription);
            return repository.save(item);
        });
    }
    @Override
    public boolean deleteItem(Long id)
    {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
