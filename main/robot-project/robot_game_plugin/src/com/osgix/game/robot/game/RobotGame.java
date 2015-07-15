package com.osgix.game.robot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.osgix.game.robot.control.RobotControl;
import com.osgix.game.robot.plugin.RobotGamePlugin;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.robot.bomb.Bomb;
import com.osgix.game.robot.robot.player.Player;
import com.osgix.game.robot.ui.image.Resources;
import com.osgix.game.robot.ui.view.RobotGameView;


public class RobotGame {

	private RobotGameView view;

	private List<AbstractRobot> allRobots;
	private Player player;

	int mapX = 30, mapY = 20;
	Random generator = new Random();
	private IConfigurationElement[] configs;

	private boolean gameOver = true;

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public RobotGame(RobotGameView view) {
		this.view = view;
		allRobots = new ArrayList<AbstractRobot>();
	}

	public void newGame() {
		gameOver = false;
		view.cleanUI();
		this.initRobots();
		view.initUI();
	}

	public void initRobots() {
		allRobots.clear();

		player = new Player();
		player.setPosition(generator.nextInt(mapX), generator.nextInt(mapY));
		allRobots.add(player);

		if (null == configs) {
			configs = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(RobotGamePlugin.PLUGIN_ID);
		}
		try {
			for (IConfigurationElement element : configs) {

				int number = null == element.getAttribute("count") ? 1
						: Integer.parseInt(element.getAttribute("count"));

				for (int i = 0; i < number; i++) {
					Object obj = element.createExecutableExtension("class");
					if (obj instanceof AbstractRobot) {
						AbstractRobot robot = (AbstractRobot) obj;

						robot.setPlayer(player);

						int power = null == element.getAttribute("power") ? 1
								: Integer.parseInt(element
										.getAttribute("power"));

						robot.setPower(power);

						int speed = null == element.getAttribute("speed") ? 1
								: Integer.parseInt(element
										.getAttribute("speed"));

						robot.setSpeed(speed);

						robot.setName(element.getAttribute("name"));

						String icon = null == element.getAttribute("icon") ? Resources.IMG_DEFAULT_ROBOT
								: element.getAttribute("icon");

						robot.setImage(AbstractUIPlugin
								.imageDescriptorFromPlugin(
										element.getNamespace(), icon));

						robot.newGame(this);
						allRobots.add(robot);
					}
				}
			}
		} catch (CoreException ex) {
		}

	}

	public List<AbstractRobot> getAllRobots() {
		return allRobots;
	}

	public Point getAbsoultePosition(int x, int y) {
		Point p = new Point(0, 0);
		p.x = x / RobotControl.L1;
		p.y = y / RobotControl.L1;
		return p;
	}

	public void next(MouseEvent e) {
		if (!gameOver) {
			if (null != e) {
				RobotControl.moveToDirection(player,
						getAbsoultePosition(e.x, e.y));
			}
			goRobot();
			checkCollide();
			checkEnd();
		}
	}

	private void checkEnd() {
		if (!allRobots.contains(player)) {
			setLost();
			return;
		}
		if (0 == allRobots.size()) {
			setWin();
			return;
		}
		boolean allCanNotMove = true;
		for (AbstractRobot rbt : allRobots) {
			if (rbt.getSpeed() > 0) {
				allCanNotMove = false;
				break;

			}
		}
		if (allCanNotMove) {
			setWin();
			return;
		}
	}

	private void checkCollide() {

		for (int i = 0; i < allRobots.size(); i++) {
			for (int j = i + 1; j < allRobots.size(); j++) {
				if (allRobots.get(i).getPostion()
						.equals(allRobots.get(j).getPostion())) {

					if (allRobots.get(i).getPower() == allRobots.get(j)
							.getPower()) {

						allRobots.get(j).destroy();
						allRobots.get(i).destroy();
						Bomb bomb = new Bomb();
						bomb.setPosition(allRobots.get(i).getPostion().x,
								allRobots.get(i).getPostion().y);

						allRobots.add(bomb);
						view.addElement(bomb);
						allRobots.remove(j);
						allRobots.remove(i);
						// i--;
						j--;
					} else if (allRobots.get(i).getPower() < allRobots.get(j)
							.getPower()) {

						allRobots.get(i).destroy();
						allRobots.remove(i);
					} else {

						allRobots.get(j).destroy();
						allRobots.remove(j);
						j--;
					}

				}
			}
		}
	}

	private void setLost() {
		MessageDialog.openInformation(null, "", "game over");
		gameOver = true;
	}

	private void setWin() {
		MessageDialog.openInformation(null, "", "You win");
		gameOver = true;
	}

	private void goRobot() {
		for (AbstractRobot robot : allRobots) {
			for (int i = 0; i < robot.getSpeed(); i++) {
				robot.move();
			}
		}
	}

	public boolean hasRobots(Point p) {
		for (AbstractRobot robot : allRobots) {
			if (robot.getPostion().x == p.x && robot.getPostion().y == p.y) {
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

}
