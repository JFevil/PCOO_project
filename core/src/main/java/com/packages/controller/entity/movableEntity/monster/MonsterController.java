package com.packages.controller.entity.movableEntity.monster;

import com.packages.controller.Controller;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.movableEntity.monster.MonsterView;

public class MonsterController extends Controller {

    public MonsterController(Monster monster, MonsterView monsterView) {
        super(monster, monsterView);
    }

    public void updateMonsterPosition() {
        ((Monster)getModel()).move();
    }
}
