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

package uk.gov.hmrc.ssttp.des.config;

import play.Application;
import play.libs.Json;
import uk.gov.hmrc.play.java.bootstrap.DefaultMicroserviceGlobal;
import uk.gov.hmrc.play.java.filters.MicroserviceAuthFilter;
import uk.gov.hmrc.ssttp.des.services.ObjectMapperFactory;

import java.util.Arrays;


public class SsttpDesMicroserviceGlobal extends DefaultMicroserviceGlobal {

    @Override
    public <T extends play.api.mvc.EssentialFilter> Class<T>[] filters() {
        return (Class[]) Arrays.stream(super.filters())
                .filter(f -> !f.isAssignableFrom(MicroserviceAuthFilter.class))
                .toArray(size -> new Class[size]);
    }

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        Json.setObjectMapper(ObjectMapperFactory.mapper());
    }
}
