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

package uk.gov.hmrc.ssttp.des.controllers;

import play.libs.F;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.services.CommPreferencesStubService;
import uk.gov.hmrc.ssttp.des.services.ObjectMapperFactory;
import uk.gov.hmrc.ssttp.des.services.SADebitStubService;
import uk.gov.hmrc.ssttp.des.services.SAReturnStubService;

import static play.libs.Json.toJson;

public class EligibilityStubController extends BaseController {

    private SADebitStubService saDebitStubService;
    private SAReturnStubService saReturnStubService;
    private CommPreferencesStubService commPreferencesStubService;

    public EligibilityStubController() {
        this.saDebitStubService = StubServicesConfig.saDebitsDueStubService;
        this.saReturnStubService = StubServicesConfig.saReturnStubService;
        this.commPreferencesStubService = StubServicesConfig.commPreferencesStubService;
    }

    public F.Promise<Result> generateSAReturns(int utr) {
        return F.Promise.pure(Results.ok(toJson(saReturnStubService.generateSAReturns())));
    }

    public F.Promise<Result> generateSADebits(int utr) {
        return F.Promise.pure(Results.ok(toJson(saDebitStubService.generateSADebit())));
    }

    public F.Promise<Result> generateCommPreferences(int utr) {
        return F.Promise.pure(Results.ok(toJson(commPreferencesStubService.generateCommPreference())));
    }
}