package idv.ericlee.cathaybktest.service;

import idv.ericlee.cathaybktest.model.vo.CurrencyVo;
import idv.ericlee.cathaybktest.util.ZonedDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CoindeskService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrencyServcie currencyServcie;

    public String getTransCoindesk()throws Exception{
        //get origin json
        String jsonStr = getCoindesk();

        if(StringUtils.isBlank(jsonStr) || !jsonStr.startsWith("{"))
            throw new Exception("json is null or malformed!");

        //transfer content
        JSONObject jsonO = new JSONObject(jsonStr);

        //modify time
        JSONObject time = jsonO.optJSONObject("time");
        String updated = ZonedDateTimeUtil.dateStrToDateStr(time.optString("updated"),ZonedDateTimeUtil.RFC1123_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        time.put("updated",updated);
        String updatedISO = ZonedDateTimeUtil.dateStrToDateStr(time.optString("updatedISO"),ZonedDateTimeUtil.ISO8601_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        time.put("updatedISO",updatedISO);
        String updateduk = ZonedDateTimeUtil.dateStrToDateStr(time.optString("updateduk"),ZonedDateTimeUtil.SELF_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        time.put("updateduk",updateduk);

        //modify bpi
        JSONObject bpi = jsonO.optJSONObject("bpi");
        JSONObject usd = bpi.optJSONObject("USD");
        CurrencyVo currUSD = currencyServcie.getCurrencyById("USD");
        usd.put("chineseName",currUSD.getChineseName());
        usd.remove("symbol");
        usd.remove("description");

        JSONObject gbp = bpi.optJSONObject("GBP");
        CurrencyVo currGBP = currencyServcie.getCurrencyById("GBP");
        gbp.put("chineseName",currGBP.getChineseName());
        gbp.remove("symbol");
        gbp.remove("description");

        JSONObject eur = bpi.optJSONObject("EUR");
        CurrencyVo currEUR = currencyServcie.getCurrencyById("EUR");
        eur.put("chineseName",currEUR.getChineseName());
        eur.remove("symbol");
        eur.remove("description");

        //remove not need
        jsonO.remove("disclaimer");
        jsonO.remove("chartName");
        return jsonO.toString();
    }

    public String getCoindesk()throws Exception{
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if(res.getStatusCodeValue()!= HttpStatus.OK.value())throw new Exception("status code [ "+res.getStatusCodeValue() + " ] content [ "+res.getBody()+" ]");
        return res.getBody();
    }
}
