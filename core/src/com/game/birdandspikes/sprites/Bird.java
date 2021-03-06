package com.game.birdandspikes.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.birdandspikes.BirdAndSpikes;

/**
 * Created by franek on 03.03.18.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private static final int JUMP_FORCE = 250;

    private Texture birdTexture;

    private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;

    private int directionStatus = 1;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdTexture = new Texture("bird.png");
        setBoundsForBird();
    }

    private void setBoundsForBird(){
        bounds = new Rectangle(position.x, position.y, birdTexture.getWidth(), birdTexture.getHeight());
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);

        velocity.scl(dt);
        position.add(directionStatus * MOVEMENT * dt, velocity.y, 0);

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }


    public boolean isTouchingRightSideOfTheScreen(){
        return position.x + birdTexture.getWidth() >= BirdAndSpikes.WIDTH;
    }

    public boolean isTouchingLeftSideOfTheScreen(){
        return position.x <= 0;

    }

    public void jump(){
        velocity.y = JUMP_FORCE;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void draw(SpriteBatch sb){
        sb.draw(birdTexture, position.x, position.y);
    }

    public void reverseFlyingDirection(){
        directionStatus *= -1;
    }

    public int getDirectionStatus() {
        return directionStatus;
    }
}
