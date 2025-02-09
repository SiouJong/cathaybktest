package idv.ericlee.cathaybktest;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.ericlee.cathaybktest.model.vo.CurrencyVo;
import idv.ericlee.cathaybktest.model.vo.ResBaseVo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static List<CurrencyVo> currencyVos;

    @BeforeAll
    public static void setUpBeforeAll() {
        List<CurrencyVo> vos = new ArrayList<>();
        vos.add(new CurrencyVo("NTD","新台幣","NT$","台灣"));
        vos.add(new CurrencyVo("USD","美元","US$","美國"));
        vos.add(new CurrencyVo("EUR","歐元3","€$$$","歐洲qq聯盟"));
        vos.add(new CurrencyVo("GBP","英鎊","£","英國"));
        currencyVos = vos;
    }

    @Test
    @Order(1)
    void createCurrenciesTest()throws Exception{
        for(CurrencyVo vo:currencyVos){
            createCurrencyTest(vo);
        }
    }

    void createCurrencyTest(CurrencyVo req)throws Exception{
        ResBaseVo res = new ResBaseVo("succ",req);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/currency")
                        .contentType("application/json;charset=UTF-8")
                        .accept("application/json;charset=UTF-8")
                        .content(mapper.writeValueAsString(req))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(res)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    void getCurrencyByIdTest()throws Exception{
        int index = 0;
        //req
        String code = currencyVos.get(index).getCurrencyCode();
        //res
        ResBaseVo res = new ResBaseVo("succ",currencyVos.get(index));
        mockMvc.perform(
             MockMvcRequestBuilders
                     .get("/api/v1/currency/"+code)
                     .accept("application/json;charset=UTF-8")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(res)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    void getAllCurrencysTest()throws Exception{
        ResBaseVo res = new ResBaseVo("succ",currencyVos);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/currency")
                                .accept("application/json;charset=UTF-8")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(res)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    void updateCurrencyTest()throws Exception{
        CurrencyVo req = new CurrencyVo("EUR","歐元","€","歐洲聯盟");

        ResBaseVo res = new ResBaseVo("succ",req);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/v1/currency/"+req.getCurrencyCode())
                                .contentType("application/json;charset=UTF-8")
                                .accept("application/json;charset=UTF-8")
                                .content(mapper.writeValueAsString(req))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(res)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    void deleteCurrenciesTest()throws Exception{
        for (CurrencyVo vo:currencyVos){
            deleteCurrencyTest(vo.getCurrencyCode());
        }
    }

    void deleteCurrencyTest(String code)throws Exception{
        ResBaseVo res = new ResBaseVo();
        res.setStatus("succ");
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/currency/"+code)
                                .accept("application/json;charset=UTF-8")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(res)))
                .andDo(MockMvcResultHandlers.print());
    }

}
