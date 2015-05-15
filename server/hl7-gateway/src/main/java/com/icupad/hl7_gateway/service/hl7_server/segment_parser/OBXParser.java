package com.icupad.hl7_gateway.service.hl7_server.segment_parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.datatype.ST;
import ca.uhn.hl7v2.model.v23.datatype.XCN;
import ca.uhn.hl7v2.model.v23.segment.OBX;
import com.icupad.hl7_gateway.domain.Abnormality;
import com.icupad.hl7_gateway.domain.TestResult;
import com.icupad.hl7_gateway.domain.TestResultExecutor;
import com.icupad.hl7_gateway.service.hl7_server.InvalidTestResultFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

import static com.icupad.hl7_gateway.service.hl7_server.segment_parser.ParserUtils.convertToLocalDateTime;

@Component
public class OBXParser extends AbstractParser implements Parser<OBX, TestResult> {
    private static final Logger logger = Logger.getLogger(OBXParser.class);

    private final String hl7MessageDelimiter;

    @Autowired
    public OBXParser(@Value("^")String hl7MessageDelimiter) {
        this.hl7MessageDelimiter = hl7MessageDelimiter;
    }

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

    private LocalDateTime getResultDate(OBX obx) throws HL7Exception {
        return convertToLocalDateTime(obx.getDateTimeOfTheObservation().getTimeOfAnEvent());
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
        String norm = obx.getReferencesRange().getValue();
        return parseNullValue(norm);
    }

    private String getUnit(OBX obx) {
        String unit = unitIsContainingHl7MessageDelimiter(obx)
                ? getUnitWithDelimiter(obx)
                : obx.getUnits().getIdentifier().getValue();

        return parseNullValue(unit);
    }

    private String getUnitWithDelimiter(OBX obx) {
        String unit;
        String unitFirstPart = obx.getUnits().getIdentifier().getValue();
        String unitSecondPart = obx.getUnits().getCe2_Text().getValue();
        unit = unitFirstPart + hl7MessageDelimiter + unitSecondPart;
        return unit;
    }

    private boolean unitIsContainingHl7MessageDelimiter(OBX obx) {
        return obx.getUnits().getCe2_Text().getValue() != null;
    }

    private Double getTestResult(OBX obx) {
        ST st = (ST) obx.getObservationValue()[0].getData();
        String testResultStr = st.getValue();

        return !isTestResultMissing(testResultStr) ? parseTestResult(testResultStr) : null;
    }

    private boolean isTestResultMissing(String testResultStr) {
        return getEmptyFieldValue().equals(testResultStr);
    }

    private double parseTestResult(String testResultStr) {
        try {
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
