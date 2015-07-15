/**
 * 
 */
package com.osgix.game.robot.robot;

import org.eclipse.swt.graphics.Image;

import com.osgix.game.robot.ui.image.Resources;


/**
 * @author Administrator
 * 
 */
public enum RobotType {

	COMPUTER(2, Resources.IMG_ROBOT), PLAYER(1, Resources.IMG_PLAYER), BOMB(3,
			Resources.IMG_BOMB);

	int power;
	Image image;

	RobotType(int power, Image image) {
		this.power = power;
		this.image = image;
	}

	public Image getImage() {
		return this.image;
	}

	public int power() {
		return this.power;
	}
}
