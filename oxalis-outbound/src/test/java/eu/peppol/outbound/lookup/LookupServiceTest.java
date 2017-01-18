package eu.peppol.outbound.lookup;

import com.google.inject.Inject;
import eu.peppol.outbound.As2PrioritizedTransportModule;
import eu.peppol.outbound.statistics.StatisticsModule;
import eu.peppol.outbound.transmission.TransmissionTestModule;
import no.difi.oxalis.api.lookup.LookupService;
import no.difi.oxalis.commons.mode.ModeModule;
import no.difi.oxalis.commons.tracing.TracingModule;
import no.difi.vefa.peppol.common.model.*;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = {As2PrioritizedTransportModule.class, LookupModule.class, TracingModule.class, ModeModule.class, TransmissionTestModule.class, StatisticsModule.class})
public class LookupServiceTest {

    @Inject
    private LookupService lookupService;

    @Test
    public void simple() throws Exception {
        Endpoint endpoint = lookupService.lookup(Header.newInstance()
                .receiver(ParticipantIdentifier.of("9908:810418052"))
                .documentType(DocumentTypeIdentifier.of("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2::Invoice##urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn:www.peppol.eu:bis:peppol4a:ver2.0::2.1"))
                .process(ProcessIdentifier.of("urn:www.cenbii.eu:profile:bii04:ver2.0")));

        Assert.assertNotNull(endpoint);
    }
}