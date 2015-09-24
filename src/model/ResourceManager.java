package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class ResourceManager {
	private static ResourceManager instance = null;
	private static HashMap<String, BufferedImage> resources;
	
	protected ResourceManager(){
		resources = new HashMap<String, BufferedImage>();
		loadResources();
	}
	 
	public static ResourceManager getInstance() {
		if(instance == null) {
			instance = new ResourceManager();
		}
		return instance;
	}
	
	private void loadResources(){
		 try {
			 resources.put("background", ImageIO.read(this.getClass().getResourceAsStream("/resources/space-background.png")));
			 resources.put("player", ImageIO.read(this.getClass().getResourceAsStream("/resources/player.png")));     
			 resources.put("small-ship", ImageIO.read(this.getClass().getResourceAsStream("/resources/small-ship.png"))); 
			 resources.put("astroid", ImageIO.read(this.getClass().getResourceAsStream("/resources/astroid.png")));    
        } catch (IOException ex) {
            Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public BufferedImage getSprite(String name){
		return resources.get(name);	
	}
}