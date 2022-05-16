package Application.MVVM.Model.monster;

import java.io.Serializable;

public class Action implements Serializable {
    private String description;
    private String actionName;

    public Action(String description, String actionName) {
        this.description = description;
        this.actionName = actionName;
    }

    public String getDescription() {
        return description;
    }

    public String getActionName() {
        return actionName;
    }

    public Action copy()
    {
        return new Action(description, actionName);
    }

}
