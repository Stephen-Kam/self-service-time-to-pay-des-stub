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
import uk.gov.hmrc.ssttp.des.models.Arrangement;
import uk.gov.hmrc.ssttp.des.models.ResultType;

@Slf4j
public class ArrangementStubService {

    public ResultType submitArrangement(Arrangement arrangement) {
        try {
            return sendArrangement(arrangement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResultType sendArrangement(Arrangement arrangement) throws Exception {
        ResultType result = new ResultType();
        String enforcementAction = arrangement.getTtpArrangement().getEnforcementAction();
        switch (enforcementAction) {
            case "force500":
                result.setServerError(true);
                break;
            case "force503":
                result.setServiceUnavailable(true);
                break;
            default:
                result.setAccepted(true);
                break;
        }
        return result;
    }
}
