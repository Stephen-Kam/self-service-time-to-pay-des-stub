package uk.gov.hmrc.ssttpds.config;

import uk.gov.hmrc.ssttpds.services.AmountsDueStubService;
import uk.gov.hmrc.ssttpds.services.SAReturnStubService;
import uk.gov.hmrc.ssttpds.services.TTPArrangementStubService;

public class StubServicesConfig {
    public static final AmountsDueStubService amountsDueStubService = new AmountsDueStubService();
    public static final SAReturnStubService saReturnStubService = new SAReturnStubService();
    public static final TTPArrangementStubService ttpArrangementStubService = new TTPArrangementStubService();
}
