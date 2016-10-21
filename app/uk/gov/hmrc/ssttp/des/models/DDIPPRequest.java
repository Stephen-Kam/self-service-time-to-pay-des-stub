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
public class DDIPPRequest {
    private String requestingService;
    private String submissionDateTime;
    private List<Fact> knownFact;

    private DirectDebitInstruction2 directDebitInstruction;
    private PaymentPlan paymentPlan;

    private boolean printFlag;

    @Data
    public static class DirectDebitInstruction2 {
        private String sortCode;
        private String accountNumber;
        private String accountName;
        private boolean paperAuddisFlag;
        private String ddiRefNumber;
    }

    @Data
    public static class PaymentPlan {
        private String ppType;
        private String paymentReference;
        private String hodService;
        private String paymentCurrency;
        private String initialPaymentAmount;
        private LocalDate initialPaymentStartDate;
        private String scheduledPaymentAmount;
        private LocalDate scheduledPaymentStartDate;
        private LocalDate scheduledPaymentEndDate;
        private String scheduledPaymentFrequency;
        private String balancingPaymentAmount;
        private LocalDate balancingPaymentDate;
        private String totalLiability;
        private LocalDate suspensionStartDate;
        private LocalDate suspensionEndDate;
    }

}
