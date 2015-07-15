package com.osgix.game.robot.ui.robot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.osgix.game.robot.robot.AbstractRobot;


public class RobotUI {
	private Label robotLabel;

	public RobotUI(AbstractRobot robot, Composite background) {
		this.robotLabel = new Label(background, SWT.NONE);
		robotLabel.setImage(robot.getImage());

	}

	public void setRobotLabel(Label robotLabel) {
		this.robotLabel = robotLabel;
	}

	public Label getRobotLabel() {
		return robotLabel;
	}

	public void remove() {
		robotLabel.dispose();
	}
}
