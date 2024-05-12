package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.escooter.api.dto.CreditCardDTO;
import com.escooter.api.model.CreditCard;
import com.escooter.api.service.CreditCardService;

/**
 * Controller for handling credit card related requests.
 */
@RestController
public class CreditCardController {
	@Autowired
    private CreditCardService creditCardService;

	/**
	 * Adding credit card data to database and returns a successful message
	 *
	 * @param creditCardDTO Credit card data include cvv
	 * @param cvv Credit card cvv number
	 * @return A ResponseEntity with http status and message
	 */

	@PutMapping("/addCard")
	public ResponseEntity<String> addCard(@RequestBody CreditCardDTO creditCardDTO){

		// call service to add credit card
		CreditCard creditCard = new CreditCard(creditCardDTO.getCardNumber(), creditCardDTO.getExpirationDate(), creditCardDTO.getCardHolderName());
		creditCardService.addCard(creditCard,creditCardDTO.getCvv());

		// create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", true);
			message.put("message", "Adding credit card success");
		} catch (JSONException e) {
			e.printStackTrace();
		}

    	return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}
}
