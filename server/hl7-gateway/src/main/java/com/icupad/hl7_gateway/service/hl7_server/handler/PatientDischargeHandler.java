package com.icupad.hl7_gateway.service.hl7_server.handler;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v23.message.ADT_A03;
import com.icupad.hl7_gateway.domain.Stay;
import com.icupad.hl7_gateway.service.StayService;
import com.icupad.hl7_gateway.service.hl7_server.StayNotFoundException;
import com.icupad.hl7_gateway.service.hl7_server.segment_parser.PV1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDischargeHandler implements MessageHandler<ADT_A03> {
    private final StayService stayService;
    private final PV1Parser pv1Parser;

    @Autowired
    public PatientDischargeHandler(StayService stayService, PV1Parser pv1Parser) {
        this.stayService = stayService;
        this.pv1Parser = pv1Parser;
    }

    @Override
    public Class<ADT_A03> getMessageType() {
        return ADT_A03.class;
    }

    @Override
    public void handle(ADT_A03 adt_a03) throws HL7Exception {
        Stay stay = pv1Parser.parse(adt_a03.getPV1());
        Stay existingStay = stayService.findByHl7Id(stay.getHl7Id());

        if (existingStay != null) {
            existingStay.setDischargeDate(stay.getDischargeDate());
            existingStay.setActive(false);

            stayService.save(existingStay);
        } else {
            throw new StayNotFoundException();
        }
    }
}
