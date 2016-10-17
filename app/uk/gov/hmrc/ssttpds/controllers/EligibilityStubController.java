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
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttpds.config.StubServicesConfig;
import uk.gov.hmrc.ssttpds.services.AmountsDueStubService;
import uk.gov.hmrc.ssttpds.services.ObjectMapperFactory;
import uk.gov.hmrc.ssttpds.services.SAReturnStubService;
import uk.gov.hmrc.ssttpds.services.TTPArrangementStubService;

import static play.libs.Json.toJson;

public class EligibilityStubController extends BaseController {

    private AmountsDueStubService amountsDueStubService;
    private SAReturnStubService saReturnStubService;
    private TTPArrangementStubService ttpArrangementStubService;
    private EligibilityStubController eligibilityStubController;

    public EligibilityStubController() {
        this.amountsDueStubService = StubServicesConfig.amountsDueStubService;
        this.saReturnStubService = StubServicesConfig.saReturnStubService;
        this.ttpArrangementStubService = StubServicesConfig.ttpArrangementStubService;
        this.eligibilityStubController = StubServicesConfig.eligibilityStubController;
        Json.setObjectMapper(ObjectMapperFactory.mapper());
    }

    /*public F.Promise<Result> generate() {
        *//*return withJsonBody(Calculation.class,
                calculation ->
                        response(OK, calculationService.generateMultipleSchedules(calculation)));*//*

    }*/

    public F.Promise<Result> generateSAReturns(int saUTR) {
        return F.Promise.pure(Results.ok(toJson(saReturnStubService.generateSAReturns())));
    }

    public F.Promise<Result> generateAmountsDue(int saUTR) {
        return F.Promise.pure(Results.ok(toJson(amountsDueStubService.generateAmountsDue())));
    }

    public F.Promise<Result> generateTTPArrangement(int saUTR) {
        return F.Promise.promise(() -> ok());
    }
}