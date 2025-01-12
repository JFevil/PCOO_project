package com.packages.factory.movableEntity.monster;

import com.packages.controller.entity.movableEntity.monster.MonsterController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.movableEntity.monster.MonsterView;

public class MonsterSpawner {
    public static MonsterController createMonster(MonsterFactory monsterFactory, float x, float y, float speed, Player player) {
        Monster model = monsterFactory.createModel(x, y, speed, player);
        MonsterView view = monsterFactory.createView(model);
        return monsterFactory.createController(model, view);
    }
}
