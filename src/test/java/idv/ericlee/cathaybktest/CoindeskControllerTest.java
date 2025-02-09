package idv.ericlee.cathaybktest;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.ericlee.cathaybktest.model.vo.CurrencyVo;
import idv.ericlee.cathaybktest.model.vo.ResBaseVo;
import idv.ericlee.cathaybktest.service.CoindeskService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
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
public class CoindeskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CoindeskService coindeskService;

    private static List<CurrencyVo> currencyVos;

    public static void setUpBeforeAll() {
        List<CurrencyVo> vos = new ArrayList<>();
        vos.add(new CurrencyVo("NTD","新台幣","NT$","台灣"));
        vos.add(new CurrencyVo("USD","美元","US$","美國"));
        vos.add(new CurrencyVo("EUR","歐元","€","歐洲聯盟"));
        vos.add(new CurrencyVo("GBP","英鎊","£","英國"));
        currencyVos = vos;
    }

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
    void getCoindeskTest() throws Exception {
        //insert data to DB
        setUpBeforeAll();
        createCurrenciesTest();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/coindesk")
                        .accept("application/json;charset=UTF-8")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void callCoindeskApi() throws Exception {
        String jsonStr = coindeskService.getCoindesk();
        JSONObject jsonO = new JSONObject(jsonStr);
        System.out.println(jsonO.toString(4));
    }
}
