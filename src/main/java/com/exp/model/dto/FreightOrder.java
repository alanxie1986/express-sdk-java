package com.exp.model.dto;

import com.exp.model.type.AddressType;
import com.exp.model.type.ExpressType;
import com.exp.model.type.FreightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreightOrder implements Serializable {

    private Long id;


	private FreightAddress sendFrom;

    private FreightAddress sendTo;

    private ExpressType expressType;

	private String orderNumber;

	private String shipmentNumber;

	private boolean pickAble;

    private List<FreightPackage> packageInfos;

    private BigDecimal realPrice;

    private BigDecimal payPrice;

    private BigDecimal optionPrice;

    private BigDecimal publicPrice;

    private FreightStatus status;

    private LocalDateTime createAt;

    private String serviceCode ;

    private Long channelId;

    private Integer feeRate;

    private String cancelMsg;

	public void setSendFrom(FreightAddress sendFrom) {
		if(sendFrom!=null){
			sendFrom.setType(AddressType.ShipFrom);
		}
		this.sendFrom = sendFrom;
	}


	public void setSendTo(FreightAddress sendTo) {
		if(sendTo!=null){
			sendTo.setType(AddressType.ShipTo);
		}
		this.sendTo = sendTo;
	}

}
