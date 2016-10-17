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

package uk.gov.hmrc.ssttpds.config;

import uk.gov.hmrc.ssttpds.controllers.EligibilityStubController;
import uk.gov.hmrc.ssttpds.services.AmountsDueStubService;
import uk.gov.hmrc.ssttpds.services.SAReturnStubService;
import uk.gov.hmrc.ssttpds.services.TTPArrangementStubService;

public class StubServicesConfig {
    public static final AmountsDueStubService amountsDueStubService = new AmountsDueStubService();
    public static final SAReturnStubService saReturnStubService = new SAReturnStubService();
    public static final TTPArrangementStubService ttpArrangementStubService = new TTPArrangementStubService();
    public static final EligibilityStubController eligibilityStubController = new EligibilityStubController();
}
