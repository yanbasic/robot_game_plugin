package com.osgix.game.robot.robot.bomb;

import org.eclipse.swt.graphics.Image;

import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.ui.image.Resources;


public class Bomb extends AbstractRobot {

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public int getPower() {
		return 10;
	}

	@Override
	public void newGame(RobotGame game) {

	}

	@Override
	public Image getImage() {
		return Resources.IMG_BOMB;
	}
}
