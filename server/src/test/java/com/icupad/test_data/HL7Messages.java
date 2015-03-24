package com.icupad.test_data;

public class HL7Messages {
    public static final String validMessage = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01|12345|P|2.3\r"
            + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
            + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
            + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
            + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
            + "AL1||SEV|001^POLLEN\r"
            + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
            + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";

    public static final String patientRegistrationMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithInvalidPesel = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||-1|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithoutPesel = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||00000000000|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20120424163100";

    public static final String patientRegistrationMessageWithoutDischargeDate = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A01|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700";

    public static final String patientDischargeMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A03|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|U|||Piłsudskiego^112a&2b^Poznań^^64-500\r" +
            "PV1||I|Klinika||||||||||||||245^Nowak^Tomasz^5289888^||476711|||||||||||||||||||||||||20120422184700|20130424163100";

    public static final String patientDetailsUpdateMessage = "MSH|^~\\&|ESKULAP|LAB|ICUPAD|ICUPAD|20120703090814||ADT^A08|12345|P|2.3|||PL||PL\r" +
            "PID||69123001518|123456789||Kowalski^Adam||19910210|M|||Piłsudskiego^112a&2b^Poznań^^64-500";

    public static final String testResultsMessage = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124004||ORU^R01|26590|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||80240344518|47064||NazwiskoTest^ImięTest||19800324|M|||Street^14&10^Londyn^^61-200|\r" +
            "PV1||I|Oddział Internistyczny||||||||||||||2844^Aliak^Magda^2233449||PO14860|||||||||||||||||||||||||20100203094900|20140512083600|\r" +
            "ORC|LAB_13710|\r" +
            "OBR|1|LAB_5_12||Morfologia - hematokryt|||20100514143200|\r" +
            "OBX|1||LAB_8736||,444|L/L|  0,37     - 0,51|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|2|LAB_5_263||Morfologia - Wskaznik anizocytozy|||20100514143200|\r" +
            "OBX|1||LAB_8735||12,2|%|  11,5     - 14,5|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|3|LAB_5_261||Morfologia - Srednia masa hemoglobiny|||20100514143200|\r" +
            "OBX|1||LAB_8734||2,2|fmol|H 1,7      - 2,1      H|H||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|4|LAB_5_260||Morfologia - Srednia obj.krwinki|||20100514143200|\r" +
            "OBX|1||LAB_8733||99|fL|  84       - 100|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|5|LAB_5_17||Morfologia - płytki krwi|||20100514143200|\r" +
            "OBX|1||LAB_8732||333|G/L|  140      - 350|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|6|LAB_5_11||Morfologia - hemoglobina|||20100514143200|\r" +
            "OBX|1||LAB_8731||8|mmol/L|  7,5      - 10,|\"\"||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|7|LAB_5_10||Morfologia - Erytocyty|||20100514143200|\r" +
            "OBX|1||LAB_8730||2|T/L|L 4,       - 5,3      L|L||||||20100514143200||465^Lab^Anna|\r" +
            "OBR|8|LAB_5_6||Morfologia - Leukocyty|||20100514143200|\r" +
            "OBX|1||LAB_8729||12,1|G/L|H 4,1      - 10,0     H|H||||||20100514143200||465^Lab^Anna|\r";

    public static final String testResultsMessage2 = "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124644||ORU^R01|26622|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||79062928519|48966||Lawendowski^Nikol||19790629|M|||Pusta^11&2^Jarock^^20999|\r" +
            "PV1||I|Oddział zakaźny||||||||||||||10^Administrator^System^9999991||PO20384|||||||||||||||||||||||||20140630103100|20140710144300|\r" +
            "ORC|LAB_16420|\r" +
            "OBR|1|LAB_103_104||Mocz - badanie ogólne - Glukoza1|||20140714144655|\r" +
            "OBX|1||LAB_10229||0|mg/dl|  0,       - 0,       ujemna ;   \\.br\\dodatnia|\"\"||||||20140714144655||1^Administrator^Systemu Dev|\r" +
            "OBR|2|LAB_47||Transaminaza Alaninowa|||20140714144648|\r" +
            "OBX|1||LAB_10227||0|IU/l|  < 40|\"\"||||||20140714144648||1^Administrator^Systemu Dev|\r" +
            "OBR|3|LAB_81||Hiv-Przeciwciała|||20140714150724|\r" +
            "OBX|1||LAB_10230||\"\"|brak|  ujemny|\"\"||||||20140714150724||1^Administrator^Systemu Dev|\r" +
            "OBR|4|LAB_55||Opad|||20140714144642|\r" +
            "OBX|1||LAB_10226||12|mm/h|H < 10,0     H|H||||||20140714144642||1^Administrator^Systemu Dev|\r" +
            "OBR|5|LAB_48||Transaminaza Asparaginowa|||20140714144651|\r" +
            "OBX|1||LAB_10228||0|IU/l|  < 35|\"\"||||||20140714144651||1^Administrator^Systemu Dev|";

