package idv.ericlee.cathaybktest.service;

import idv.ericlee.cathaybktest.model.dao.Currency;
import idv.ericlee.cathaybktest.model.vo.CurrencyVo;
import idv.ericlee.cathaybktest.repository.CurrencyRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyServcie {

    @Autowired
    CurrencyRepo currencyRepo;

    public CurrencyVo createCurrency(CurrencyVo req)throws Exception{
        if(StringUtils.isBlank(req.getCurrencyCode()) || StringUtils.isBlank(req.getChineseName()))
            throw new Exception("CurrencyVo => CurrencyCode or ChineseName must not be null!");

        Currency currency = new Currency();
        BeanUtils.copyProperties(req,currency);
        currencyRepo.save(currency);
        return req;
    }

    public CurrencyVo getCurrencyById(String code)throws Exception{
        if(StringUtils.isBlank(code))throw new Exception("code => CurrencyCode must not be null!");

        Currency currency = currencyRepo.findById(code).orElseThrow(() -> new Exception("The Currency is not exist! => "+code));
        CurrencyVo vo = new CurrencyVo();
        BeanUtils.copyProperties(currency,vo);
        return vo;
    }

    public List<CurrencyVo> getAllCurrencys()throws Exception{
        List<Currency> currencies = currencyRepo.findAll();
        List<CurrencyVo> vos = new ArrayList<>();
        for(Currency currency:currencies){
            CurrencyVo vo = new CurrencyVo();
            BeanUtils.copyProperties(currency,vo);
            vos.add(vo);
        }
        return vos;
    }

    public CurrencyVo updateCurrency(String code,CurrencyVo req)throws Exception{
        if(StringUtils.isBlank(code))throw new Exception("code => Currency must not be null!");
        if(StringUtils.isBlank(req.getCurrencyCode()) || StringUtils.isBlank(req.getChineseName()))
            throw new Exception("CurrencyVo => CurrencyCode or ChineseName must not be null!");
        if(!code.equals(req.getCurrencyCode()))throw new Exception("code and CurrencyCode do not match!");

        Currency currency = new Currency();
        BeanUtils.copyProperties(req,currency);
        currencyRepo.save(currency);
        return req;
    }

    public void deleteCurrency(String code)throws Exception{
        if(StringUtils.isBlank(code))throw new Exception("code => Currency must not be null!");

        currencyRepo.deleteById(code);
    }
}
