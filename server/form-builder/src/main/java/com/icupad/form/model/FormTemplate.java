package com.icupad.form.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FormTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String formId;

    @Column
    private String moduleName;

    @Column
    private String formName;
    
    @Column(length = 100_000)
    private String body;

}
