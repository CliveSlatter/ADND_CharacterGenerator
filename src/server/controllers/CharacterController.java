package server.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("abilities/")
public class CharacterController {
    @POST
    @Path("scores")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String newAbilities(
            @FormParam("str_as") String str_as,
            @FormParam("dex_as") String dex_as,
            @FormParam("wis_as") String wis_as,
            @FormParam("int_as") String int_as,
            @FormParam("con_as") String con_as,
            @FormParam("cha_as") String cha_as
    ) {

        return "Ability scores successfully created" + str_as;
    }


}
