package com.osgix.game.robot.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Point;

import com.osgix.game.robot.control.RobotControl;
import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.robot.player.Player;


public class TelePortAction extends Action {
	private RobotGame game;

	public TelePortAction(RobotGame robotGame) {
		this.game = robotGame;
	}

	@Override
	public String getText() {
		return "Teleport";
	}

	@Override
	public void run() {
		if (!game.isGameOver()) {
			Point telePortPoint = new Point(0, 0);
			int distance = 0;
			for (int i = 0; i < game.getMapX(); i++) {
				for (int j = 0; j < game.getMapY(); j++) {
					int d = 0;
					for (AbstractRobot robot : game.getAllRobots()) {
						if (!(robot instanceof Player)) {
							if (i == robot.getPostion().x
									&& j == robot.getPostion().y) {
								break;
							} else {
								d += Math.abs(i - robot.getPostion().x)
										+ Math.abs(j - robot.getPostion().y);
							}
						}
					}

					if (d > distance) {
						distance = d;
						telePortPoint.x = i;
						telePortPoint.y = j;
					}
				}
			}

			RobotControl.moveToPoint(game.getPlayer(), telePortPoint);
		}
	}
}
