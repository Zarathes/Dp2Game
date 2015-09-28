package controller.behaviour;

import java.util.ArrayList;

import library.Point;
import library.Vector;
import model.EntityID;
import model.entity.GameObject;

public class CollisionBehaviour {
	private ArrayList<GameObject> entities;
	
	public CollisionBehaviour(){
		entities = new ArrayList<GameObject>();
	}
	
	public void add(GameObject entity){
		entities.add(entity);		
	}
	
	public void remove(GameObject entity){
		entities.remove(entity);
	}
	
	public void execute(){
		int t = 1;
		
		for (int i = 0; i < entities.size(); i++) {
			GameObject current = entities.get(i);	
			
			for (int l = t; l < entities.size(); l++) {
				GameObject entity = entities.get(l);	
					
				float length = Point.subtractPoints(entity.getPosition(), current.getPosition()).length();
									
				if(length < ((current.getRadius() * 0.8) + (entity.getRadius()  * 0.8))){					
					// Two Astroids, do 2-Dimensional Elastic Collisions without Trigonometry
					if(current.getId() == EntityID.Astroid && entity.getId() == EntityID.Astroid){
						// Elastic Collisions
						// URL http://www.vobarian.com/collisions/2dcollisions2.pdf
						
						// Step 1: Find unit normal and unit tangent vectors. 
						Vector normal = Point.subtractPoints(entity.getPosition(), current.getPosition()).normalized();						
						Vector tangent = Point.subtractPoints(entity.getPosition(), current.getPosition()).tangent();
						
						// Step 2: Resolve the velocity vectors into normal and tangential components.
						Vector currentNormal = Vector.multiplyVector(normal, current.getDirection());
						Vector currentTangent = Vector.multiplyVector(tangent, current.getDirection());
						Vector entityNormal = Vector.multiplyVector(normal, entity.getDirection());
						Vector entityTangent = Vector.multiplyVector(tangent, entity.getDirection());
						
						// Step 3: Find the new tangential velocities (after the collision). 
						Vector currentTangentNew = currentTangent;
						Vector entityTangentNew = entityTangent;
						
						// Step 4: Find the new normal velocities.
						Vector currentNormalNew = Vector.divideVector((Vector.addVectors(Vector.multiplyVector(currentNormal, (float)(current.getMass() - entity.getMass())), Vector.multiplyVector(entityNormal,(float)(2 * entity.getMass())))), (float)(current.getMass() + entity.getMass())); 
						Vector entityNormalNew = Vector.divideVector((Vector.addVectors(Vector.multiplyVector(entityNormal, (float)(entity.getMass() - current.getMass())), Vector.multiplyVector(currentNormal,(float)(2 * current.getMass())))), (float)(entity.getMass() + current.getMass()));
						
						// Step 5: Convert the scalar normal and tangential velocities into vectors.
						Vector currentNormalVelocity = Vector.multiplyVector(currentNormalNew, normal);
						Vector currentTangentVelocity = Vector.multiplyVector(currentTangentNew, tangent);
						Vector entityNormalVelocity = Vector.multiplyVector(entityNormalNew, normal);
						Vector entityTangentVelocity = Vector.multiplyVector(entityTangentNew, tangent);
						
						// Step 6: Find the final velocity vectors.
						Vector currentNewDirection = Vector.addVectors(currentNormalVelocity, currentTangentVelocity);
						Vector entityNewDirection = Vector.addVectors(entityNormalVelocity, entityTangentVelocity);
						
						// Step 7: Set new Direction.
						current.setDirection(currentNewDirection);
						entity.setDirection(entityNewDirection);
					} else if(current.getId() == EntityID.SmallDrone){
						current.setAlive(false);
						if(entity.getId() == EntityID.SmallDrone){
							entity.setAlive(false);
						}
					} else if(entity.getId() == EntityID.SmallDrone){
						entity.setAlive(false);
						if(current.getId() == EntityID.SmallDrone){
							current.setAlive(false);
						}
					}
				}
			}	
			t++;
		}	
	}
}
