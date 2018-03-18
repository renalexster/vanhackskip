package com.skip.orderapi.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Component;

import com.skip.orderapi.exception.AuthException;

import net.minidev.json.JSONObject;

@Component
public class JWTService {
	public static final String SECRET = "ANJZbXK6oPnigjfVE44dYBwnYZ5iHcLcsLBFnYcJW9iVjyQ6tp3gLzROKZeErDjWmbo5d3n78e2w9xRR15jKXTzxOTrlkVLmiLMK0XFVgs6OdLbdwEzLOC2lJweqNsUW/M/mw51a2krk9QL5v45xhUs2z9m/lHraoZHykJT5Ovb9Q9xsB3pVxkrSDirx89onEsAmudGag9itd2oD8sxZ39vJuTu1n9MUouFm5L1FXpdRvi4/HVLJmEDQmP4knhTo/ESxmRPYFHhmArK97pJ66mPenLSRz6OyQvaNtxFhrG7DH2mQFWYM3+djIedf9MugooaZZ1wljIuwJMhSM9CYXg==";
	public static final String ISSUER = "sds_ati";
	public static final String SUBJECT = "alerta_celular";
	public static final String JWT_TYPE = "JWT";
	public static final String BEARER_TYPE = "Bearer";
	public static final long TT_MINUTES = 60; // 60 minutos

	public String createToken(@Body JSONObject jsonLoggedUser, Exchange exchange) throws UnsupportedEncodingException, JoseException {		
		String name = jsonLoggedUser.getAsString("name");
		String email = jsonLoggedUser.getAsString("email");
		Number id = jsonLoggedUser.getAsNumber("id");

		JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(TT_MINUTES);
        claims.setSubject(SUBJECT);
        claims.setIssuer(ISSUER);
        claims.setClaim("email", email);
        claims.setClaim("id", id);
        claims.setClaim("name", name);
        
        Key key = new HmacKey(SECRET.getBytes("UTF-8"));

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);
        jws.setDoKeyValidation(false);

        JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "JWT");
		jsonObject.put("key", jws.getCompactSerialization());
		
		System.out.println(jsonObject);

		return jsonObject.toJSONString();
	}

	
	public String parseToken(@Header("Authorization") String jwt) throws UnsupportedEncodingException, InvalidJwtException, MalformedClaimException {
		Key key = new HmacKey(SECRET.getBytes("UTF-8"));
		String token = "";

		if (jwt == null) {
			throw new AuthException("Token not found");
		}

		String[] splitToken = jwt.split(" ");
		if (splitToken == null || splitToken.length != 2 || !splitToken[0].equals(BEARER_TYPE)) {
			throw new AuthException("Invalid Token");
		}

		token = splitToken[1];

		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer(ISSUER)
                .setVerificationKey(key)
                .setRelaxVerificationKeyValidation()
                .build();

        JwtClaims processedClaims = jwtConsumer.processToClaims(token);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", processedClaims.getClaimValue("id", Number.class));
		jsonObject.put("name", processedClaims.getClaimValue("name", String.class));
		jsonObject.put("email", processedClaims.getClaimValue("email", String.class));

		return jsonObject.toJSONString();
	}
}
