package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.model.v23.segment.PID;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.core.domain.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.icupad.hl7_gateway.test_data.HL7Messages.patientRegistrationMessageWithoutAddressAndAdmittingDoctor;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class PIDParserTest {
    @Autowired
    private PIDParser pidParser;

    @Autowired
    private HapiContext hapiContext;

    @Test
    public void emptyAddressShouldBeParsedToNull() throws HL7Exception {
        PID pid = pidWithEmptyAddress();

        Address address = pidParser.parse(pid).getAddress();

        assertEquals(null, address.getStreet());
        assertEquals(null, address.getCity());
        assertEquals(null, address.getHouseNumber());
        assertEquals(null, address.getPostalCode());
        assertEquals(null, address.getStreetNumber());
    }

    private PID pidWithEmptyAddress() throws HL7Exception {
        ADT_A01 messageWithPid = (ADT_A01) hapiContext.getGenericParser()
                .parse(patientRegistrationMessageWithoutAddressAndAdmittingDoctor);
        return messageWithPid.getPID();
    }
}
