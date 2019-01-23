package server.controllers;

import server.models.service.AbilityBonusService;
import server.models.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("ability/")
public class AbilityBonusController {

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public int getUser(@FormParam("str_as") int str_as) {

        int bonus = AbilityBonusService.selectByBase(str_as);

        if (bonus == 0) {
            return 0;
        } else {
            return bonus;
        }
    }
}
