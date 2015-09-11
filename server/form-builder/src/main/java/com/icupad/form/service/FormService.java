package com.icupad.form.service;

import com.icupad.form.model.FormTemplate;

import java.util.Collection;

public interface FormService {

    Collection<FormTemplate> findByModule(String module);

    FormTemplate saveTemplate(FormTemplate template);

    FormTemplate findByModuleAndId(String module, String formId);

}
