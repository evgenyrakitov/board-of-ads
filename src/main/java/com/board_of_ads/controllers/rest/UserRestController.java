package com.board_of_ads.controllers.rest;

import com.board_of_ads.exceptions.ReRegistrationException;
import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.EmailService;
import com.board_of_ads.service.interfaces.RegionService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final RoleService roleService;
    private final CityService cityService;
    private final RegionService regionService;
    private final MessageSource messages;
    private final EmailService emailService;
    private final Environment env;

    /**
     * Метод для регистрации новых пользователей.
     * Часть логики проверки введённых в форму данных
     * находится в registration.js и modal_registration.js.
     * После сохранения User в базу он так же автоматически авторизуется.
     */
    @CrossOrigin()  //далее - поправить, сделано чтобы работала страничка
    @ApiOperation(value = "create new User", code = 201, response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully create user")})
    @PostMapping(value = "/registration", consumes = {"application/json"})
    public ResponseEntity<User> registration(@RequestBody User user) {
        if (userService.findUserByEmail(user.getEmail()) != null ||
                userService.findUserByPhone(user.getPhone()) != null) {
            throw new ReRegistrationException("User is already registered");
        }
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findRoleByName("USER");
        roleSet.add(role);
        user.setRoles(roleSet);
        user.setRegion(regionService.findById(user.getRegion().getId()));
        user.setCity(cityService.findById(user.getCity().getId()));
        User newUser = userService.addUser(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null,
                newUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);

        return responseEntity;

    }

    @PostMapping(value = "/admin/add")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/edit", consumes = {"application/json"})
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.updateUser(user);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/getCities")
    public ResponseEntity<List<City>> getCities(@RequestBody Region region) {
        List<City> cities = cityService.findAllByRegionId(region.getId());
        return ResponseEntity.ok(cities);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/user/favoritePostings/add")
    public void addFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.addFavoritePosting(id, user.getId());
    }

    @PostMapping("/user/favoritePostings/delete")
    public void deleteFavoritePosting(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteFavoritePosting(id, user.getId());
    }

    @PostMapping("/user/favoritePostings/delete/all")
    public void deleteAllFavoritePostings(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteAllFavoritePosting(user.getId());
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@RequestParam("email") String userEmail,
                                                 Locale locale) throws MessagingException {
        User user = userService.findUserByEmail(userEmail);
        boolean boll = (user != null);
        if (boll) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            String bodyHTML = "<!DOCTYPE html>" +
                    "<html lang='en'>" +
                    "<head>" +
                    "<meta charset='UTF-8'>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Reset Password</h1>" +
                    "<span>Click on the link to reset the password </span>"+
                    "<a href='http://"+Objects.requireNonNull(env.getProperty("server.domain"))+":"+
                    Objects.requireNonNull(env.getProperty("server.port"))+
                    "/reset/changePassword?token="+token+"'>Reset Password</a></body></html>";
            emailService.sendHTMLEmail("Reset Password",bodyHTML, user);
        }
        return new ResponseEntity<>(boll, HttpStatus.OK);
    }

}
