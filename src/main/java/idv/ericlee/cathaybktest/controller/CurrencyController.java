package idv.ericlee.cathaybktest.controller;

import idv.ericlee.cathaybktest.model.dao.Currency;
import idv.ericlee.cathaybktest.model.vo.CurrencyVo;
import idv.ericlee.cathaybktest.model.vo.ResBaseVo;
import idv.ericlee.cathaybktest.service.CurrencyServcie;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 幣別 crud
 */
@RestController
@RequestMapping("/api/v1/currency")
@Slf4j
public class CurrencyController {

    @Autowired
    CurrencyServcie currencyServcie;

    /**
     * 建立幣別
     * @param req
     * @return CurrencyVo
     */
    @PostMapping
    public ResBaseVo createCurrency(@RequestBody CurrencyVo req){
        ResBaseVo res = new ResBaseVo();
        try {
            log.info("req CurrencyVo => {}",req);
            CurrencyVo vo = currencyServcie.createCurrency(req);
            res.setStatus("succ");
            res.setContent(vo);
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }

    /**
     * 取得某一幣別
     * @param code
     * @return CurrencyVo
     */
    @GetMapping("/{code}")
    public ResBaseVo getCurrencyById(@PathVariable String code){
        ResBaseVo res = new ResBaseVo();
        try {
            log.info("req code => {}",code);
            CurrencyVo vo = currencyServcie.getCurrencyById(code);
            res.setStatus("succ");
            res.setContent(vo);
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }

    /**
     * 取得所有幣別
     * @return List<CurrencyVo>
     */
    @GetMapping
    public ResBaseVo getAllCurrencys(){
        ResBaseVo res = new ResBaseVo();
        try {
            List<CurrencyVo> vo = currencyServcie.getAllCurrencys();
            res.setStatus("succ");
            res.setContent(vo);
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }

    /**
     * 更新某一幣別
     * @param code
     * @param req
     * @return CurrencyVo
     */
    @PutMapping("/{code}")
    public ResBaseVo updateCurrency(@PathVariable String code,@RequestBody CurrencyVo req){
        ResBaseVo res = new ResBaseVo();
        try {
            log.info("req code => {}, CurrencyVo => {}",code,req);
            CurrencyVo vo = currencyServcie.updateCurrency(code,req);
            res.setStatus("succ");
            res.setContent(vo);
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }

    /**
     * 刪除某一幣別
     * @param code
     */
    @DeleteMapping("/{code}")
    public ResBaseVo deleteCurrency(@PathVariable String code){
        ResBaseVo res = new ResBaseVo();
        try {
            log.info("req code => {}",code);
            currencyServcie.deleteCurrency(code);
            res.setStatus("succ");
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }
}
