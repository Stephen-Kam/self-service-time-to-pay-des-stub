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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.mvc.Codec;
import play.libs.F;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import scala.util.parsing.json.JSONObject;
import uk.gov.hmrc.play.java.controller.BaseController;
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig;
import uk.gov.hmrc.ssttp.des.services.CommPreferencesStubService;
import uk.gov.hmrc.ssttp.des.services.SADebitStubService;
import uk.gov.hmrc.ssttp.des.services.SAReturnStubService;
import uk.gov.hmrc.ssttp.des.services.StatusCodeService;

import static play.core.j.JavaResults.ServiceUnavailable;
import static play.libs.Json.toJson;

public class EligibilityStubController extends BaseController {

    private SADebitStubService saDebitStubService;
    private SAReturnStubService saReturnStubService;
    private CommPreferencesStubService commPreferencesStubService;
    private StatusCodeService statusCodeService;
    private static Codec utf8 = Codec.javaSupported("utf-8");

    public EligibilityStubController() {
        this.saDebitStubService = StubServicesConfig.saDebitsDueStubService;
        this.saReturnStubService = StubServicesConfig.saReturnStubService;
        this.commPreferencesStubService = StubServicesConfig.commPreferencesStubService;
        this.statusCodeService = StubServicesConfig.statusCodeService;
    }

    public F.Promise<Result> generateSAReturns(String utr) {
        try {
            if (!request().getHeader(AUTHORIZATION).isEmpty()) {
                switch (utr) {
                    case "0": //404
                        return F.Promise.pure(Results.notFound());
                    case "force500":
                        return F.Promise.pure(Results.internalServerError(toJson(statusCodeService.generate500())));
                    case "force503":
                        return F.Promise.pure(new Status(ServiceUnavailable(), toJson(statusCodeService.generate503()), utf8));
                    case "1234567890":
                        JsonNode json = JsonNodeFactory.instance.objectNode().set("returns", toJson(saReturnStubService.generateSAReturns()));
                        return F.Promise.pure(Results.ok(json));
                    default:
                        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
                }
            }
        } catch (NullPointerException e) {
            return F.Promise.pure(Results.unauthorized(toJson(statusCodeService.generate401())));
        }
        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
    }

    public F.Promise<Result> generateSADebits(String utr) {
        try {
            if (!request().getHeader(AUTHORIZATION).isEmpty()) {
                switch (utr) {
                    case "0": //404
                        return F.Promise.pure(Results.notFound());
                    case "force500":
                        return F.Promise.pure(Results.internalServerError(toJson(statusCodeService.generate500())));
                    case "force503":
                        return F.Promise.pure(new Status(ServiceUnavailable(), toJson(statusCodeService.generate503()), utf8));
                    case "1234567890":
                        return F.Promise.pure(Results.ok(toJson(saDebitStubService.generateSADebit())));
                    default:
                        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
                }
            }
        } catch (NullPointerException e) {
            return F.Promise.pure(Results.unauthorized(toJson(statusCodeService.generate401())));
        }
        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
    }

    public F.Promise<Result> generateCommPreferences(String utr) {
        try {
            if (!request().getHeader(AUTHORIZATION).isEmpty()) {
                switch (utr) {
                    case "0": //404
                        return F.Promise.pure(Results.notFound());
                    case "force500":
                        return F.Promise.pure(Results.internalServerError(toJson(statusCodeService.generate500())));
                    case "force503":
                        return F.Promise.pure(new Status(ServiceUnavailable(), toJson(statusCodeService.generate503()), utf8));
                    case "1234567890":
                        return F.Promise.pure(Results.ok(toJson(commPreferencesStubService.generateCommPreference())));
                    default:
                        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
                }
            }
        } catch (NullPointerException e) {
            return F.Promise.pure(Results.unauthorized(toJson(statusCodeService.generate401())));
        }
        return F.Promise.pure(Results.badRequest(toJson(statusCodeService.invalidRequest())));
    }
}