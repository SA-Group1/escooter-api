package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.CreditCard;
import com.escooter.api.service.CreditCardService;

@RestController
public class CreditCardController {

		@Autowired
        CreditCard creditCardModel;
		
		@Autowired
        CreditCardService creditCardService;

	    // @RequestMapping("/addCard")
	    // public String hello(){
        //     creditCardModel = new CreditCard();
        //     creditCardModel.setCreditCard_ID("0001250030004512","24 07");
        //     creditCardService.addCard(creditCardModel);
	    //     return "New Card added";
	    // }
}
