package com.icupad.common.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icupad.common.service.FormService;
import com.icupad.form.model.FormTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/form")
public class FormController {
    
    private static final String FORM_ID = "form_domain";

    private static final String FORM_NAME = "form_name";

    private static final TypeReference<HashMap<String,Object>> MAP_REF = new TypeReference<HashMap<String,Object>>() {};
    
    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @RequestMapping(value = "/model/{module}/{modelName}", method = RequestMethod.GET)
    public ResponseEntity<?> getFormTemplate(@PathVariable("module") String module, @PathVariable("modelName") String modelName) {
        try {
            throwExceptionIfNotValid(module, modelName);
            return new ResponseEntity<>(Class.forName("com.icupad." + module + ".model." + modelName).getDeclaredFields(), HttpStatus.OK);
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{module}/{formId}", method = RequestMethod.GET)
    public ResponseEntity<String> getForm(@PathVariable("module") String module, @PathVariable("formId") String formId) {
        return new ResponseEntity<>(formService.findByModuleAndId(module, formId).getBody(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{module}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getModuleTemplates(@PathVariable("module") String module) {
        return new ResponseEntity<>(formService.findByModule(module).stream().collect(Collectors.toMap((FormTemplate t) -> t.getFormId(), t -> t.getFormName())), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{module}", method = RequestMethod.POST)
    public ResponseEntity<FormTemplate> addTemplate(@PathVariable("module") String module, @RequestBody String content) throws IOException {
        FormTemplate template = createTemplate(module, content);
        return new ResponseEntity<>(formService.saveTemplate(template), HttpStatus.CREATED);
    }

    private void throwExceptionIfNotValid(String... strings) {
        for (String string : strings) {
            if (!string.matches("[a-zA-Z_]+")) {
                throw new IllegalArgumentException("Invalid value: '" + string + "'");
            }
        }
    }

    private FormTemplate createTemplate(String module, String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> extractedContent = mapper.readValue(content, MAP_REF);
        if (!extractedContent.containsKey(FORM_NAME) || !extractedContent.containsKey(FORM_ID)) {
            throw new IllegalArgumentException("Form model is not valid");
        }
        FormTemplate template = new FormTemplate();
        template.setFormId(extractedContent.get(FORM_ID));
        template.setFormName(extractedContent.get(FORM_NAME));
        template.setModuleName(module);
        template.setBody(content);
        return template;
    }


}
