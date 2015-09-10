package com.icupad.nurse.controller;

import com.icupad.nurse.model.NurseDiagnosis;
import com.icupad.nurse.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    private final NurseService nurseService;

	@Autowired
	public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<?> form(@RequestBody NurseDiagnosis diagnosis) {
        nurseService.save(diagnosis);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/diagnosis", method = RequestMethod.GET)
    public ResponseEntity<Collection<NurseDiagnosis>> findAllDiagnosis() {
        return new ResponseEntity<>(nurseService.findAll(), HttpStatus.OK);
    }

}
