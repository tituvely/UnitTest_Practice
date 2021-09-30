package com.sds.teams.msg.controller;

import com.sds.teams.BaseUnitTest;
import com.sds.teams.msg.domain.Product;
import com.sds.teams.msg.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class ProductControllerTest extends BaseUnitTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService mockProductService;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void POST_products_callSaveProduct_thenCallProductServiceSave() {
        Product product = new Product("Daisy", "SDS");
        productController.saveProduct(product);

        then(mockProductService).should().save(product);
    }

    @Test
    public void GET_products_callGetProductById_thenReturn_Product() {
        when(mockProductService.getProductById(1L)).thenReturn(new Product(1L, "Daisy", "SDS"));

        Product result = productController.getProductById(1L);

        then(mockProductService).should().getProductById(1L);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Daisy");
        assertThat(result.getCompany()).isEqualTo("SDS");
    }

    @Test
    public void GET_products_callGetAllProducts_thenReturnProductList() {
        productController.getProducts(null);
        then(mockProductService).should().getAllProducts();
        then(mockProductService).should(never()).getProductsByName("Daisy");
    }

    @Test
    public void GET_products_callGetAllProducts_withNameParam_thenReturn_filtered_ProductList() {
        productController.getProducts("Daisy");
        then(mockProductService).should().getProductsByName(stringArgumentCaptor.capture());
        then(mockProductService).should(never()).getAllProducts();

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("Daisy");
    }


}
