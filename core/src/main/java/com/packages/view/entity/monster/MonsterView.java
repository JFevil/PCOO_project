package com.packages.view.entity.monster;

import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.EntityView;

public class MonsterView extends EntityView {

    public MonsterView(Monster monster) {
        super("assets/cutOrc1/tile000.png", monster);
    }
}
