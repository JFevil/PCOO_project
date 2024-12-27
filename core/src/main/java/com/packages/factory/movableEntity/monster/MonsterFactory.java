package com.packages.factory.movableEntity.monster;

import com.packages.controller.entity.monster.MonsterController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.monster.MonsterView;

public class MonsterFactory {
    public MonsterController createMonster(Factory factory, float x, float y, float speed, Player player) {
        Monster model = factory.createModel(x, y, speed, player);
        MonsterView view = factory.createView(model);
        return factory.createController(model, view);
    }
}
