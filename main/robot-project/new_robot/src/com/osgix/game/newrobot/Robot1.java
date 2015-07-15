package com.osgix.game.newrobot;

import java.util.Random;

import org.eclipse.swt.graphics.Point;

import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;


public class Robot1 extends AbstractRobot {

	@Override
	public boolean move() {
		moveToPlayer();
		return true;
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
