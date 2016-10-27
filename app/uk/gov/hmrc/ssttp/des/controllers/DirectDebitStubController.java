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

import play.api.mvc.Codec;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Results;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.models.DDIPPRequest;
import uk.gov.hmrc.ssttp.des.models.ServicePayload;
import uk.gov.hmrc.ssttp.des.services.DDIPPStubService;
import uk.gov.hmrc.ssttp.des.services.DDIStubService;
import uk.gov.hmrc.ssttp.des.services.StatusCodeService;

import static play.core.j.JavaResults.ServiceUnavailable;
import static play.libs.Json.toJson;

public class DirectDebitStubController extends BaseController {

    private DDIStubService ddiStubService;
    private DDIPPStubService ddippStubService;
    private StatusCodeService statusCodeService;
    private static Codec utf8 = Codec.javaSupported("utf-8");

    public DirectDebitStubController() {
        this.ddiStubService = StubServicesConfig.ddiStubService;
        this.ddippStubService = StubServicesConfig.ddippStubService;
        this.statusCodeService = StubServicesConfig.statusCodeService;
    }

    public F.Promise<Result> generateDDI(String credentialId) {
        String requestingService = request().body().asJson().get("requestingService").textValue();
        if (requestingService.equals("force404")) {
            return F.Promise.pure(Results.notFound(toJson(statusCodeService.generate404())));
        } else if (requestingService.equals("force500")) {
            return F.Promise.pure(Results.internalServerError(toJson(statusCodeService.generate500())));
        } else if (requestingService.equals("force503")) {
            return F.Promise.pure(new Status(ServiceUnavailable(), toJson(statusCodeService.generate503()), utf8));
        } else if (!credentialId.equals("1234567890123456")) {
            return F.Promise.pure(Results.notFound(toJson(statusCodeService.generateBPNotFound())));
        } else {
            return withJsonBody(ServicePayload.class, servicePayload ->
                    response(OK, ddiStubService.generateDDI()));
        }

    }

    public F.Promise<Result> generateDDIPP(String credentialId) {
        String requestingService = request().body().asJson().get("requestingService").textValue();
        switch (requestingService) {
            case "force404":
                return F.Promise.pure(Results.notFound(toJson(statusCodeService.generate404())));
            case "force500":
                return F.Promise.pure(Results.internalServerError(toJson(statusCodeService.generate500())));
            case "force503":
                return F.Promise.pure(new Status(ServiceUnavailable(), toJson(statusCodeService.generate503()), utf8));
            case "force400":
                return F.Promise.pure(Results.badRequest(toJson(statusCodeService.generate400())));
            default:
                return withJsonBody(DDIPPRequest.class, ddippRequest ->
                        response(CREATED, ddippStubService.generateDDIPP()));
        }
    }
}
