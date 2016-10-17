package uk.gov.hmrc.ssttpds.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AmountsDue {
    private String originCode;
    private LocalDate dueDate;
    private LocalDate relevantDate;
    //linkingCharge TBD
    private Amount amount;
    private int taxYear;
}
