package com.osgix.game.robot.ui.action;

import org.eclipse.jface.action.Action;

import com.osgix.game.robot.game.RobotGame;


public class StartAction extends Action {

	private RobotGame game;

	public StartAction(RobotGame robotGame) {
		this.game = robotGame;
	}

	@Override
	public String getText() {
		return "Start";
	}

	@Override
	public void run() {
		game.newGame();
	}
}
