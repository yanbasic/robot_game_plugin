package com.osgix.game.robot.robot;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.osgix.game.robot.control.RobotControl;
import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.player.Player;
import com.osgix.game.robot.ui.robot.RobotUI;


public abstract class AbstractRobot {

	private Player player;
	private String name;
	private RobotUI robotUI;
	private int power;
	private int speed;
	private ImageDescriptor image;
	private Point point = new Point(0, 0);

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public RobotUI getRobotUI() {
		return robotUI;
	}

	public void setRobotUI(RobotUI robotUI) {
		this.robotUI = robotUI;
	}

	public void setPosition(int x, int y) {
		point.x = x;
		point.y = y;
	}

	public void setPosition(Point point) {
		this.point = point;
	}

	public Point getPostion() {
		return point;
	}

	public boolean destroy() {
		if (null == robotUI.getRobotLabel()) {
			return false;
		}
		robotUI.getRobotLabel().dispose();
		return true;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setImage(ImageDescriptor image) {
		this.image = image;
	}

	public Image getImage() {
		return image.createImage();
	}

	/**
	 * Close to player
	 */
	public void moveToPlayer() {
		RobotControl.moveToDirection(this, player.getPostion());
	}

	public abstract void newGame(RobotGame game);

	public abstract boolean move();
}
