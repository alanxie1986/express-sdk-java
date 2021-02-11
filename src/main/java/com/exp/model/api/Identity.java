package com.exp.model.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Identity {
    private String accountNumber;
    private String requestId;
    private String responseId;
    private String password;
    private String accessToken;
    private BigDecimal balance;
    private String userName;

}
