package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Skill {

    public static ArrayList<Skill> skills = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (Skill s: skills) {
            if (s.getId() > id) {
                id = s.getId();
            }
        }
        return id + 1;
    }

    private int id;
    private String skillName;
    private String keyAbility;


    public Skill(int id, String skillName, String keyAbility) {
        this.id = id;
        this.skillName = skillName;
        this.keyAbility = keyAbility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getKeyAbility() {
        return keyAbility;
    }

    public void setKeyAbility(String keyAbility) {
        this.keyAbility = keyAbility;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("skillName", getSkillName());
        j.put("keyAbility", getKeyAbility());
        return j;
    }
}
