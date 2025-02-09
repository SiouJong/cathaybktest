package idv.ericlee.cathaybktest.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyVo implements Serializable {

    private static final long serialVersionUID = -5998195767014875670L;

    /**
     * 幣別代號
     */
    private String currencyCode;

    /**
     * 中文幣別
     */
    private String chineseName;

    /**
     * 幣別符號
     */
    private String symbol;

    /**
     * 地區/國家
     */
    private String countryRegion;
}
