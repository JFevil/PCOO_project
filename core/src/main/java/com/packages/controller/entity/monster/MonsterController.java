package com.packages.controller.entity.monster;

import com.packages.controller.Controller;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.EntityView;
import com.packages.view.entity.monster.MonsterView;

public class MonsterController implements Controller {
    private Monster model;
    private MonsterView view;

    public MonsterController(Monster monster, MonsterView monsterView) {
        this.model = monster;
        this.view = monsterView;
    }

    public void updateMonsterPosition() {
        model.move();
    }

    public MonsterView getView() {
        return view;
    }

    public Monster getModel() {
        return model;
    }
}
