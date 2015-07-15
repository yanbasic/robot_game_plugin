package com.osgix.game.robot.ui.action;

import org.eclipse.jface.action.Action;

import com.osgix.game.robot.game.RobotGame;


public class WaitRobotAction extends Action {

	private RobotGame robotGame;

	public WaitRobotAction(RobotGame robotGame) {
		this.robotGame = robotGame;
	}

	@Override
	public String getText() {
		return "Wait";
	}

	@Override
	public void run() {
		if (!robotGame.isGameOver()) {
			robotGame.next(null);
		}
	}
}
