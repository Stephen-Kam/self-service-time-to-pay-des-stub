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
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.services.ArrangementStubService;
import uk.gov.hmrc.ssttp.des.models.Arrangement;
import uk.gov.hmrc.ssttp.des.models.ResultType;

import static play.libs.Json.toJson;

public class ArrangementStubController extends BaseController {

    private ArrangementStubService arrangementStubService;

    public ArrangementStubController() {
        this.arrangementStubService = StubServicesConfig.arrangementStubService;
    }

    public F.Promise<Result> submitArrangement(int utr) {
        return withJsonBody(Arrangement.class,
                this::retrieveResponse);
    }

    private F.Tuple<Integer, Object> retrieveResponse (Arrangement arrangement) {
        ResultType result = arrangementStubService.submitArrangement(arrangement);
        if(result.isAccepted()) {
            return response(ACCEPTED, "");
        } else if(result.isInvalidJSON()) {
            return response(BAD_REQUEST, toJson("{\n\"reason\": \"Malformed JSON received\"\n}"));
        } else if(result.isServerError()) {
            return response(INTERNAL_SERVER_ERROR, toJson("{\n\"reason\": \"DES is currently experiencing problems that require live service intervention\"\n}"));
        } else if(result.isServiceUnavailable()) {
            return response(SERVICE_UNAVAILABLE, "{\n\"reason\": \"Dependent systems are currently not responding\"\n}");
        } else {
            return response(BAD_REQUEST, toJson("{\n\"reason\": \"Submission has not passed validation\"\n}"));
        }
    }
}
