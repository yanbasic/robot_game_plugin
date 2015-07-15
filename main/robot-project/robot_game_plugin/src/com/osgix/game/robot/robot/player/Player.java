package com.osgix.game.robot.robot.player;

import java.util.Random;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.plugin.RobotGamePlugin;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.ui.image.Resources;


public class Player extends AbstractRobot {

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public Image getImage() {
		return Resources.IMG_PLAYER;
	}

	@Override
	public int getPower() {
		return RobotGamePlugin.PLAYER_POWER;
	}

	@Override
	public void newGame(RobotGame game) {
		Random generator = new Random();
		Point p = new Point(0, 0);
		do {
			p.x = generator.nextInt(game.getMapX());
			p.y = generator.nextInt(game.getMapY());
		} while (game.hasRobots(p));
		setPosition(p);
	}
}
