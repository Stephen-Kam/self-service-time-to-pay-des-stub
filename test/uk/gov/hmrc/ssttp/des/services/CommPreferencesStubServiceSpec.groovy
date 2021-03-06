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

class CommPreferencesStubServiceSpec extends Specification {

    @Shared
    private service = StubServicesConfig.commPreferencesStubService;

    def "Verify CommPreferences.json reads in correctly"() {
        when:
        def result = service.generateCommPreference();

        then:
        result.welshLanguageIndicator
        !result.audioIndicator
        !result.largePrintIndicator
        !result.brailleIndicator
    }
}
