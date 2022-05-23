package Application.MVVM.Model.monster;

import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.initWrapper.IStatFormat;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster implements Serializable, IStatFormat {
    private Stats monsterStats;
    private int ac;
    private String cr;
    private String monsterName;
    private ArrayList<Action> monsterAction;

    public Monster(Stats monsterStats, int ac, String cr, String monsterName, ArrayList<Action> monsterAction) {
        this.monsterStats = monsterStats;
        this.ac = ac;
        this.cr = cr;
        this.monsterName = monsterName;
        this.monsterAction = monsterAction;
    }


    public Stats getMonsterStats() {
        return monsterStats;
    }

    public int getMaxHP() {
        return monsterStats.getMaxHP();
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

    @Override
    public String getFormattedStats(){
        return String.format("Max HP: %d",monsterStats.getMaxHP()) + "\n"
            + String.format("STR: %d (%d)",monsterStats.getStrength(),monsterStats.getStrengthModifier()) + "\n"
            + String.format("DEX: %d (%d)",monsterStats.getDexterity(),monsterStats.getDexterityModifier()) +"\n"
            + String.format("CON: %d (%d)",monsterStats.getConstitution(),monsterStats.getConstitutionModifier()) +"\n"
            + String.format("INT: %d (%d)",monsterStats.getIntelligence(),monsterStats.getIntelligenceModifier()) +"\n"
            + String.format("WIS: %d (%d)",monsterStats.getWisdom(),monsterStats.getWisdomModifier()) +"\n"
            + String.format("CHA: %d (%d)",monsterStats.getCharisma(),monsterStats.getCharismaModifier());
    }

    @Override public String toString() {
        return "[" + cr + "] " + monsterName;
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
        && otherMonster.getCr().equals(cr));
    }
}