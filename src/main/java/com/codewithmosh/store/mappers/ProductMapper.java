package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source="category.id",target="categoryId")
    ProductDto toDto(Product product);
    Product toEntity(ProductDto product);
     //  When mapping a ProductDto to a Product entity, exclude the id field from
//    mapping. This is because id is a primary key, and Hibernate does not allow primary key
//    values to be changed once an entity is managed.
     @Mapping(target = "id", ignore = true)
    void update(ProductDto productDto, @MappingTarget Product product);

}
