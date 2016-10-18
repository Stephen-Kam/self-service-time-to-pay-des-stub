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
import uk.gov.hmrc.ssttpds.models.SADebit;
import uk.gov.hmrc.ssttpds.models.Debits;
import java.util.List;

@Slf4j
public class SADebitStubService {

    private final ObjectMapper mapper;

    public SADebitStubService() {
        mapper = ObjectMapperFactory.mapper();
    }

    public List<SADebit> generateSADebit() {
        try {
            return buildSADebit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<SADebit> buildSADebit() throws Exception {
        String jsonAsString = IOUtils.toString(this.getClass().getResourceAsStream("/SADebit.json"));
        Debits result = mapper.readValue(jsonAsString, Debits.class);
        return result.getDebits();
    }
}
