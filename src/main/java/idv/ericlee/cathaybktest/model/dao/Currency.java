package idv.ericlee.cathaybktest.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 幣別 中文對照表
 */
@Data
@Entity
@Table(name = "currency_map")
public class Currency implements Serializable {

    private static final long serialVersionUID = -5883131620959394519L;
    /**
     * 幣別代號
     */
    @Id
    @Column(name = "currency_code")
    private String currencyCode;

    /**
     * 中文幣別
     */
    @Column(name = "chinese_name",nullable = false)
    private String chineseName;

    /**
     * 幣別符號
     */
    @Column(name = "symbol")
    private String symbol;

    /**
     * 地區/國家
     */
    @Column(name = "country_region")
    private String countryRegion;
}
