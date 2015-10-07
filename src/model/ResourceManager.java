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
			 resources.put("background1", ImageIO.read(this.getClass().getResourceAsStream("/resources/space-background1.png")));
			 resources.put("background2", ImageIO.read(this.getClass().getResourceAsStream("/resources/space-background2.png")));
			 resources.put("background3", ImageIO.read(this.getClass().getResourceAsStream("/resources/space-background3.jpg")));
			 resources.put("small-ship1", ImageIO.read(this.getClass().getResourceAsStream("/resources/small-ship1.png"))); 
			 resources.put("small-ship2", ImageIO.read(this.getClass().getResourceAsStream("/resources/small-ship2.png"))); 
			 resources.put("small-ship3", ImageIO.read(this.getClass().getResourceAsStream("/resources/small-ship3.png"))); 
			 resources.put("astroid1", ImageIO.read(this.getClass().getResourceAsStream("/resources/astroid1.png")));    
			 resources.put("astroid2", ImageIO.read(this.getClass().getResourceAsStream("/resources/astroid2.png")));    
			 resources.put("astroid3", ImageIO.read(this.getClass().getResourceAsStream("/resources/astroid3.png")));   
        } catch (IOException ex) {
            Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public BufferedImage getSprite(String name){
		return resources.get(name);	
	}
}