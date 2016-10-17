package uk.gov.hmrc.ssttpds.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Amount {
    private BigDecimal amount;
    private String currency;
}
