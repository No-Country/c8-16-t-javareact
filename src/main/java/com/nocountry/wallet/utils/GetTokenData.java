package com.nocountry.wallet.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class GetTokenData {

    public static Long getUserIdFromToken(String token) throws ParseException {

        // decodifico y divido el token en header y payload.
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        JSONParser parser = new JSONParser();
        JSONObject payload_json = (JSONObject) parser.parse(payload);

        Long user_id = Long.parseLong(payload_json.get("userId").toString());
        //JSONObject auth_json = (JSONObject) parser.parse(payload_json.get("auth").toString());

        return user_id;

    }

}