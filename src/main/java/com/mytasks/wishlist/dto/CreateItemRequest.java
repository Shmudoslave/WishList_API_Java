package com.mytasks.wishlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemRequest
{
    private String description;

    public String getDescription() {
        return description;
    }
}
