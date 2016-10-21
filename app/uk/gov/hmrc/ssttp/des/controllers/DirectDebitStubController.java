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
import play.mvc.Result;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.models.DDIPPRequest;
import uk.gov.hmrc.ssttp.des.models.ServicePayload;
import uk.gov.hmrc.ssttp.des.services.DDIPPStubService;
import uk.gov.hmrc.ssttp.des.services.DDIStubService;

public class DirectDebitStubController extends BaseController {

    private DDIStubService ddiStubService;
    private DDIPPStubService ddippStubService;

    public DirectDebitStubController() {
        this.ddiStubService = StubServicesConfig.ddiStubService;
        this.ddippStubService = StubServicesConfig.ddippStubService;
    }

    public F.Promise<Result> generateDDI(String credentialId) {
        return withJsonBody(ServicePayload.class, servicePayload ->
                response(OK, ddiStubService.generateDDI()));
    }

    public F.Promise<Result> generateDDIPP(String credentialId) {
        return withJsonBody(DDIPPRequest.class, ddippRequest ->
                response(OK, ddippStubService.generateDDIPP()));
    }
}
