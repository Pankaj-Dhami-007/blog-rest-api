package com.dhami.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;

    @NotBlank(message = "Category title must not be blank")
    @Size(min = 4, message = "Category title must be at least 4 characters long")
    private String categoryTitle;

    @NotBlank(message = "Category description must not be blank")
    @Size(min = 10, message = "Category description must be at least 10 characters long")
    private String categoryDescription;
}
