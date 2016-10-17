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

package uk.gov.hmrc.ssttpds.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import uk.gov.hmrc.ssttpds.models.Returns;
import uk.gov.hmrc.ssttpds.models.SAReturn;

import java.util.List;

@Slf4j
public class SAReturnStubService {

    private final ObjectMapper mapper;

    public SAReturnStubService() {
        mapper = ObjectMapperFactory.mapper();
    }

    public List<SAReturn> generateSAReturns() {
        try {
            return buildSAReturns();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<SAReturn> buildSAReturns() throws Exception {
        String jsonAsString = IOUtils.toString(this.getClass().getResourceAsStream("/SAReturn.json"));
        Returns result = mapper.readValue(jsonAsString, Returns.class);
        return result.getReturns();
    }
}