    public static final String testResultsMessage3= "MSH|^~\\&|ESKULAP|RUCH CHORYCH|ICUPad|ICUPad|20150202124409||ORU^R01|26606|P|2.3|||||POL|windows-1250|PL|\r" +
            "PID||99999999999|10113||Nazwisko^Imię||19700701|F|||Moniuszki^12&11^Poznan^^61-310|\r" +
            "PV1||I|Oddział Chirurgii Oka||||||||||||||11^Admin^Test^8899991||PO11086|||||||||||||||||||||||||20040831161300|20041026100000|\r" +
            "ORC|LAB_4709|\r" +
            "OBR|1|LAB_3||Potas w surowicy|||20040921105353|\r" +
            "OBX|1||LAB_2336||4,4|mmol/l|  3,5  -   5,5|\"\"||||||20040921105353||362^Laboratorium-Długienazwisko^Adminh-Administ|\r" +
            "OBR|2|LAB_1||Sód w surowicy|||20040921105353|\r" +
            "OBX|1||LAB_2335||148|mmol/l|  138  -  148   (Norma sodu)|\"\"||||||20040921105353||362^Laboratorium-Długienazwisko^Adminh-Administ|\r" +
            "OBR|3|LAB_139||Chlorki w surowicy|||20040921105353|\r" +
            "OBX|1||LAB_2337||111|mmol/l|H 98  -  110 H|H||||||20040921105353||362^Laboratorium-Długienazwisko^Adminh-Administ|\r";

    public static final String testResultsMessage4 = "MSH|^~\\&|SENDERAPP|SENDERFAC|COVCDR|COVCDR|20130212221503||ORU^R01|1676326503009050|P|2.3\r" +
            "PID|1||MRN101||DOE^JOHN^A||20000101|M||W|1 Campus Martius^^Detroit^MI^48226||(313)227-7300||EN|S|||111-11-1111|||H\r" +
            "PV1|1|U| 12E^1211^01||||1689885733^ORANGE TEAM, OMNI|||Med||||Tra|||99999999^SMITH^KEVIN^^^^MD|I|000000000000|YY|P||||||||||||||||||||Ac|||20130224080500\r" +
            "ORC|RE|F78520223|000000000^LA||CM||||20130226020200||||  PICU|||^RESULT PERFORMED|||RES\r" +
            "OBR|1|F78520223|1305611705^LA|0101301^COMPLETE BLOOD COUNT^COMPLETE BLOOD COUNT|||20130226010600|20130226020200||2632||||20130226014200||333333^GEORGE, BOB|||||0001305611705|20130226020200|||F||^^^20130226043000^^EA~^^^^^EA\r" +
            "OBX|1|NM|0106550^WHITE BLOOD CELL COUNT^WHITE BLOOD CELL COUNT||7.9|10e9/L|4.3-11.0||||F|||20130226020200|34333^Kelly, Bacon^^00010033^MOLIS XE2|RES\r" +
            "OBX|2|NM|0104650^RBCx10e12^RBCx10e12||4.09|10e12/L|4.53-5.73|L|||F|||20130226020200|34333^Kelly, Bacon^^00010033^MOLIS XE2|RES\r" +
            "OBX|3|NM|0102150^HEMOGLOBIN^HEMOGLOBIN||12.9|g/dL|13.6-17.4|L|||F|||20130226020200|34333^Kelly, Bacon^^00010033^MOLIS XE2|RES\r" +
            "OBX|4|NM|0102100^HEMATOCRIT^HEMATOCRIT||37.5|%|40.7-50.8|L|||F|||20130226020200|34333^Kelly, Bacon^^00010033^MOLIS XE2|RES\r" +
            "OBX|5|NM|0103500^MEAN CORPUSCULAR VOLUME^MEAN CORPUSCULAR VOLUME||91.7|fL|81.6-96.8||||F|||20130226020200|34333^Kelly, Bacon^^00010033^MOLIS XE2|RES\r" +
            "NTE|1||Test performed at Tulsa";
}
