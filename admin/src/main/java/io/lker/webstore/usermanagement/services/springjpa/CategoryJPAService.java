package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.catalogue.Category;
import io.lker.webstore.usermanagement.repositories.CategoryRepository;
import io.lker.webstore.usermanagement.services.CategoryService;
import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class CategoryJPAService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryJPAService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category findById(Long aLong) {
        return categoryRepository.findById(aLong)
                .orElseThrow(() -> new ProductNotFoundException(aLong));
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public void delete(Category object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
