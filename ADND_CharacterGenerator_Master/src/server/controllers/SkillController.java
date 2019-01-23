package server.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Console;
import server.models.Skill;
import server.models.service.SkillService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("skills/")
public class SkillController {
        @GET
        @Path("list")
        @Produces(MediaType.APPLICATION_JSON)
        public String listSkills() {

            Console.log("/skill/list - Getting all skills from database");

            String status = SkillService.selectAllInto(Skill.skills);

            if (status.equals("OK")) {

                JSONArray skillsList = new JSONArray();
                for (Skill s: Skill.skills) {

                    JSONObject jo = s.toJSON();
                    skillsList.add(jo);

                }

                return skillsList.toString();

            } else {

                System.out.println("An error occurred! " + status);
                return "";
            }
        }
}
