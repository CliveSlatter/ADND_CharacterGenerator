package server.controllers;

import server.Console;
import server.models.User;
import server.models.service.UserService;
import server.models.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("user/")
public class UserController {

    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String newMessage(@FormParam("username") String username,
                             @FormParam("firstName") String firstName,
                             @FormParam("lastName") String lastName,
                             @FormParam("password") String password1,
                             @FormParam("password2") String password2) {
        Console.log("/user/new - Creating " + username);
        UserService.selectAllInto(User.users);
        for (User u : User.users) {
            if (u.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return "Error: Username already exists";
            }
        }
        if (!password1.equals(password2)) {
            return "Error: Passwords do not match.";
        }
        String token = UUID.randomUUID().toString();
        int userId = User.nextId();
        int salt = (int) (Math.random() * 8999999) + 1000000;
        String hash = User.GenerateHash(password1, salt);
        String success = UserService.insert(new User(userId, username, firstName, lastName, salt, hash, token));
        if (success.equals("OK")) {
            return token;
        } else {
            return "Error: Can't create new user.";
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String newMessage(@FormParam("username") String username,
                             @FormParam("password") String password) {

        Console.log("/user/login - Attempt by " + username);
        UserService.selectAllInto(User.users);
        for (User u : User.users) {
            if (u.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (!u.getHash().equals(User.GenerateHash(password, u.getSalt()))) {
                    return "Error: Incorrect password";
                }
                String token = UUID.randomUUID().toString();
                u.setSessionToken(token);
                String success = UserService.update(u);
                if (success.equals("OK")) {
                    return token;
                } else {
                    return "Error: Can't create session token.";
                }
            }
        }
        return "Error: Can't find user account.";
    }

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@CookieParam("sessionToken") Cookie sessionCookie) {

        String currentUser = UserService.validateSessionCookie(sessionCookie);

        if (currentUser == null) {
            return "";
        } else {
            return currentUser;
        }
    }

}

