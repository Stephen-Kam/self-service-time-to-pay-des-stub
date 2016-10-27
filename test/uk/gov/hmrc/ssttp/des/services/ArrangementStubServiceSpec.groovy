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

package uk.gov.hmrc.ssttp.des.services

import spock.lang.Shared
import spock.lang.Specification
import uk.gov.hmrc.ssttp.des.config.StubServicesConfig
import uk.gov.hmrc.ssttp.des.models.Arrangement

class ArrangementStubServiceSpec extends Specification {

    @Shared
    private service = StubServicesConfig.arrangementStubService

    def "Verify arrangement service returns correct values"() {
        given:
        def arrangement = new Arrangement()
        def ttpArrangement = new Arrangement.TTPArrangement()
        ttpArrangement.setEnforcementAction("CCP")
        arrangement.setTtpArrangement(ttpArrangement)

        when:
        def result = service.submitArrangement(arrangement)

        then:
        result.accepted
        !result.submissionError;
        !result.invalidJSON;
        !result.serverError;
        !result.serviceUnavailable;
    }

    def "Verify arrangement service returns 500 response"() {
        given:
        def arrangement = new Arrangement()
        def ttpArrangement = new Arrangement.TTPArrangement()
        ttpArrangement.setEnforcementAction("force500")
        arrangement.setTtpArrangement(ttpArrangement)

        when:
        def result = service.submitArrangement(arrangement)

        then:
        !result.accepted
        !result.submissionError;
        !result.invalidJSON;
        result.serverError;
        !result.serviceUnavailable;
    }

    def "Verify arrangement service returns 503 response"() {
        given:
        def arrangement = new Arrangement()
        def ttpArrangement = new Arrangement.TTPArrangement()
        ttpArrangement.setEnforcementAction("force503")
        arrangement.setTtpArrangement(ttpArrangement)

        when:
        def result = service.submitArrangement(arrangement)

        then:
        !result.accepted
        !result.submissionError;
        !result.invalidJSON;
        !result.serverError;
        result.serviceUnavailable;
    }
}
