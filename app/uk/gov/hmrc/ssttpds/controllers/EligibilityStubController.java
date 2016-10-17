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

package uk.gov.hmrc.ssttpds.controllers;

import play.libs.F;
import play.mvc.Result;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttpds.config.StubServicesConfig;
import uk.gov.hmrc.ssttpds.services.AmountsDueStubService;
import uk.gov.hmrc.ssttpds.services.SAReturnStubService;
import uk.gov.hmrc.ssttpds.services.TTPArrangementStubService;

public class EligibilityStubController extends BaseController {

    private AmountsDueStubService amountsDueStubService;
    private SAReturnStubService saReturnStubService;
    private TTPArrangementStubService ttpArrangementStubService;

    public EligibilityStubController() {
        this.amountsDueStubService = StubServicesConfig.amountsDueStubService;
        this.saReturnStubService = StubServicesConfig.saReturnStubService;
        this.ttpArrangementStubService = StubServicesConfig.ttpArrangementStubService;
    }

    /*public F.Promise<Result> generate() {
        *//*return withJsonBody(Calculation.class,
                calculation ->
                        response(OK, calculationService.generateMultipleSchedules(calculation)));*//*

    }*/

    public F.Promise<Result> generateSAReturns() {
        return F.Promise.promise(() -> ok());
    }

    public F.Promise<Result> generateAmountsDue() {
        return F.Promise.promise(() -> ok());
    }

    public F.Promise<Result> generateTTPArrangement() {
        return F.Promise.promise(() -> ok());
    }
}