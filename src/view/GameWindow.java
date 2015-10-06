package view;

import static model.GameGlobals.FULLSCREEN;
import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.GameController;

public class GameWindow extends JFrame{
	
	private static final long serialVersionUID = -4830100929102375164L;

	public GameWindow(String title, GameController game){
		setTitle(title);
		setPreferredSize(new Dimension(GWIDTH, GHEIGHT));
		setMinimumSize(new Dimension(GWIDTH, GHEIGHT));
		setMaximumSize(new Dimension(GWIDTH, GHEIGHT));
		
		if(FULLSCREEN){		
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		add(game);
		setVisible(true);		
		game.start();
	}

}
