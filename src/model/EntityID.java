package model;

import controller.level.Level;
import model.entity.Astroid;
import model.entity.SmallDrone;

public enum EntityID {
	Player {
        public void createEntity(Level currentLevel) {
        }
    },
	Astroid {
        public void createEntity(Level currentLevel) {
    		new Astroid(currentLevel);
        }
    },
	SmallDrone {
        public void createEntity(Level currentLevel) {
    		new SmallDrone(currentLevel);
        }
    };
    public void createEntity(Level currentLevel) {
    }
}
