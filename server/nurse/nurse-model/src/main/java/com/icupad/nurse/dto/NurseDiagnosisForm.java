package com.icupad.nurse.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NurseDiagnosisForm {
    
    private String stanSwiadomosci;
    
    private boolean zwiotczenie;
    
    private int otwieranieOczu;
    
    private int reakcjaRuchowa;
    
    private String reaktywnoscKonczynyGorne;
    
    private String reaktywnoscKonczynyDolne;
    
    private List<String> odruchBabinskiego;

}
