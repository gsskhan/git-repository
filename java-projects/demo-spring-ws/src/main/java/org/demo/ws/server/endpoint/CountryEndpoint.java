package org.demo.ws.server.endpoint;

import org.demo.ws.server.constant.AppConstants;
import org.demo.ws.server.repository.CountryRepository;
import org.demo.ws.server.stubs.countries.GetCountryRequest;
import org.demo.ws.server.stubs.countries.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint implements AppConstants {	

	@Autowired
	private CountryRepository countryRepository;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		return response;
	}

}
