package org.emg.stormRage.characters;


import org.emg.stormRage.managers.ResourceManager;
import org.emg.stormRage.util.Constants;
import org.emg.stormRage.util.Posiciones;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


public class Player extends Character{
	public enum State{
		RIGHT, LEFT, UP, DOWN, IDLE_RIGHT, IDLE_LEFT, IDLE_UP, IDLE_DOWN,
		ATTACK_RIGHT, ATTACK_LEFT, ATTACK_UP, ATTACK_DOWN
	}
	
	public State state;
	
	public enum Posicion{
		RIGHT, LEFT, UP, DOWN
	}
	public Posicion posicion;
	
	
	Animation animationRight;
	Animation animationLeft;
	Animation animationUp;
	Animation animationDown;
	
	Animation idleFrame_Right;
	Animation idleFrame_Left;
	Animation idleFrame_Up;
	Animation idleFrame_Down;
	
	TextureRegion idleFrame;
	
	Animation animationAttackRight;
	Animation animationAttackLeft;
	Animation animationAttackUp;
	Animation animationAttackDown;
	
	
	float posicionx;
	float posiciony;
	
	//Valores del heroe;
	public static float hp;
	public static float attackSpeed;
	public static float strength;
	public static float speed;
	public static float cooldown = 30;

	public boolean damage = true;
	public boolean movimiento = false;
	
	public Player(TextureRegion texture, float x, float y, float speed, float hp, float attackspeed, float strength) {
		super(texture, x, y);
		
		idleFrame = texture;
		
		idleFrame_Right =ResourceManager.getAnimation("player_Idle_right");
		idleFrame_Left =ResourceManager.getAnimation("player_Idle_left");
		idleFrame_Up = ResourceManager.getAnimation("player_Idle_up");
		idleFrame_Down =ResourceManager.getAnimation("player_Idle_down");
		
		animationRight = ResourceManager.getAnimation("player_Move_right");
		animationLeft = ResourceManager.getAnimation("player_Move_left");
		animationUp = ResourceManager.getAnimation("player_Move_up");
		animationDown = ResourceManager.getAnimation("player_Move_down");
		
		animationAttackRight = ResourceManager.getAnimation("player_Atack_right");
		animationAttackLeft = ResourceManager.getAnimation("player_Atack_left");
		animationAttackUp = ResourceManager.getAnimation("player_Atack_up");
		animationAttackDown = ResourceManager.getAnimation("player_Atack_down");
		
		this.hp=hp;
		this.attackSpeed=attackspeed;
		this.strength=strength;
		this.speed=speed;
		state = State.IDLE_UP;
		posicion=Posicion.DOWN;
	}
	
	public void move(Vector2 movement){
		movement.scl(speed);
		position.add(movement);
		
		Posiciones.posX=position.x;
		Posiciones.posY = position.y;
		
		rect.x = position.x;
		rect.y = position.y;
		
		if(position.x<0){
			position.x = 0;
		}
		
		if(position.x > Constants.SCREEN_WIDTH - idleFrame.getRegionWidth() ){
			idleFrame.getRegionWidth();
			position.x = Constants.SCREEN_WIDTH - currentFrame.getRegionWidth();
		}
		if(position.y < 0){
			position.y =0;
		}
		if(position.y + currentFrame.getRegionHeight() > Constants.SCREEN_HEIGHT){
			position.y = Constants.SCREEN_HEIGHT - currentFrame.getRegionHeight();
		}
		
	}
	
	public void mainSkill(){
		System.out.println("ESCUDO ACTIVADO");
		damage=false;
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	System.out.println("VULNERABLE");
		    	damage= true;
		    }
		}, 5);
	}
	
	@Override
	public void update(float dt) {

		stateTime += dt;
		switch (state) {
		case RIGHT:
			currentFrame = animationRight.getKeyFrame(stateTime, true);
			break;
		case LEFT:
			currentFrame = animationLeft.getKeyFrame(stateTime, true);
			break;
		case UP:
			currentFrame = animationUp.getKeyFrame(stateTime, true);
			break;
		case DOWN:
			currentFrame = animationDown.getKeyFrame(stateTime, true);
			break;
		case IDLE_RIGHT:
			currentFrame = idleFrame_Right.getKeyFrame(stateTime, true);
			break;
		case IDLE_LEFT:
			currentFrame = idleFrame_Left.getKeyFrame(stateTime, true);
			break;
		case IDLE_UP:
			currentFrame = idleFrame_Up.getKeyFrame(stateTime, true);
			break;
		case IDLE_DOWN:
			currentFrame = idleFrame_Down.getKeyFrame(stateTime, true);
			break;
		case ATTACK_RIGHT:
			currentFrame = animationAttackRight.getKeyFrame(stateTime, true);
			break;
		case ATTACK_LEFT:
			currentFrame = animationAttackLeft.getKeyFrame(stateTime, true);
			break;
		case ATTACK_UP:
			currentFrame = animationAttackUp.getKeyFrame(stateTime, true);
			break;
		case ATTACK_DOWN:
			currentFrame = animationAttackDown.getKeyFrame(stateTime, true);
			break;
		default:
			currentFrame = idleFrame;
		}
	}
}
