package net.productsellint.api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.productsellint.business.abstracts.UserService;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.core.utilities.results.SuccessResult;
import net.productsellint.dataTransferObjects.concretes.UserDto;
import net.productsellint.dataTransferObjects.concretes.UserRequest;
import net.productsellint.entities.concretes.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/addStock")
    ResponseEntity add(@RequestBody UserRequest userRequest) {
        this.userService.save(userRequest);
        return ResponseEntity.status(200).body("Kullanıcı eklendi.");
    }

    @GetMapping("/deleteStock")
    ResponseEntity delete(@RequestParam Integer id) {
        this.userService.delete(id);
        return ResponseEntity.status(200).body("Kullanıcı silindi.");

    }

    @GetMapping("/activateStock")
    ResponseEntity activateStock(@RequestParam Integer id) {
        this.userService.activate(id);
        return ResponseEntity.status(200).body(new SuccessResult("Kullanıcı aktif."));
    }

    @GetMapping("/disableStock")
    ResponseEntity<Result> disable(@RequestParam Integer id) {
        this.userService.disable(id);
        return ResponseEntity.status(200).body(new SuccessResult("Kullanıcı devre dışı."));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping("/token/refreshtoken")
    public void refreshToken(@RequestBody HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                //UserDto userDto = this.userService.getUser(username);
                UserEntity userEntity = mapper.map(userService.getUser(username), UserEntity.class);
                /*String access_token = JWT.create()
                        .withSubject(userEntity.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                String refresh_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                response.setHeader("access_token", access_token);
                response.setHeader("refresh_token", refresh_token);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/
            } catch (Exception exception) {/*
                log.info("Error logging in {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);*/
            }
        } else {
            throw new  RuntimeException("Refresh token is missing");
        }
    }
}
