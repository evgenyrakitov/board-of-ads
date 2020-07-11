package com.board_of_ads.controllers.auth;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login/vk")
public class vkAuthController {
    private static final Logger logger = LoggerFactory.getLogger(vkAuthController.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
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
    @Value("${vk.userInfoUri}")
    private String userInfoUri;
    @Value("${vk.apiVersion}")
    private String apiVersion;

    public vkAuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

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
        System.out.println(response.getBody());

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            String token = root.path("access_token").toString();
            String vkUserId = root.path("user_id").toString();
            String email = root.path("email").toString();
            checkAndAuthVkUser(token, vkUserId, email);
        }

        return "redirect:/";
    }

    private void checkAndAuthVkUser(String token, String vkUserId, String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {

        }

    }
}
