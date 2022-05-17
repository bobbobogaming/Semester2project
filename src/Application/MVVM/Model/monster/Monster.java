package Application.MVVM.Model.monster;

import Application.MVVM.Model.character.Stats;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster implements Serializable {
    private Stats monsterStats;
    private int maxHP, ac;
    private String cr;
    private String monsterName;
    private ArrayList<Action> monsterAction;

    public Monster(Stats monsterStats, int maxHP, int ac, String cr, String monsterName, ArrayList<Action> monsterAction) {
        this.monsterStats = monsterStats;
        this.maxHP = maxHP;
        this.ac = ac;
        this.cr = cr;
        this.monsterName = monsterName;
        this.monsterAction = monsterAction;
    }


    public Stats getMonsterStats() {
        return monsterStats;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAc() {
        return ac;
    }

    public String getCr() {
        return cr;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public ArrayList<Action> getMonsterAction() {
        ArrayList<Action> monsterActions = new ArrayList<>();
        for (Action action : monsterAction) {
            monsterActions.add(action.copy());
        }
        return monsterActions;
    }

    @Override public String toString() {
        return monsterName;
    }
    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Monster)) {
            return false;
        }
        Monster otherMonster = (Monster) obj;
        return  (otherMonster.getMonsterName().equals(monsterName)
        && otherMonster.getMonsterAction().equals(monsterAction)
        && otherMonster.getMonsterStats().equals(monsterStats)
        && otherMonster.getAc() == ac
        && otherMonster.getCr() == cr
        && otherMonster.getMaxHP() == maxHP);
    }
}