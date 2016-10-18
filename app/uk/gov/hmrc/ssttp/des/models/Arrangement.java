/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ssttp.des.models;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class Arrangement {
    private TTPArrangement ttpArrangement;
    private LetterAndControl letterAndControl;

    @Data
    public class TTPArrangement {
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate firstPaymentDate;
        private String firstPaymentAmount;
        private String regularPaymentAmount;
        private String regularPaymentFrequency;
        private LocalDate reviewDate;
        private String initials;
        private String enforcementAction;
        private Boolean directDebit;
        private List<DebitDetails> debitDetails;
        private String saNote;


    }

    @Data
    public static class DebitDetails {
        private String debitType;
        private LocalDate dueDate;
    }

    @Data
    public class LetterAndControl {
        private String customerName;
        private String salutation;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String addressLine4;
        private String addressLine5;
        private String postcode;
        private String totalAll;
        private String clmIndicateInt;
        private String clmPymtString;
        private String officeName1;
        private String officeName2;
        private String officePostCode;
        private String officePhone;
        private String officeFax;
        private String officeOpeningHours;
        private String template;
        private String exceptionType;
        private String exceptionReason;
    }
}
