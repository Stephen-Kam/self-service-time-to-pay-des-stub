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

import play.mvc.Result;
import play.libs.F;
import play.mvc.Results;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.services.ArrangementStubService;
import uk.gov.hmrc.ssttp.des.models.Arrangement;
import uk.gov.hmrc.ssttp.des.models.ResultType;
import uk.gov.hmrc.ssttp.des.services.StatusCodeService;

import static play.libs.Json.toJson;

public class ArrangementStubController extends BaseController {

    private ArrangementStubService arrangementStubService;
    private StatusCodeService statusCodeService;

    public ArrangementStubController() {
        this.arrangementStubService = StubServicesConfig.arrangementStubService;
        this.statusCodeService = StubServicesConfig.statusCodeService;
    }

    public F.Promise<Result> submitArrangement(String utr) {
        try {
            if(request().getHeader("Environment").isEmpty()) {
                throw new NullPointerException("Missing Environemnt");
            }
            if (!request().getHeader(AUTHORIZATION).isEmpty()) {
                try {
                    return withJsonBody(Arrangement.class,
                            this::retrieveResponse);
                } catch (RuntimeException e) {
                    return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
                }
            }
        } catch (NullPointerException e) {
            return F.Promise.pure(Results.unauthorized(toJson(statusCodeService.generate401())));
        }
        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
    }

    private F.Tuple<Integer, Object> retrieveResponse(Arrangement arrangement) {

        ResultType result = arrangementStubService.submitArrangement(arrangement);
        if (result.isAccepted()) {
            return response(ACCEPTED, "");
        } else if (result.isInvalidJSON()) {
            return response(BAD_REQUEST, statusCodeService.invalidJSONFormat());
        } else if (result.isServerError()) {
            return response(INTERNAL_SERVER_ERROR, statusCodeService.generate500());
        } else if (result.isServiceUnavailable()) {
            return response(SERVICE_UNAVAILABLE, statusCodeService.generate503());
        } else {
            return response(BAD_REQUEST, statusCodeService.invalidRequest());
        }
    }
}
