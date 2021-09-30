package com.sds.teams.msg.service;

import com.sds.teams.BaseUnitTest;
import com.sds.teams.msg.domain.Product;
import com.sds.teams.msg.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

public class ProductServiceTest extends BaseUnitTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository mockProductRepository;

    @Test
    public void whenCallSave_thenCallRepositorySave() {
        Product product = new Product("Daisy", "SDS");
        productService.save(product);

        then(mockProductRepository).should().save(product);
    }

    @Test
    public void whenCallGetProductById_thenReturnProduct() {
        when(mockProductRepository.findById(1L)).thenReturn(
                Optional.of(new Product(1L, "Daisy", "SDS"))
        );

        Product product = productService.getProductById(1L);
        then(mockProductRepository).should().findById(1L);

        assertThat(product.getName()).isEqualTo("Daisy");
        assertThat(product.getCompany()).isEqualTo("SDS");
    }

    @Test
    public void whenCallGetProductByIdWithNonExistId_thenReturnNull() {
        long NONE_EXIST_ID = 2L;
        when(mockProductRepository.findById(NONE_EXIST_ID)).thenReturn(Optional.empty());

        Product product = productService.getProductById(NONE_EXIST_ID);
        then(mockProductRepository).should().findById(NONE_EXIST_ID);

        assertThat(product).isNull();
    }

    @Test
    public void whenCallGetAllProducts_thenReturnProductList() {
        List<Product> products = Lists.newArrayList(new Product("Daisy", "SDS"), new Product("Echo", "SDS"));
        when(mockProductRepository.findAll()).thenReturn(products);

        List<Product> allProducts = productService.getAllProducts();

        then(mockProductRepository).should().findAll();

        assertThat(allProducts.size()).isEqualTo(2);
        assertThat(allProducts).contains(new Product("Daisy", "SDS"), new Product("Echo", "SDS"));
    }

    @Test
    public void GET_products_callGetProductsByName_thenShouldFindAllByNameContains() {
        String NAME = "DAISY";
        productService.getProductsByName(NAME);

        then(mockProductRepository).should().findAllByNameContains(NAME);
    }

}
