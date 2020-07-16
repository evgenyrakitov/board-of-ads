package com.board_of_ads.controllers.auth;

import com.board_of_ads.service.interfaces.OAuthNetworkService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для авторизации в вконтакте.
 * Информация взята от сюда https://vk.com/dev/auth_sites
 */
@Controller
@RequestMapping("/login/vk")
public class VKoAuthController {

    private static final Logger logger = LoggerFactory.getLogger(VKoAuthController.class);
    private final OAuthNetworkService OAuthNetworkService;

    @Value("${vk.clientId}")
    private String clientId;
    @Value("${vk.clientSecret}")
    private String clientSecret;
    @Value("${vk.userAuthorizationUri}")
    private String userAuthorizationUri;
    @Value("${vk.accessTokenUri}")
    private String accessTokenUri;
    @Value("${vk.scope}")
    private String scope;
    @Value("${vk.apiVersion}")
    private String apiVersion;

    public VKoAuthController(OAuthNetworkService OAuthNetworkService) {
        this.OAuthNetworkService = OAuthNetworkService;
    }

    /**
     * Начальная страница, с которой начинается авторизация.
     * Подтягиваются данные для ВК и пользователь отправляется на сервер ВК для авторизации.
     */
    @GetMapping("/auth")
    public String authPage(HttpServletRequest request) {
        StringBuilder redirectURL = new StringBuilder(userAuthorizationUri).append("?")
                .append("client_id=").append(clientId).append("&")
                .append("scope=").append(scope).append("&")
                .append("redirect_uri=http://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/login/vk").append("&")
                .append("response_type=code").append("&")
                .append("v=").append(apiVersion);

        return "redirect:" + redirectURL.toString();
    }

    /**
     * Посадочная страница куда сервер ВК перенаправляет пользователя с кодом для получения токена.
     * Получаем тут токен и e-mail, проверяем есть ли уже такой пользователь в бд, если нет, то создаем.
     * Далее авторизуем данного пользователя в секьюрити и отправляем на главную страницу.
     *
     * @param code - Код для получения токена, его даёт сервер ВК.
     */
    @SneakyThrows
    @GetMapping("")
    public String landingPage(HttpServletRequest request, @RequestParam String code) {

        StringBuilder getTokenURI = new StringBuilder(accessTokenUri).append("?")
                .append("client_id=").append(clientId).append("&")
                .append("client_secret=").append(clientSecret).append("&")
                .append("code=").append(code).append("&")
                .append("redirect_uri=http://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/login/vk");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getTokenURI.toString(), String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            String token = root.path("access_token").textValue();
            String vkUserId = String.valueOf(root.path("user_id").intValue());
            String email = root.path("email").textValue();

            OAuthNetworkService.doAutoLogin(OAuthNetworkService.findOrCreateUserVK(token, vkUserId, email));
        }

        return "redirect:/";
    }
}
