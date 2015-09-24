package model.entity;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import library.Point;
import library.Vector;
import model.EntityID;

public abstract class GameObject {
	protected EntityID id;
	
	protected Point position;
	protected Vector direction;
	protected Dimension size;
	

	protected BufferedImage sprite;

	protected boolean alive;
	
	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	protected GameObject(EntityID id){
		this.id = id;
	}

	public EntityID getId() {
		return id;
	}

	public void setId(EntityID id) {
		this.id = id;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Vector getDirection() {
		return direction;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage bufferedImage) {
		this.sprite = bufferedImage;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public abstract void die();	
}
