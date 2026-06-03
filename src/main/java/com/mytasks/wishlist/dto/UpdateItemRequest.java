package com.mytasks.wishlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateItemRequest
{
    private String description;

    public String getDescription() {
        return description;
    }
}
