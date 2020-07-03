package com.avito.controllers.rest;

import com.avito.extensions.CustomMessageSource;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/i18n")
public class I18nRest {

    private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);

    private final MessageSource messageSource;

    @GetMapping("/{prefix}")
    public ResponseEntity<Map<String, String>> getMessages(@PathVariable String prefix, Locale locale) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry e : ((CustomMessageSource) messageSource).getMessages(locale).entrySet()) {
            if (e.getKey().toString().startsWith(prefix)) {
                map.put(e.getKey().toString(), e.getValue().toString());
            }
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
