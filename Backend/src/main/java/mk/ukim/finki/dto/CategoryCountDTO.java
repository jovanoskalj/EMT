package mk.ukim.finki.dto;

import mk.ukim.finki.model.enumerations.CategoryAcc;

public class CategoryCountDTO {
    private CategoryAcc category;
    private Long count;

    public CategoryCountDTO(CategoryAcc category, Long count) {
        this.category = category;
        this.count = count;
    }

    public CategoryAcc getCategory() {
        return category;
    }

    public Long getCount() {
        return count;
    }
}
