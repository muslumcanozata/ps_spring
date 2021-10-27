package net.productsellint.controllersTest;

import net.productsellint.api.controllers.ProductsController;
import net.productsellint.business.concretes.ProductServiceImpl;
import net.productsellint.dataTransferObjects.concretes.ProductDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;

@WebMvcTest(ProductsController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productServiceImpl;

    @Test
    public void testGetAll() throws Exception {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto());
        productDtoList.add(new ProductDto());
        productDtoList.get(0).setId(1);
        productDtoList.get(1).setId(2);
        productDtoList.get(0).setProductName("Prod1");
        productDtoList.get(1).setProductName("Prod1");

        when(productServiceImpl.getAll()).thenReturn(productDtoList);

        /*mockMvc.perform(MockMvcRequestBuilders.get("/api/products/getAll").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));*/

       /*mockMvc.perform(get("/api/products/getAll"))
                .andExpect(jsonPath("$."),hasSize(2));*/
    }
}
