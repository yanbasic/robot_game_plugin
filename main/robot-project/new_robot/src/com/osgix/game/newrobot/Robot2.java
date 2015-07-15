package com.osgix.game.newrobot;

import java.util.Random;

import org.eclipse.swt.graphics.Point;

import com.osgix.game.robot.control.RobotControl;
import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;


public class Robot2 extends AbstractRobot {
	Point targetPoint;
	private RobotGame game;

	@Override
	public boolean move() {
		targetPoint.x = targetPoint.x > game.getMapX() ? 0 : targetPoint.x + 1;
		RobotControl.moveToPoint(this, targetPoint);
		return true;
	}

	@Override
	public void newGame(RobotGame game) {
		this.game = game;
		Random generator = new Random();
		targetPoint = new Point(0, 0);
		do {
			targetPoint.x = generator.nextInt(game.getMapX());
			targetPoint.y = generator.nextInt(game.getMapY());
		} while (game.hasRobots(targetPoint));
		setPosition(targetPoint);
	}
}
