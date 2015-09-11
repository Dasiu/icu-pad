package com.icupad.form.service.impl;

import com.icupad.form.repository.FormTemplateRepository;
import com.icupad.form.service.FormService;
import com.icupad.form.model.FormTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FormServiceImpl implements FormService {

    private final FormTemplateRepository formTemplateRepository;
    
    @Autowired
    public FormServiceImpl(FormTemplateRepository formTemplateRepository) {
        this.formTemplateRepository = formTemplateRepository;
    }

    @Override
    public Collection<FormTemplate> findByModule(String module) {
        return formTemplateRepository.findByModuleName(module);
    }

    @Override
    public FormTemplate saveTemplate(FormTemplate template) {
        return formTemplateRepository.save(template);
    }

    @Override
    public FormTemplate findByModuleAndId(String module, String formId) {
        return formTemplateRepository.findByModuleNameAndFormId(module, formId);
    }

}
