package com.terminal.restController;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.terminal.model.Terminal;
import com.terminal.service.terminalService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api") 
public class terminalRestController {
	private static final Logger logger = Logger
			.getLogger(terminalRestController.class);
	
	@Autowired
	private terminalService service;
	
	
	 @RequestMapping(value="/getTerminal",
	            method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	    public  ResponseEntity<Terminal>  getTerminal()
	    {
		 Terminal terminal = service.getTerminal();
		 if(terminal==null){
			 return new ResponseEntity<Terminal>(terminal,HttpStatus.SERVICE_UNAVAILABLE);
		 }else{
			 return new ResponseEntity<Terminal>(terminal,HttpStatus.OK);
		 }
		  
	    }
	 
	 @RequestMapping(value="/releaseTerminals",
	            method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	    public  ResponseEntity<String>  releaseTerminal()
	    {
		 boolean flagRelease = service.releaseTerminals();
		 if(flagRelease==false){
			 return new ResponseEntity<String>("{msg : Failed}",HttpStatus.SERVICE_UNAVAILABLE);
		 }else{
			 return new ResponseEntity<String>("{msg : Sucess}",HttpStatus.OK);
		 }
		  
	    }
	

}
