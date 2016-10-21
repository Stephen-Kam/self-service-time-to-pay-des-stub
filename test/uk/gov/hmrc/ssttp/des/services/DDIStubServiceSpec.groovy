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

class DDIStubServiceSpec extends Specification {

    @Shared
    private service = StubServicesConfig.ddiStubService

    def "Verify DDI.json reads in correctly"() {
        when:
        def result = service.generateDDI()

        then:
        result.processingDate == "2001-12-17T09:30:47Z"
        result.directDebitInstruction.size() == 2
        result.directDebitInstruction.get(0).accountNumber == "12345678"
        result.directDebitInstruction.get(1).creationDate.toString() == "2001-01-01"
    }
}
