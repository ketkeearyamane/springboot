package com.test.spring;

import com.test.beans.Product;
import com.test.repository.ProductRepository;
import org.apache.tomcat.jni.Proc;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {


    @Mock
    private Model model;

    @InjectMocks
    private MainController mainController;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void homePageTest() {
        String result = mainController.homePage(model);
        assertEquals("home", result);

    }

    @Test
    public void showProductsPageTest() {
        List<Product> iterable = new ArrayList<>();
        iterable.add(new Product(1, "iphone", 990, "Mobiles"));
        Mockito.when(productRepository.findAll()).thenReturn(iterable);
        String result = mainController.findAll(model);

        assertEquals("index", result);
    }
}
