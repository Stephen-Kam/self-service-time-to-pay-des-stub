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

package uk.gov.hmrc.ssttp.des.services;

import lombok.extern.slf4j.Slf4j;
import uk.gov.hmrc.ssttp.des.models.ErrorResponse;

@Slf4j
public class StatusCodeService {

    public ErrorResponse generate404() {
        return new ErrorResponse("Resource not found", "");
    }

    public ErrorResponse generate500() {
        return new ErrorResponse("Server Error", "");
    }

    public ErrorResponse generate503() {
        return new ErrorResponse("Service unavailable", "");
    }

    public ErrorResponse generateBPNotFound() {
        return new ErrorResponse("BP not found", "002");
    }

    public ErrorResponse generate400() {
        return new ErrorResponse("SERVICE missing or invalid", "001");
    }

    public ErrorResponse invalidUTR() {
        return new ErrorResponse("The request has not passed validation, invalid UTR", "");
    }

    public ErrorResponse invalidJSONFormat() {
        return new ErrorResponse("Invalid JSON message received", "");
    }

    public ErrorResponse invalidRequest() {
        return new ErrorResponse("Your submission contains one or more errors", "");
    }
}
