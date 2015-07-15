package com.osgix.game.robot.ui.dialog;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import com.osgix.game.robot.game.RobotGame;
import com.osgix.game.robot.robot.AbstractRobot;
import com.osgix.game.robot.ui.action.StartAction;
import com.osgix.game.robot.ui.action.TelePortAction;
import com.osgix.game.robot.ui.action.WaitRobotAction;
import com.osgix.game.robot.ui.event.ClickListener;
import com.osgix.game.robot.ui.robot.RobotUI;


public class GameDialog extends Window {

	private RobotGame robotGame;

	private Composite background;

	private Label msgLabel;

	public GameDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite bg = new Composite(parent, SWT.NONE);
		bg.setLayout(new FormLayout());

		createToolBar(bg);
		createBackground(bg);
		render();

		return super.createContents(parent);
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
		background.setBackgroundImage(ImageDescriptor.createFromFile(null,
				"img/map.png").createImage());

		background.addMouseListener(new ClickListener(robotGame));
		FormData data = new FormData();
		data.width = this.robotGame.getMapX() * 20;
		data.height = this.robotGame.getMapY() * 20;
		data.top = new FormAttachment(0, 24);
		background.setLayoutData(data);
	}

	public void init(RobotGame game) {
		this.robotGame = game;
		if (null != background) {
			cleanUI();
			render();
			background.layout();
		}
	}

	public void cleanUI() {
		msgLabel.dispose();
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
	}

}
