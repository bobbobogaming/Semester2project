package Database.Adapters;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Application.MVVM.Model.monster.Action;
import Application.MVVM.Model.monster.Monster;
import Database.DataBaseConnector;
import Database.IDatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetMonsterFromDataBaseView {
    public ArrayList<Monster> getData(){

        ArrayList<Monster> monsterArrayList = new ArrayList<>();
        ArrayList<Action> actions;
        IDatabaseConnector iDatabaseConnector = new DataBaseConnector();

        ResultSet rs = null;
        try {
            rs = iDatabaseConnector.selectAllDataFromTable("viewmonsterall");

            actions = new ArrayList<>();

            String oldMonstername = "";
            Monster oldMonster = null;

            while (rs.next()) {
                int constitution = rs.getInt("constitution");
                int wisdom = rs.getInt("wisdom");
                int charisma = rs.getInt("charisma");
                int intelligence = rs.getInt("intelligence");
                int strength = rs.getInt("strength") ;
                int dexterity = rs.getInt("Dexterity");

                int ac = rs.getInt("ac");
                String speed = rs.getString("speed");
                int maxHp = rs.getInt("maxHp");
                String monsterName = rs.getString("monstername");
                String cr = rs.getString("cr");

                String description = rs.getString("description");
                String actionName = rs.getString("actionname");

                Action action = new Action(description,actionName);

                if (oldMonstername.equals(monsterName)){
                    actions.add(action);
                    Stats stats = new Stats(strength, dexterity, constitution, intelligence, wisdom, charisma, maxHp);
                    oldMonster = new Monster(stats, ac, cr, monsterName, actions);

                }else if (oldMonstername.equals("")){
                    oldMonstername = monsterName;
                    actions.add(action);
                    Stats stats = new Stats(strength, dexterity, constitution, intelligence, wisdom, charisma, maxHp);
                    oldMonster = new Monster(stats, ac, cr, monsterName, actions);
                }else
                {
                    monsterArrayList.add(oldMonster);
                    actions = new ArrayList<>();
                    actions.add(action);
                    oldMonstername = monsterName;
                    Stats stats = new Stats(strength, dexterity, constitution, intelligence, wisdom, charisma, maxHp);
                    oldMonster = new Monster(stats, ac, cr, monsterName, actions);
                }
            }

            monsterArrayList.add(oldMonster);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return monsterArrayList;
    }
}
