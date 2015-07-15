package com.osgix.game.robot.ui.image;

import org.eclipse.swt.graphics.Image;

import com.osgix.game.robot.plugin.RobotGamePlugin;


public class Resources {

	public static Image IMG_MAP1 = RobotGamePlugin.getImageDescriptor(
			"img/map.png").createImage();

	public static Image IMG_ROBOT = RobotGamePlugin.getImageDescriptor(
			"img/gnome-robots.png").createImage();

	public static Image IMG_PLAYER = RobotGamePlugin.getImageDescriptor(
			"img/gnomes.png").createImage();

	public static Image IMG_BOMB = RobotGamePlugin.getImageDescriptor(
			"img/bomb.png").createImage();

	public static Image IMG_BIG_BOMB = RobotGamePlugin.getImageDescriptor(
			"img/bigbomb.png").createImage();

	public static String IMG_DEFAULT_ROBOT = "img/gnome-robots.png";

}
