package com.sena.crud_basic.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class CaptchaService {

    private static final String SECRET_KEY = "6LeADR4rAAAAAACffbVl2wY9C2EwjbZEiEoCH94i";

    public boolean validateCaptcha(String recaptchaToken) {
        // URL para verificar el token del reCAPTCHA en el servidor de Google
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + SECRET_KEY + "&response=" + recaptchaToken;

        // Usamos RestTemplate para hacer la solicitud HTTP
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

        // Comprobar que la respuesta no sea nula
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String responseBody = response.getBody();
            // Validar que "success" esté en el cuerpo de la respuesta
            return responseBody.contains("\"success\": true");
        }

        // Si la respuesta no es exitosa o es nula, devolver falso
        return false;
    }
}
