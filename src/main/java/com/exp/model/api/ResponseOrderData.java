package com.exp.model.api;

import com.exp.model.type.ExpressType;
import com.exp.model.type.FreightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderData {

    private String orderNumber;

    private String shipmentNumber;

    private BigDecimal payPrice;

    private ExpressType expressType;

    private String serviceCode ;

    private LocalDateTime createTime;

    private FreightStatus status;

    private List<ResponsePackageData> packages;

}
