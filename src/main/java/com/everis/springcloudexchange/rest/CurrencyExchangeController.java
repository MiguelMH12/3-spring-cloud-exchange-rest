package com.everis.springcloudexchange.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.everis.springcloudexchange.model.ExchangeValue;
import com.everis.springcloudexchange.responses.ExchangeValueResponse;
import com.everis.springcloudexchange.service.ExchangeValueService;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	ExchangeValueService exchangeValueService;
	
	//Entorno donde se ejecuta la aplicacion
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValueResponse retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValueResponse response = new ExchangeValueResponse();
		try {
			ExchangeValue valor = exchangeValueService.findByFromAndTo(from, to);
			if(valor == null) {
				response.setSuccessful(false);
				response.setMessage("Factor no encontrada");
				return response;
			} 
			//Para saber que servidor nos regresa
			valor.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			response.setValue(valor);
			response.setSuccessful(true);
			response.setMessage("Factor recuperado exitosamente");
		} catch(Exception e) {
			response.setSuccessful(false);
			response.setMessage(e.getMessage());
		}
		//Nos regresa el valor de la conversion
		return response;
	}

}
