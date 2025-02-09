package idv.ericlee.cathaybktest.controller;

import idv.ericlee.cathaybktest.model.vo.ResBaseVo;
import idv.ericlee.cathaybktest.service.CoindeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coindesk")
@Slf4j
public class CoindeskController {

    @Autowired
    CoindeskService coindeskService;

    /**
     * 取得匯率表
     * @return
     */
    @GetMapping
    public ResBaseVo getCoindesk(){
        ResBaseVo res = new ResBaseVo();
        try {
            String coindesk = coindeskService.getTransCoindesk();
            res.setStatus("succ");
            res.setContent(coindesk);
        }catch (Exception e){
            log.error("{}",e);
            res.setStatus("error");
        }
        return res;
    }
}
