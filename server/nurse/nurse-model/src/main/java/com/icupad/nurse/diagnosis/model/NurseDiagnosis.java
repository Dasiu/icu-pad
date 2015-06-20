package com.icupad.nurse.diagnosis.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
public class NurseDiagnosis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "OGO_STAN_SWIADOMOSCI")
    private StanSwiadomosci stanSwiadomosci;
    
    @Column(name = "OGO_ZWIOTCZENIE")
    private boolean zwiotczenie;
    
    @Min(1)
    @Max(4)
    @Column(name = "GLA_OTWIERANIE_OCZU")
    private Integer otwieranieOczu;
    
    @Min(1)
    @Max(6)
    @Column(name = "GLA_REAKCJA_RUCHOWA")
    private Integer reakcjaRuchowa;
    
    @Column(name = "KON_REAKTYWNOSC_KONCZYN_GORNE")
    private boolean reaktywnoscKonczynyGorne;
    
    @Column(name = "KON_REAKTYWNOSC_KONCZYN_DOLNE")
    private boolean reaktywnoscKonczynyDolne;
    
    @ElementCollection
    @CollectionTable(
        name = "LIMB",
        joinColumns = @JoinColumn(name = "NURSE_DIAGNOSIS")
    )
    @Column(name = "KON_ODRUCH_BABINSKIEGO")
    private Set<Konczyna> odruchBabinskiego;

}
