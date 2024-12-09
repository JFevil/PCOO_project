package com.packages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.packages.controller.entity.monster.MonsterController;
import com.packages.controller.entity.player.PlayerController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.packages.view.entity.monster.MonsterView;
import com.packages.view.entity.player.PlayerView;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private Monster monster1;
    private Monster monster2;
    private PlayerView playerView;
    private MonsterView monsterView1;
    private MonsterView monsterView2;
    private PlayerController playerController;
    private MonsterController monsterController1;
    private MonsterController monsterController2;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Initialisation des modèles
        player = new Player(400, 400, 100, 100, 300, 50, 10);
        monster1 = new Monster(300, 300, 100, 100, 100, 50, 5, player);
        monster2 = new Monster(100, 100, 100, 100, 150, 50, 5, player);

        // Initialisation des vues
        playerView = new PlayerView(player);
        monsterView1 = new MonsterView(monster1);
        monsterView2 = new MonsterView(monster2);

        // Initialisation des contrôleurs
        playerController = new PlayerController(player);
        monsterController1 = new MonsterController(monster1, player);
        monsterController2 = new MonsterController(monster2, player);
    }

    @Override
    public void render() {
        playerController.input();
        monsterController1.updateMonsterPosition();
        monsterController2.updateMonsterPosition();

        // Rendu graphique
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        playerView.render(batch);
        monsterView1.render(batch);
        monsterView2.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerView.dispose();
        monsterView1.dispose();
        monsterView2.dispose();
    }
}
