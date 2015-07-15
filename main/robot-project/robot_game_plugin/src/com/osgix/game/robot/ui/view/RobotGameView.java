package com.osgix.game.robot.ui.view;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.part.ViewPart;

import com.osgix.game.robot.control.RobotControl;
import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.ui.action.StartAction;
import com.osgix.game.robot.ui.action.TelePortAction;
import com.osgix.game.robot.ui.action.WaitRobotAction;
import com.osgix.game.robot.ui.event.ClickListener;
import com.osgix.game.robot.ui.image.Resources;
import com.osgix.game.robot.ui.robot.RobotUI;


public class RobotGameView extends ViewPart {

	private RobotGame robotGame;
	private Composite background;

	/**
	 * The constructor.
	 */
	public RobotGameView() {

		robotGame = new RobotGame(this);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		Composite bg = new Composite(parent, SWT.NONE);
		bg.setLayout(new FormLayout());

		createBackground(bg);
		createToolBar(bg);
		render();
	}

	private void createToolBar(Composite parent) {
		ToolBar bar1 = new ToolBar(parent, SWT.FLAT);
		ToolBarManager mgr = new ToolBarManager(bar1);
		mgr.add(new StartAction(robotGame));
		mgr.add(new TelePortAction(robotGame));
		mgr.add(new WaitRobotAction(robotGame));
		mgr.update(true);
	}

	private void createBackground(Composite parent) {
		background = new Composite(parent, SWT.NONE);
		background.setLayout(new FormLayout());
		background.setBackgroundImage(Resources.IMG_MAP1);

		background.addMouseListener(new ClickListener(robotGame));
		FormData data = new FormData();
		data.width = this.robotGame.getMapX() * 20;
		data.height = this.robotGame.getMapY() * 20;
		data.top = new FormAttachment(0, 24);
		background.setLayoutData(data);
	}

	public void initUI() {
		if (null != background) {
			cleanUI();
			render();
			background.layout();
		}
	}

	public void cleanUI() {
		for (AbstractRobot robot : robotGame.getAllRobots()) {
			if (null != robot.getRobotUI())
				robot.destroy();
		}
	}

	private void render() {
		for (AbstractRobot rbt : this.robotGame.getAllRobots()) {
			addElement(rbt);
		}

	}

	public void addElement(AbstractRobot robot) {
		robot.setRobotUI(new RobotUI(robot, background));
		RobotControl.moveToPoint(robot, robot.getPostion());
	}

	@Override
	public void setFocus() {
		background.setFocus();
	}

	@Override
	public void dispose() {
		robotGame = null;
		background.dispose();
		super.dispose();
	}
}