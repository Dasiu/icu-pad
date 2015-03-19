package com.icupad.service.hl7_server.segment_parsers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.ST;
import ca.uhn.hl7v2.model.v23.datatype.XCN;
import ca.uhn.hl7v2.model.v23.segment.OBX;
import com.icupad.domain.test_result.Abnormality;
import com.icupad.domain.test_result.TestResult;
import com.icupad.domain.test_result.TestResultExecutor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

@Component
public class OBXParser implements Parser<OBX, TestResult> {
    private static final Logger logger = Logger.getLogger(OBXParser.class);

    @Override
    public TestResult parse(OBX obx) throws HL7Exception {
        TestResult testResult = new TestResult();

        testResult.setHl7Id(getHl7Id(obx));
        testResult.setValue(getTestResult(obx));
        testResult.setUnit(getUnit(obx));
        testResult.setNorm(getNorm(obx));
        testResult.setAbnormality(getAbnormality(obx));
        testResult.setResultDate(getResultDate(obx));
        testResult.setExecutor(getTestResultExecutor(obx));

        return testResult;
    }

    private TestResultExecutor getTestResultExecutor(OBX obx) {
        TestResultExecutor testResultExecutor = new TestResultExecutor();
        XCN responsibleObserver = obx.getResponsibleObserver();
        testResultExecutor.setExecutorHl7Id(responsibleObserver.getIDNumber().getValue());
        testResultExecutor.setName(responsibleObserver.getGivenName().getValue());
        testResultExecutor.setSurname(responsibleObserver.getFamilyName().getValue());

        return testResultExecutor;
    }

    private LocalDateTime getResultDate(OBX obx) throws DataTypeException {
        Instant instant = obx.getDateTimeOfTheObservation().getTimeOfAnEvent().getValueAsDate().toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private Abnormality getAbnormality(OBX obx) {
        String abnormalFlag = obx.getAbnormalFlags()[0].getValue();
        switch (abnormalFlag) {
            case "H":
                return Abnormality.ABOVE_HIGH_NORM;
            case "L":
                return Abnormality.BELOW_LOW_NORM;
            default:
                return null;
        }
    }

    private String getNorm(OBX obx) {
        return obx.getReferencesRange().getValue();
    }

    private String getUnit(OBX obx) {
        return obx.getUnits().getIdentifier().getValue();
    }

    private double getTestResult(OBX obx) {
        try {
            ST st = (ST) obx.getObservationValue()[0].getData();
            String testResultStr = st.getValue();

            Locale polishLocale = new Locale("pl", "PL");
            NumberFormat numberFormat = NumberFormat.getInstance(polishLocale);
            return numberFormat.parse(testResultStr).doubleValue();
        } catch (ParseException e) {
            logger.error(e);
            throw new InvalidTestResultFormat();
        }
    }

    private String getHl7Id(OBX obx) {
        return obx.getObservationIdentifier().getCe1_Identifier().getValue();
    }
}
