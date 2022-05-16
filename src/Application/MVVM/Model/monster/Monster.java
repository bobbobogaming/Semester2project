package Application.MVVM.Model.monster;

import Application.MVVM.Model.character.Stats;

import java.util.ArrayList;

public class Monster {
    private Stats monsterStats;
    private int maxHP, ac;
    private double cr;
    private String monsterName;
    private ArrayList<Action> monsterAction;

    public Monster(Stats monsterStats, int maxHP, int ac, double cr, String monsterName, ArrayList<Action> monsterAction) {
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

    public double getCr() {
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
}
