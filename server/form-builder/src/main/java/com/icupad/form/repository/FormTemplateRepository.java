package com.icupad.form.repository;

import com.icupad.form.model.FormTemplate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    Collection<FormTemplate> findByModuleName(String moduleName);

    FormTemplate findByModuleNameAndFormId(String moduleName, String formId);

}
