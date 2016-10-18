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

package uk.gov.hmrc.ssttp.des.services.controllers

import play.libs.Json
import play.test.FakeRequest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import uk.gov.hmrc.ssttp.des.config.SsttpDesMicroserviceGlobal

import static play.mvc.Http.Status.ACCEPTED
import static play.test.Helpers.fakeApplication
import static play.test.Helpers.route
import static play.test.Helpers.start
import static play.test.Helpers.status
import static play.test.Helpers.stop

class ArrangementStubControllerSpec extends Specification {

    @Shared
    def fakeApplication = fakeApplication(new SsttpDesMicroserviceGlobal())

    def setupSpec() {
        start(fakeApplication)
    }

    def cleanupSpec() {
        stop(fakeApplication)
    }

    @Unroll
    def "Verify that the status returned is accepted: input #input = #statusCode"() {
        when:
        def response = route(new FakeRequest("POST", "/time-to-pay/taxpayers/1234567890/arrangements").withJsonBody(Json.parse(input)))

        then:
        status(response) == statusCode

        where:
        input || statusCode
        """{
            "ttpArrangement": {
            "startDate": "2016-08-09",
            "endDate": "2016-09-16",
            "firstPaymentDate": "2016-08-09",
            "firstPaymentAmount": "90000.00",
            "regularPaymentAmount": "6000.00",
            "regularPaymentFrequency": "Monthly",
            "reviewDate": "2016-08-09",
            "initials": "DOM",
            "enforcementAction": "CCP",
            "directDebit": true,
            "debitDetails": [
                    {
                        "debitType": "IN2",
                        "dueDate": "2004-07-31"
                    }
            ],
            "saNote": "SA Note Text Here"
        },
            "letterAndControl": {
            "customerName": "Customer Name",
            "salutation": "Dear Sir or Madam",
            "addressLine1": "Plaza 2",
            "addressLine2": "Ironmasters Way",
            "addressLine3": "Telford",
            "addressLine4": "Shropshire",
            "addressLine5": "UK",
            "postCode": "TF3 4NA",
            "totalAll": "50000",
            "clmIndicateInt": "Interest is due",
            "clmPymtString": "1 payment of x.xx then 11 payments of x.xx",
            "officeName1": "office name 1",
            "officeName2": "office name 2",
            "officePostcode": "TF2 8JU",
            "officePhone": "1234567",
            "officeFax": "12345678",
            "officeOpeningHours": "9-5",
            "template": "template",
            "exceptionType": "2",
            "exceptionReason": "Customer requires Large Format printing"
        }
        }""" || ACCEPTED
    }
}
