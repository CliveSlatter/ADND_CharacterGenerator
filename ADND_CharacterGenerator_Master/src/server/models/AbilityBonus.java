package server.models;

import org.json.simple.JSONObject;

public class AbilityBonus {
    private int base;
    private int bonus;

    public AbilityBonus(int base, int bonus) {
        this.base = base;
        this.bonus = bonus;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("base", getBase());
        j.put("bonus", getBonus());
        return j;
    }
}
