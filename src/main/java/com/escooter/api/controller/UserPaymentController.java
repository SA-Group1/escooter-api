package com.escooter.api.controller;

import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.BindCreditCardDTO;
import com.escooter.api.dto.BindMemberCardDTO;
import com.escooter.api.dto.UserCredentialsDTO;
import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.service.UserPaymentService;
import com.escooter.api.utils.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller for handling payment related requests.
 */
@RestController
@RequestMapping("/api")
public class UserPaymentController {
	@Autowired
	private UserPaymentService userPaymentService;

    /**
     * Binds credit card data to the user's account and returns a result message.
     * 
     * @param bindCreditCardDTO User and credit card data including CVV.
     * @return A ResponseEntity with HTTP status and message.
     */
	@PostMapping("bindCreditCard")
	public ResponseEntity<String> bindCreditCard(@RequestBody BindCreditCardDTO bindCreditCardDTO) {
		CreditCard creditCard = bindCreditCardDTO.getCreditCard();
		String cvv = bindCreditCardDTO.getCvv();
		UserCredentials userCredentials = bindCreditCardDTO.getUserCredentials();
		
		try {
			userPaymentService.bindCreditCard(userCredentials, creditCard , cvv);
			return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Bind credit card success."), HttpStatus.ACCEPTED);
		} catch (UserCredentialsException e){
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This credit card is already been used."), HttpStatus.CONFLICT);
		} catch (CardExpiredException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This credit card has expired."), HttpStatus.BAD_REQUEST);
		} catch (DateTimeParseException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid expiration date."), HttpStatus.BAD_REQUEST);
		} catch (CreditCardCvvException e){
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid cvv."), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Unbinding credit card data to user's account and returns a result message.
	 * 
	 * @param userCredentialsDTO The user's credentials.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("unbindCreditCard")
	public ResponseEntity<String> unbindCreditCard(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		try {
			userPaymentService.unbindCreditCard(userCredentialsDTO.getUserCredentials());
			return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("unbind credit card success."),HttpStatus.OK);
		} catch (UserCredentialsException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."),HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("unbind credit card failed."),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/**
	 * Adding member card data to database and returns a successful message.
	 * 
	 * @param bindMemberCardDTO Member card data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("bindMemberCard")
	public ResponseEntity<String> bindMemberCard(@RequestBody BindMemberCardDTO bindMemberCardDTO) {

		MemberCard memberCard = bindMemberCardDTO.getMemberCard();
		UserCredentials userCredentials = bindMemberCardDTO.getUserCredentials();
		
		try {
			userPaymentService.bindMemberCard(userCredentials, memberCard);
			return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Bind member card success."), HttpStatus.ACCEPTED);
		} catch (UserCredentialsException e){
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This member card is already been used."), HttpStatus.CONFLICT);
		} catch (CardExpiredException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This member card has expired."), HttpStatus.BAD_REQUEST);
		} catch (DateTimeParseException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid expiration date."), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Gets card information for the user.
	 * 
	 * @param userCredentialsDTO User's account.
	 * @return A ResponseEntity with http status and result message.
	 */
	@PostMapping("getUserPayment")
	public ResponseEntity<String> getUserPayment(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		try {

			JSONObject message = new JSONObject();
			CreditCard creditCard = userPaymentService.getCreditCard(userCredentialsDTO.getUserCredentials());
			MemberCard memberCard = userPaymentService.getMemberCard(userCredentialsDTO.getUserCredentials());
			
			ObjectMapper objectMapper = new ObjectMapper();
			message.put("creditCard",new JSONObject(objectMapper.writeValueAsString(creditCard)));
			message.put("memberCard",new JSONObject(objectMapper.writeValueAsString(memberCard)));
			
			return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("get user payment success.",message),HttpStatus.OK);
			
		} catch (JSONException | JsonProcessingException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("get user payment failed."),HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		}
	}
}