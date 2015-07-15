package com.osgix.game.robot.ui.event;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.player.Player;


public class ClickListener extends MouseAdapter {

	RobotGame game;
	Player player;

	public ClickListener(RobotGame robotGame) {
		this.game = robotGame;
	}

	public ClickListener(Player player) {
		this.player = player;
	}

	@Override
	public void mouseDown(MouseEvent e) {
		if (!game.isGameOver()) {
			game.next(e);
		}
	}
}
