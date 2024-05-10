package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.CreditCard;
import com.escooter.api.service.CreditCardService;

@RestController
public class CreditCardController {

		@Autowired
        CreditCard creditCard;
		
		@Autowired
        CreditCardService creditCardService;

	    @RequestMapping("/addCard")
	    public String hello() {
            creditCard = new CreditCard("01234567891234","24 07", "1234");
            creditCardService.addCard(creditCard);
	        return "New Card added";
	    }
}
