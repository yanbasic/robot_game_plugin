package com.osgix.game.robot.control;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;

import com.osgix.game.robot.plugin.RobotGamePlugin;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.ui.robot.RobotUI;


public class RobotControl {

	public static int L1 = 20;

	public static final int TOP = 1;

	public static final int LEFT = 1 << 1;

	public static final int RIGHT = 1 << 2;

	public static final int BOTTOM = 1 << 3;

	public static final int TOP_LEFT = TOP | LEFT;

	public static final int TOP_RIGHT = TOP | RIGHT;

	public static final int BOTTOM_LEFT = BOTTOM | LEFT;

	public static final int BOTTOM_RIGHT = BOTTOM | RIGHT;

	public static boolean moveRobot(AbstractRobot robot, int direction) {
		if (!canMove(robot)) {
			return false;
		}
		switch (direction) {
		case TOP:
			moveStep(robot, 0, -1);
			break;
		case LEFT:
			moveStep(robot, -1, 0);
			break;
		case RIGHT:
			moveStep(robot, 1, 0);
			break;
		case BOTTOM:
			moveStep(robot, 0, 1);
			break;
		case TOP_LEFT:
			moveStep(robot, -1, -1);
			break;
		case TOP_RIGHT:
			moveStep(robot, 1, -1);
			break;
		case BOTTOM_LEFT:
			moveStep(robot, -1, 1);
			break;
		case BOTTOM_RIGHT:
			moveStep(robot, 1, 1);
			break;
		default:
			moveStep(robot, 0, 0);
			break;
		}
		return true;
	}

	private static boolean canMove(AbstractRobot robot) {
		if (RobotGamePlugin.BOMB_POWER == robot.getPower()) {
			return false;
		} else
			return true;
	}

	private static void moveStep(AbstractRobot robot, int stepX, int stepY) {

		robot.getPostion().x += stepX;
		robot.getPostion().y += stepY;
		setPosition(robot.getRobotUI(), robot.getPostion());
	}

	private static void setPosition(RobotUI robot, Point point) {
		if (!robot.getRobotLabel().isDisposed()) {
			FormData data = new FormData();
			data.width = 16;
			data.height = 16;
			data.left = new FormAttachment(0, 2 + point.x * RobotControl.L1);
			data.top = new FormAttachment(0, 2 + point.y * RobotControl.L1);
			robot.getRobotLabel().setLayoutData(data);
			robot.getRobotLabel().getParent().layout();
		}
	}

	public static void moveToPoint(AbstractRobot robot, Point targetPoint) {
		robot.getPostion().x = targetPoint.x;
		robot.getPostion().y = targetPoint.y;
		setPosition(robot.getRobotUI(), targetPoint);
	}

	public static void moveToDirection(AbstractRobot robot, Point targetPoint) {
		int move = 0;
		int rx = targetPoint.x - robot.getPostion().x;
		if (rx < 0) {
			move = RobotControl.LEFT;
		} else if (rx > 0) {
			move = RobotControl.RIGHT;
		}
		int ry = targetPoint.y - robot.getPostion().y;
		if (ry < 0) {
			move |= RobotControl.TOP;
		} else if (ry > 0) {
			move |= RobotControl.BOTTOM;
		}
		RobotControl.moveRobot(robot, move);
	}
}
