package org.laalka.bankingtransactions_springaop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String userName;
    private BigDecimal balance;
}
