<<<<<<< HEAD
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
=======
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
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
