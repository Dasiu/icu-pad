package com.icupad.hl7_gateway.core.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ORU_R01;
import ca.uhn.hl7v2.model.v23.segment.OBX;
import com.icupad.hl7_gateway.Application;
import com.icupad.hl7_gateway.core.domain.Abnormality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.icupad.hl7_gateway.test_data.HL7Messages.testResultsMessageWithouTestResultUnitNormAndAbnormality;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class OBXParserTest {
    @Autowired
    private HapiContext hapiContext;

    @Autowired
    private OBXParser obxParser;

    @Test
    public void emptyTestResultShouldBeParsedToNull() throws HL7Exception {
        OBX obx = obxWithoutTestResultUnitNormAndAbnormality();

        Double testResult = obxParser.parse(obx).getValue();

        assertEquals(null, testResult);
    }

    @Test
    public void emptyUnitShouldBeParsedToNull() throws HL7Exception {
        OBX obx = obxWithoutTestResultUnitNormAndAbnormality();

        String unit = obxParser.parse(obx).getUnit();

        assertEquals(null, unit);
    }

    @Test
    public void emptyNormShouldBeParsedToNull() throws HL7Exception {
        OBX obx = obxWithoutTestResultUnitNormAndAbnormality();

        String norm = obxParser.parse(obx).getNorm();

        assertEquals(null, norm);
    }

    @Test
    public void emptyAbnormalityShouldBeParsedToNull() throws HL7Exception {
        OBX obx = obxWithoutTestResultUnitNormAndAbnormality();

        Abnormality abnormality = obxParser.parse(obx).getAbnormality();

        assertEquals(null, abnormality);
    }

    private OBX obxWithoutTestResultUnitNormAndAbnormality() throws HL7Exception {
        ORU_R01 oru = (ORU_R01) hapiContext.getGenericParser()
                .parse(testResultsMessageWithouTestResultUnitNormAndAbnormality);
        return oru.getRESPONSE().getORDER_OBSERVATION().getOBSERVATION().getOBX();
    }
}
