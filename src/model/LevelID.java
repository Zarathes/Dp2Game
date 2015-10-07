package model;

import controller.level.Level;

public enum LevelID {
	Init {
        public Level createLevel() {
        	return new controller.level.Level1();
        }
    },
	Level1 {
        public Level createLevel() {
        	return new controller.level.Level2();
        }
    },
	Level2 {
    	public Level createLevel() {
        	return new controller.level.Level3();
        }
    },
	Level3 {
        public Level createLevel() {
        	return new controller.level.Level3();
        }
    };
    public Level createLevel() {
    	return null;
    }
}