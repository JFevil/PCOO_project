package com.packages.factory.movableEntity.monster;

import com.packages.controller.entity.movableEntity.monster.MonsterController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.movableEntity.monster.MonsterView;
import com.packages.model.entity.movableEntity.player.Player;

public interface MonsterFactory {
    MonsterView createView(Monster monster);
    Monster createModel(float x, float y, float speed, Player player);
    MonsterController createController(Monster model, MonsterView view);
}
