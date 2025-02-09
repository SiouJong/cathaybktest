package idv.ericlee.cathaybktest.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBaseVo implements Serializable {
    private static final long serialVersionUID = -3103122450048646768L;

    private String status;

    private Object content;
}
