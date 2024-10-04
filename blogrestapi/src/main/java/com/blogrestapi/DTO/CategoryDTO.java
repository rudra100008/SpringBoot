package com.blogrestapi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private int categoryId;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 2,message = "title should have more than 2 letters")
    private String categoryTitle;
}
