package de.ait.platform.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Schema(description = "DTO for creating or updating a category")
public class CategoryRequest {

    @Schema(description = "Name of the category", example = "Technology", required = true)
    private String name;
}
