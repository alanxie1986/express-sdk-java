package com.exp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreightPackage implements Serializable {
    private Long id;

    private String packageName;

    private String weight;

    private String length;

    private String width;

    private String height;

    private String declaredValue;

    private String referance1;

    private String referance2;


    private Long orderId;

    private FreightOrder freightOrder;

    private String traceId;

    private String traceData;

    private String description;

    private BigDecimal price;

    private String label;

    public void setFreightOrder(FreightOrder freightOrder) {
        if (freightOrder != null) {
            this.setOrderId(freightOrder.getId());
        }
        this.freightOrder = freightOrder;
    }

    public void setOrderId(Long orderId) {
        if (this.freightOrder == null) {
            this.freightOrder = new FreightOrder();
            this.freightOrder.setId(orderId);
        }
        this.orderId = orderId;
    }


}
