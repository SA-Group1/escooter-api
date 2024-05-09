package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.CreditCardModel;
import com.escooter.api.service.CreditCardService;

@RestController
public class CreditCardController {

		@Autowired
        CreditCardModel creditCardModel;
		
		@Autowired
        CreditCardService creditCardService;

	    @RequestMapping("/addCard")
	    public String hello(){
            creditCardModel = new CreditCardModel();
            creditCardModel.setCreditCard_ID("0001250030004512","24 07");
            creditCardService.addCard(creditCardModel);
	        return "New Card added";
	    }
}
