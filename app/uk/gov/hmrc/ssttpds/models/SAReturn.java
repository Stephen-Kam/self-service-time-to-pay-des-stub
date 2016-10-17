package uk.gov.hmrc.ssttpds.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SAReturn {
    @NotNull(message = "ssttpds.sareturn.taxyearend.null")
    private int taxYearEnd;

    private LocalDate issued;

    private LocalDate received;

    private LocalDate due;
}
