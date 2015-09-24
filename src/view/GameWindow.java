package view;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import static model.GameGlobals.*;
import controller.GameController;

public class GameWindow extends Canvas{

	private static final long serialVersionUID = 7363695646035884010L;
	
	public GameWindow(String title, GameController game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(GWIDTH, GHEIGHT));
		frame.setMinimumSize(new Dimension(GWIDTH, GHEIGHT));
		frame.setMaximumSize(new Dimension(GWIDTH, GHEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
	}

}
