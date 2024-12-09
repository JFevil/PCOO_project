package com.packages.controller.entity.monster;

import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;

public class MonsterController {
    private Monster monster;

    public MonsterController(Monster monster, Player player) {
        this.monster = monster;
    }

    public void updateMonsterPosition() {
        monster.move();
    }
}
