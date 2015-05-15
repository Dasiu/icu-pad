package com.icupad.hl7_gateway.test_data;

public class HL7Messages {
    public static final String patientRegistrationMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithoutAddressAndAdmittingDoctor = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||\"\"^\"\"&\"\"^\"\"^^\"\"\r" +
            "PV1||I|Klinika||||||||||||||\"\"^\"\"^\"\"^\"\"^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithInvalidPesel = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||-1|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithoutPesel = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||00000000000|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithNullDischargeDate = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700";

    public static final String patientRegistrationMessageWithDischargeDateAsEmptyString = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|\"\"";

    public static final String patientDischargeMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A03|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20130424163100";

    public static final String patientDetailsUpdateMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A08|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|M|||Piłsudskiego^112a&2b^Poznań^^64-500";

    public static final String testResultsMessage = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Białko C-reaktywne|||20100514143200|\r" +
            "OBX|1||LAB_8736||40,3|mg/dl|37,0     - 47,0|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|2|LAB_5_263||Kreatynina|||20100514143200|\r" +
            "OBX|1||LAB_8735||4,66|mg/dl|4,00     - 5,00|\"\"||||||20100514143200||465^Lab^Anna|\r";

    public static final String testResultsMessageWithouTestResultUnitNormAndAbnormality = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Białko C-reaktywne|||20100514143200|\r" +
            "OBX|1||LAB_8736||\"\"|\"\"|\"\"|\"\"||||||20100514143200||465^Lab^Anna|\r";

    public static final String testResultsMessageWithOneMissingResult = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Białko C-reaktywne|||20100514143200|\r" +
            "OBX|1||LAB_8736||,444|mg/dl|  0,37     - 0,51|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|2|LAB_5_263||Kreatynina|||20100514143200|\r" +
            "OBX|1||LAB_8735||\"\"|brak|  11,5     - 14,5|\"\"||||||20100514143200||465^Lab^Anna|\r";

    public static final String completeBloodCountPDWResult = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Morfologia (krew żylna) - PDW|||20100514143200|\r" +
            "OBX|1|ST|LAB_25242036||2,1|fl|\"\"|\"\"||||||20100514143200||1463^Dzieszyńska^Iwona|\r";

    public static final String messageWithUnknownTest = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Unknown test|||20100514143200|\r" +
            "OBX|1|ST|LAB_25242036||2,1|mmol/L|\"\"|\"\"||||||20100514143200||1463^Dzieszyńska^Iwona|\r";

    public static final String messageWithUnitContainingCaretChar = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Oddział internistyczny||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Białko C-reaktywne|||20100514143200|\r" +
            "OBX|1|ST|LAB_25242036||2,1|10^3/ul|\"\"|\"\"||||||20100514143200||1463^Dzieszyńska^Iwona|\r";
}
