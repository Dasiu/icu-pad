package com.icupad.common.controller;

import com.icupad.common.service.FormService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class FormControllerTest {

    private static final String VALID_MODULE = "test_module";
    private static final String VALID_MODEL_CLASS_NAME = "TestModel";

    @Test
    public void shouldReturn200WhenRequestForExistingClassDescription() {
        //given
        FormService mockFormService = Mockito.mock(FormService.class);
        FormController formController = new FormController(mockFormService);
        //when
        ResponseEntity<?> response = formController.getFormTemplate(VALID_MODULE, VALID_MODEL_CLASS_NAME);
        //then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void shouldReturn404WhenRequestForNotExistingClassDescription() {
        //given
        FormService mockFormService = Mockito.mock(FormService.class);
        FormController formController = new FormController(mockFormService);
        //when
        ResponseEntity<?> response = formController.getFormTemplate(VALID_MODULE, "NoSuchClass");
        //then
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    public void shouldThrowExceptionWhenModuleNameContainsIllegalCharacter() {
        //given
        FormService mockFormService = Mockito.mock(FormService.class);
        FormController formController = new FormController(mockFormService);
        //when
        try {
            formController.getFormTemplate("invalid.module", VALID_MODEL_CLASS_NAME);
            //then
            fail();
        } catch (IllegalArgumentException e) {}
        //then
    }

    @Test
    public void shouldThrowExceptionWhenClassNameContainsIllegalCharacter() {
        //given
        FormService mockFormService = Mockito.mock(FormService.class);
        FormController formController = new FormController(mockFormService);
        //when
        try {
            formController.getFormTemplate(VALID_MODULE, "not.a.Class");
            //then
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

}
