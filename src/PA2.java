/**
 * @author Rosy Chen
 * @class: CS480: Introduction to Computer Graphics 
 * @assignment: PA2
 * @due_date: 10/18/2016
 * 
 * PA2.java - drives the scorpion model simulation. 
 * - creates all the parts of the scorpion and puts them all together
 * - limits rotation on scorpion limb
 */
 
/**
 * History:
 * 
 * 19 February 2011 - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 16 January 2008 - translated from C code by Stan Sclaroff
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 * 
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;//for new version of gl
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;//for new version of gl
import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl


public class PA2 extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private class Leg{
		private final Component joint1;
		private final Component joint2;
		private final Component joint3;
		private final Component joint4; 
		private final List<Component> joints;
	
		 /**
	     * Instantiates the leg with the four specified joints.
	     * @param joint1 - The joint closest to the body
	     * @param joint2 - The joint 2nd closest to the body
	     * @param joint3 - The joint 3rd closest to the 
	     * @param joint4 - The joint farthest from the body
	     */
		
		public Leg(final Component joint1, final Component joint2, final Component joint3, final Component joint4){
			this.joint1 = joint1;
			this.joint2 = joint2;
			this.joint3 = joint3;
			this.joint4 = joint4;
			this.joints = Collections.unmodifiableList(Arrays.asList(this.joint1, this.joint2, this.joint3, this.joint4));
		}
		
		/*
		 * @return The joints of the scorpion
		 */
		Component joint1(){
			return this.joint1;
		}
		
		Component joint2(){
			return this.joint2;
		}
		
		Component joint3(){
			return this.joint3;
		}
		
		Component joint4(){
			return this.joint4;
		}
		
		/* 
	     * @return An unmodifiable view of the list of the joints of this finger.
		 */
		List<Component> joints(){
			return this.joints;
		}
	}
	
	private class Tail_Claws{
		private final List<Component> joints;
		private final Component joint1;
		private final Component joint2;
		private final Component joint3;
		private final Component joint4; 
		private final Component joint5;
		private final Component joint6;
		
		public Tail_Claws(final Component joint1, final Component joint2, final Component joint3, 
			final Component joint4, final Component joint5, final Component joint6){
				this.joint1 = joint1;
				this.joint2 = joint2;
				this.joint3 = joint3;
				this.joint4 = joint4;
				this.joint5 = joint5;
				this.joint6 = joint6;
				this.joints = Collections.unmodifiableList(Arrays.asList(this.joint1, this.joint2, this.joint3, this.joint4, this.joint5, this.joint6));
		}
		
		Component joint1(){
			return this.joint1;
		}
		
		Component joint2(){
			return this.joint2;
		}
		
		Component joint3(){
			return this.joint3;
		}
		
		Component joint4(){
			return this.joint4;
		}
		
		Component joint5(){
			return this.joint5;
		}
		
		Component joint6(){
			return this.joint6;
		}
		
		/* 
	     * @return An unmodifiable view of the list of the joints of this finger.
		 */
		List<Component> joints(){
			return this.joints;
		}
	}

	/** Randomly generated serial version UID. */
	private static final long serialVersionUID = -7060944143920496524L;
	/** The default width of the created window. */
	public static final int DEFAULT_WINDOW_HEIGHT = 540;
	/** The default height of the created window. */
	public static final int DEFAULT_WINDOW_WIDTH = 540;
	/** The color for components which are selected for rotation. */
	public static final FloatColor ACTIVE_COLOR = FloatColor.RED;
	/** The color for components which are not selected for rotation. */
	public static final FloatColor INACTIVE_COLOR = FloatColor.ORANGE;
	/** The initial position of the top level component in the scene. */
	public static final Point3D INITIAL_POSITION = new Point3D(1, 0, 0);
	/** The angle by which to rotate the joint on user request to rotate. */
	public static final double ROTATION_ANGLE = 2.0;
	
	/** The radius of the body */
	public static final double BODY_RADIUS = 1;
	
	/** The height of the tail */
	public static final double TAIL_HEIGHT = 0.25;
	/** The radius of the tail */
	public static final double TAIL_RADIUS1 = 0.08;
	public static final double TAIL_RADIUS2 = 0.06;
	public static final double TAIL_RADIUS3 = 0.05;
	public static final double TAIL_RADIUS6 = 0.02;
	
	/** The height of the leg */
	public static final double LEG_HEIGHT = 0.4;
	public static final double LEG_HEIGHT1 = 0.1; // closest to the body joint
	/** The radius of the leg */
	public static final double LEG_RADIUS = 0.03;
	
	/** The height of the claw */
	public static final double CLAW_BODY_JOINT_HEIGHT = 0.2;
	public static final double CLAW_HEIGHT = 0.25;
	/** The radius of the claw */
	public static final double CLAW_RADIUS = 0.05;
	
	/**
	 * Runs the scorpion simulation in a single JFrame
	 * @param args - This parameter is ignored.
	 */
	
	public static void main(final String[] args) {
		new PA2().animator.start();
	}

	
	/** The animator which controls the framerate at which the canvas is animated.  */
	final FPSAnimator animator;
	/** The canvas on which we draw the scene. */
	private final GLCanvas canvas;
	/** The capabilities of the canvas. */
	private final GLCapabilities capabilities = new GLCapabilities(null);
	/** The OpenGL utility object. */
	private final GLU glu = new GLU();
	/** The OpenGL utility toolkit object */ 
	private final GLUT glut = new GLUT();
	

	/** The last x and y coordinates of the mouse press. */
	private int last_x = 0, last_y = 0;
	/** Whether the world is being rotated. */
	private boolean rotate_world = false;
	/** The axis around which to rotate the selected joints. */
	private Axis selectedAxis = Axis.X;
	/** The set of components which are currently selected for rotation. */
	private final Set<Component> selectedComponents = new HashSet<Component>(50);

	/**
	 * The set of legs, claws, and tails which have been selected for rotation.
	 * Selecting a joint will only affect the joints in this set of selected part
	 **/
	private final Set<Leg> selectedLeg = new HashSet<Leg>(8);
	private final Set<Tail_Claws> selected_tail_claw = new HashSet<Tail_Claws>(3);
	/** Whether the state of the model has been changed. */
	private boolean stateChanged = true;


	/** The top level component in the scene which controls the positioning and rotation of everything in the scene. */
	private final Component topLevelComponent;
	/** The tails and claws to be modeled */
	private final Tail_Claws[] tail;
	/** The body to be modeled */
	private final Component body;	
	/** The legs to be modeled */
	private final Leg[] legs;
	/** The claws to be modeled */
	private final Tail_Claws[] claws;
	
	/** The quaternion which controls the rotation of the world. */
	private Quaternion viewing_quaternion = new Quaternion();
	/** The set of all components. */
	private final List<Component> components;
		

	public static String SCORPION_NAME = "scorpion";

	public static String TAIL_1_NAME = "tail joint1";
	public static String TAIL_2_NAME = "tail joint2";
	public static String TAIL_3_NAME = "tail joint3";
	public static String TAIL_4_NAME = "tail joint4";
	public static String TAIL_5_NAME = "tail joint5";
	public static String TAIL_6_NAME = "tail joint6";
	
	public static String BODY_NAME = "body";

	public static String LEFT_LEG_A1_NAME = "left leg joint a1";
	public static String LEFT_LEG_A2_NAME = "left leg joint a2";
	public static String LEFT_LEG_A3_NAME = "left leg joint a3";
	public static String LEFT_LEG_A4_NAME = "left leg joint a4";

	public static String LEFT_LEG_B1_NAME = "left leg joint b1";
	public static String LEFT_LEG_B2_NAME = "left leg joint b2";
	public static String LEFT_LEG_B3_NAME = "left leg joint b3";
	public static String LEFT_LEG_B4_NAME = "left leg joint b4";

	public static String LEFT_LEG_C1_NAME = "left leg joint c1";
	public static String LEFT_LEG_C2_NAME = "left leg joint c2";
	public static String LEFT_LEG_C3_NAME = "left leg joint c3";
	public static String LEFT_LEG_C4_NAME = "left leg joint c4";	

	public static String LEFT_LEG_D1_NAME = "left leg joint d1";
	public static String LEFT_LEG_D2_NAME = "left leg joint d2";
	public static String LEFT_LEG_D3_NAME = "left leg joint d3";
	public static String LEFT_LEG_D4_NAME = "left leg joint d4";

	public static String RIGHT_LEG_A1_NAME = "right leg joint A1";
	public static String RIGHT_LEG_A2_NAME = "right leg joint A2";
	public static String RIGHT_LEG_A3_NAME = "right leg joint A3";
	public static String RIGHT_LEG_A4_NAME = "right leg joint A4";

	public static String RIGHT_LEG_B1_NAME = "right leg joint B1";
	public static String RIGHT_LEG_B2_NAME = "right leg joint B2";
	public static String RIGHT_LEG_B3_NAME = "right leg joint B3";
	public static String RIGHT_LEG_B4_NAME = "right leg joint B4";

	public static String RIGHT_LEG_C1_NAME = "right leg joint C1";
	public static String RIGHT_LEG_C2_NAME = "right leg joint C2";
	public static String RIGHT_LEG_C3_NAME = "right leg joint C3";
	public static String RIGHT_LEG_C4_NAME = "right leg joint C4";

	public static String RIGHT_LEG_D1_NAME = "right leg joint D1";
	public static String RIGHT_LEG_D2_NAME = "right leg joint D2";
	public static String RIGHT_LEG_D3_NAME = "right leg joint D3";
	public static String RIGHT_LEG_D4_NAME = "right leg joint D4";
	
	public static String LEFT_CLAW_1_NAME = "left claw joint1";
	public static String LEFT_CLAW_2_NAME = "left claw joint2";
	public static String LEFT_CLAW_3_NAME = "left claw joint3";
	public static String LEFT_CLAW_4_NAME = "left claw joint4";
	public static String LEFT_CLAW_5_NAME = "left claw joint5";
	public static String LEFT_CLAW_6_NAME = "left claw joint6";
	
	public static String RIGHT_CLAW_1_NAME = "right claw joint1";
	public static String RIGHT_CLAW_2_NAME = "right claw joint2";
	public static String RIGHT_CLAW_3_NAME = "right claw joint3";
	public static String RIGHT_CLAW_4_NAME = "right claw joint4";
	public static String RIGHT_CLAW_5_NAME = "right claw joint5";
	public static String RIGHT_CLAW_6_NAME = "right claw joint6";

	public static String TOP_LEVEL_NAME = "top level";
	
	

	/**
	 * Initializes the necessary OpenGL objects and adds a canvas to this JFrame.
	 */
	public PA2() {
		this.capabilities.setDoubleBuffered(true);

		this.canvas = new GLCanvas(this.capabilities);
		this.canvas.addGLEventListener(this);
		this.canvas.addMouseListener(this);
		this.canvas.addMouseMotionListener(this);
		this.canvas.addKeyListener(this);
		// this is true by default, but we just add this line to be explicit
		this.canvas.setAutoSwapBufferMode(true);
		this.getContentPane().add(this.canvas);

		// refresh the scene at 60 frames per second
		this.animator = new FPSAnimator(this.canvas, 60);

		this.setTitle("CS480/CS680 : Scorpion Simulator");
		this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		
	    // all 4th joint in legs
		final Component joint4_a = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_A4_NAME);
		final Component joint4_b = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_B4_NAME);
		final Component joint4_c = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_C4_NAME);
		final Component joint4_d = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_D4_NAME);
		final Component joint4_A = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_A4_NAME);
		final Component joint4_B = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_B4_NAME);
		final Component joint4_C = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_C4_NAME);
		final Component joint4_D = new Component(new Point3D(0, 0, LEG_HEIGHT), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_D4_NAME);
		
		
		// all 3rd joints in legs
		final Component joint3_a = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_A3_NAME);
		final Component joint3_b = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_B3_NAME);
		final Component joint3_c = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_C3_NAME);
		final Component joint3_d = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_D3_NAME);
		final Component joint3_A = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_A3_NAME);
		final Component joint3_B = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_B3_NAME);
		final Component joint3_C = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_C3_NAME);
		final Component joint3_D = new Component(new Point3D(0, 0, LEG_HEIGHT),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_D3_NAME);
		
		
		// all 2nd joints in legs
		final Component joint2_a = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_A2_NAME);
		final Component joint2_b = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_B2_NAME);
		final Component joint2_c = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_C2_NAME);
		final Component joint2_d = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), LEFT_LEG_D2_NAME);
		final Component joint2_A = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_A2_NAME);
		final Component joint2_B = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_B2_NAME);
		final Component joint2_C = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_C2_NAME);
		final Component joint2_D = new Component(new Point3D(0, 0, LEG_HEIGHT1),
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT, this.glut), RIGHT_LEG_D2_NAME);


		// all the body joints, displaced by various amounts from the body
		final Component joint1_a = new Component(new Point3D(0.28, 0, 1.3), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_A1_NAME);
		final Component joint1_b = new Component(new Point3D(0.3, 0, 1.1), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_B1_NAME);
		final Component joint1_c = new Component(new Point3D(0.3, 0, 0.9), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_C1_NAME);
		final Component joint1_d = new Component(new Point3D(0.28, 0, 0.7), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), LEFT_LEG_D1_NAME);
		
		final Component joint1_A = new Component(new Point3D(-0.28, 0, 1.3), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_A1_NAME);
		final Component joint1_B = new Component(new Point3D(-0.3, 0, 1.1), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_B1_NAME );
		final Component joint1_C = new Component(new Point3D(-0.3, 0, 0.9), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_C1_NAME );
		final Component joint1_D = new Component(new Point3D(-0.28, 0, 0.7), 
				new RoundedCylinder(LEG_RADIUS, LEG_HEIGHT1, this.glut), RIGHT_LEG_D1_NAME );
		
		
		// tail joints
		final Component tail_6 = new Component(new Point3D(0, 0, TAIL_HEIGHT), 
				new RoundedCylinder(TAIL_RADIUS6, TAIL_HEIGHT, this.glut), TAIL_6_NAME);
		final Component tail_5 = new Component(new Point3D(0, 0, TAIL_HEIGHT),
				new RoundedCylinder(TAIL_RADIUS3, TAIL_HEIGHT, this.glut), TAIL_5_NAME);
		final Component tail_4 = new Component(new Point3D(0, 0, TAIL_HEIGHT),
				new RoundedCylinder(TAIL_RADIUS3, TAIL_HEIGHT, this.glut), TAIL_4_NAME);
		final Component tail_3 = new Component(new Point3D(0, 0, TAIL_HEIGHT),
				new RoundedCylinder(TAIL_RADIUS3, TAIL_HEIGHT, this.glut), TAIL_3_NAME);
		final Component tail_2 = new Component(new Point3D(0, 0, TAIL_HEIGHT), 
				new RoundedCylinder(TAIL_RADIUS2, TAIL_HEIGHT, this.glut), TAIL_2_NAME);
		final Component tail_1 = new Component(new Point3D(0, 0, 0.45),
				new RoundedCylinder(TAIL_RADIUS1, TAIL_HEIGHT, this.glut), TAIL_1_NAME);
		
		
		// left claw
		final Component left_claw_6 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), LEFT_CLAW_6_NAME);
		final Component left_claw_5 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), LEFT_CLAW_5_NAME);
		final Component left_claw_4 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), LEFT_CLAW_4_NAME);
		final Component left_claw_3 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), LEFT_CLAW_3_NAME);
		final Component left_claw_2 = new Component(new Point3D(0, 0, CLAW_BODY_JOINT_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), LEFT_CLAW_2_NAME);
		
		final Component left_claw_1 = new Component(new Point3D(-0.16, 0, 1.50), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_BODY_JOINT_HEIGHT, this.glut), LEFT_CLAW_1_NAME);
		


		// right claw
		final Component right_claw_6 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), RIGHT_CLAW_6_NAME);
		final Component right_claw_5 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), RIGHT_CLAW_5_NAME);
		final Component right_claw_4 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), RIGHT_CLAW_4_NAME);
		final Component right_claw_3 = new Component(new Point3D(0, 0, CLAW_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), RIGHT_CLAW_3_NAME);
		final Component right_claw_2 = new Component(new Point3D(0, 0, CLAW_BODY_JOINT_HEIGHT), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_HEIGHT, this.glut), RIGHT_CLAW_2_NAME);
		
		final Component right_claw_1 = new Component(new Point3D(0.16, 0, 1.50), 
				new RoundedCylinder(CLAW_RADIUS, CLAW_BODY_JOINT_HEIGHT, this.glut), RIGHT_CLAW_1_NAME);
		
		
	    // put together the fingers for easier selection by keyboard input later on
	    this.legs = new Leg[] { 
	    	new Leg(joint1_a, joint2_a, joint3_a, joint4_a),
	        new Leg(joint1_b, joint2_b, joint3_b, joint4_b),
	        new Leg(joint1_c, joint2_c, joint3_c, joint4_c),
	        new Leg(joint1_d, joint2_d, joint3_d, joint4_d),

	    	new Leg(joint1_A, joint2_A, joint3_A, joint4_A),
	        new Leg(joint1_B, joint2_B, joint3_B, joint4_B),
	        new Leg(joint1_C, joint2_C, joint3_C, joint4_C),
	        new Leg(joint1_D, joint2_D, joint3_D, joint4_D)
		    };
	    
	    this.tail = new Tail_Claws[]{
	    	new Tail_Claws(tail_1, tail_2, tail_3, tail_4, tail_5, tail_6)	
	    };
		 
	    this.claws = new Tail_Claws[]{
	    	new Tail_Claws(left_claw_1, left_claw_2, left_claw_3, left_claw_4, left_claw_5, left_claw_6),
	   		new Tail_Claws(right_claw_1, right_claw_2, right_claw_3, right_claw_4, right_claw_5, right_claw_6)
	     };
	        
	    
	    this.body = new Component(new Point3D(0, 0, 0), 
	    	new Body(BODY_RADIUS, this.glut), SCORPION_NAME);
	    
	    
	    // the top level component which provides an initial position and rotation
	    // to the scene (but does not cause anything to be drawn)
	    this.topLevelComponent = new Component(INITIAL_POSITION, TOP_LEVEL_NAME);
	    
	    this.topLevelComponent.addChild(this.body);
	    
	    this.body.addChild(tail_1);
	    tail_1.addChild(tail_2);
	    tail_2.addChild(tail_3);
	    tail_3.addChild(tail_4);
	    tail_4.addChild(tail_5);
	    tail_5.addChild(tail_6);

	    this.body.addChildren(joint1_A, joint1_B, joint1_C, joint1_D, 
	    						joint1_a, joint1_b, joint1_c, joint1_d, 
	    						left_claw_1, right_claw_1);

	    joint1_A.addChild(joint2_A);
	    joint2_A.addChild(joint3_A);
	    joint3_A.addChild(joint4_A);

	    joint1_B.addChild(joint2_B);
	    joint2_B.addChild(joint3_B);
	    joint3_B.addChild(joint4_B);

	    joint1_C.addChild(joint2_C);
	    joint2_C.addChild(joint3_C);
	    joint3_C.addChild(joint4_C);

	    joint1_D.addChild(joint2_D);
	    joint2_D.addChild(joint3_D);
	    joint3_D.addChild(joint4_D);


	    joint1_a.addChild(joint2_a);
	    joint2_a.addChild(joint3_a);
	    joint3_a.addChild(joint4_a);

	    joint1_b.addChild(joint2_b);
	    joint2_b.addChild(joint3_b);
	    joint3_b.addChild(joint4_b);

	    joint1_c.addChild(joint2_c);
	    joint2_c.addChild(joint3_c);
	    joint3_c.addChild(joint4_c);

	    joint1_d.addChild(joint2_d);
	    joint2_d.addChild(joint3_d);
	    joint3_d.addChild(joint4_d);

	    left_claw_1.addChild(left_claw_2);
	    left_claw_2.addChild(left_claw_3);
	    left_claw_3.addChildren(left_claw_4, left_claw_5);
	    left_claw_4.addChild(left_claw_6);

	    right_claw_1.addChild(right_claw_2);
	    right_claw_2.addChild(right_claw_3);
	    right_claw_3.addChildren(right_claw_4, right_claw_5);
	    right_claw_4.addChild(right_claw_6);


		// turn the whole arm to be at an arm-like angle
		this.topLevelComponent.rotate(Axis.X, -125);
	    this.topLevelComponent.rotate(Axis.Y, 236);
		this.topLevelComponent.rotate(Axis.Z, 20);
		
		tail_1.rotate(Axis.Y, 180);
		tail_1.rotate(Axis.X, -30);
		tail_2.rotate(Axis.X, 30);
		tail_3.rotate(Axis.X, 40);
		tail_4.rotate(Axis.X, 30);
		tail_5.rotate(Axis.X, 30);
		tail_6.rotate(Axis.X, 35);
		
		joint1_a.rotate(Axis.Y, 90);
		joint1_b.rotate(Axis.Y, 90);
		joint1_c.rotate(Axis.Y, 90);
		joint1_d.rotate(Axis.Y, 90);
		
		joint2_a.rotate(Axis.X, 45);
		joint2_b.rotate(Axis.X, 45);
		joint2_c.rotate(Axis.X, 45);
		joint2_d.rotate(Axis.X, 45);
				
		joint3_a.rotate(Axis.X, -135);
		joint3_b.rotate(Axis.X, -135);
		joint3_c.rotate(Axis.X, -135);
		joint3_d.rotate(Axis.X, -135);
		
		joint4_a.rotate(Axis.X, 80);
		joint4_b.rotate(Axis.X, 80);
		joint4_c.rotate(Axis.X, 80);
		joint4_d.rotate(Axis.X, 80);
		
		joint1_A.rotate(Axis.Y, -90);
		joint1_B.rotate(Axis.Y, -90);
		joint1_C.rotate(Axis.Y, -90);
		joint1_D.rotate(Axis.Y, -90);
		
		joint2_A.rotate(Axis.X, 45);
		joint2_B.rotate(Axis.X, 45);
		joint2_C.rotate(Axis.X, 45);
		joint2_D.rotate(Axis.X, 45);
		
		joint3_A.rotate(Axis.X, -135);
		joint3_B.rotate(Axis.X, -135);
		joint3_C.rotate(Axis.X, -135);
		joint3_D.rotate(Axis.X, -135);
		
		joint4_A.rotate(Axis.X, 80);
		joint4_B.rotate(Axis.X, 80);
		joint4_C.rotate(Axis.X, 80);
		joint4_D.rotate(Axis.X, 80);
		
		right_claw_2.rotate(Axis.Y, 90);
		right_claw_3.rotate(Axis.Y, -90);
		right_claw_4.rotate(Axis.Y, 20);
		right_claw_5.rotate(Axis.Y, -45);
		right_claw_6.rotate(Axis.Y, -60);
		
		left_claw_2.rotate(Axis.Y, -90);
		left_claw_3.rotate(Axis.Y, 90);
		left_claw_4.rotate(Axis.Y, -20);
		left_claw_5.rotate(Axis.Y, 45);
		left_claw_6.rotate(Axis.Y, 60);
		
		for (final Component left_leg_joint1 : Arrays.asList(joint1_A, joint1_B, joint1_C, joint1_D)) {
	    	left_leg_joint1.setXPositiveExtent(0);	
	    	left_leg_joint1.setXNegativeExtent(0);
	    	left_leg_joint1.setYPositiveExtent(-80);
	    	left_leg_joint1.setYNegativeExtent(-100);
	    	left_leg_joint1.setZPositiveExtent(0);
	    	left_leg_joint1.setZNegativeExtent(0);
	    }

	    for (final Component right_leg_joint1 : Arrays.asList(joint1_a, joint1_b, joint1_c, joint1_d)) {
			right_leg_joint1.setXPositiveExtent(0);	
			right_leg_joint1.setXNegativeExtent(0);
			right_leg_joint1.setYPositiveExtent(100);
			right_leg_joint1.setYNegativeExtent(80);
			right_leg_joint1.setZPositiveExtent(0);
			right_leg_joint1.setZNegativeExtent(0);
	    }

	    for (final Component leg_joint2 : Arrays.asList(joint2_A, joint2_B, joint2_C, joint2_D, joint2_a, joint2_b, joint2_c, joint2_d)) {
	    	leg_joint2.setXPositiveExtent(60);
	    	leg_joint2.setXNegativeExtent(20);
	    	leg_joint2.setYPositiveExtent(0);
	    	leg_joint2.setYNegativeExtent(0);
	    	leg_joint2.setZPositiveExtent(0);
	    	leg_joint2.setZNegativeExtent(0);
	    }	   

	    for (final Component leg_joint3 : Arrays.asList(joint3_A, joint3_B, joint3_C, joint3_D, joint3_a, joint3_b, joint3_c, joint3_d)) {
	    	leg_joint3.setXPositiveExtent(-85);
	    	leg_joint3.setXNegativeExtent(-135);
	    	leg_joint3.setYPositiveExtent(0);
	    	leg_joint3.setYNegativeExtent(0);
	    	leg_joint3.setZPositiveExtent(0);
	    	leg_joint3.setZNegativeExtent(0);
	    }	    

	    for (final Component leg_joint4 : Arrays.asList(joint4_A, joint4_B, joint4_C, joint4_D, joint4_a, joint4_b, joint4_c, joint4_d)) {
	    	leg_joint4.setXPositiveExtent(110);
	    	leg_joint4.setXNegativeExtent(60);
	    	leg_joint4.setYPositiveExtent(0);
	    	leg_joint4.setYNegativeExtent(0);
	    	leg_joint4.setZPositiveExtent(0);
	    	leg_joint4.setZNegativeExtent(0);
	    }	
	    

	    left_claw_1.setXPositiveExtent(30);
	    left_claw_1.setXNegativeExtent(0);
	    left_claw_1.setYPositiveExtent(0);
	    left_claw_1.setYNegativeExtent(0);
	    left_claw_1.setZPositiveExtent(0);
	    left_claw_1.setZNegativeExtent(0);
	    
	    left_claw_2.setXPositiveExtent(0);
	    left_claw_2.setXNegativeExtent(0);
	    left_claw_2.setYPositiveExtent(-75);
	    left_claw_2.setYNegativeExtent(-95);
	    left_claw_2.setZPositiveExtent(0);
	    left_claw_2.setZNegativeExtent(0);
	    
    	left_claw_3.setXPositiveExtent(0);
    	left_claw_3.setXNegativeExtent(0);
    	left_claw_3.setYPositiveExtent(95);
    	left_claw_3.setYNegativeExtent(65);
    	left_claw_3.setZPositiveExtent(0);
    	left_claw_3.setZNegativeExtent(0);

    	left_claw_4.setXPositiveExtent(0);
    	left_claw_4.setXNegativeExtent(0);
    	left_claw_4.setYPositiveExtent(-15);
    	left_claw_4.setYNegativeExtent(-30);
    	left_claw_4.setZPositiveExtent(0);
    	left_claw_4.setZNegativeExtent(0);
    	
    	left_claw_5.setXPositiveExtent(0);
    	left_claw_5.setXNegativeExtent(0);
    	left_claw_5.setYPositiveExtent(70);
    	left_claw_5.setYNegativeExtent(35);
    	left_claw_5.setZPositiveExtent(0);
    	left_claw_5.setZNegativeExtent(0);
    	
    	left_claw_6.setXPositiveExtent(0);
    	left_claw_6.setXNegativeExtent(0);
    	left_claw_6.setYPositiveExtent(60);
    	left_claw_6.setYNegativeExtent(60);
    	left_claw_6.setZPositiveExtent(0);
    	left_claw_6.setZNegativeExtent(0);
    	
	    right_claw_1.setXPositiveExtent(30);
	    right_claw_1.setXNegativeExtent(0);
	    right_claw_1.setYPositiveExtent(0);
	    right_claw_1.setYNegativeExtent(0);
	    right_claw_1.setZPositiveExtent(0);
	    right_claw_1.setZNegativeExtent(0);
	    
	    right_claw_2.setXPositiveExtent(0);
	    right_claw_2.setXNegativeExtent(0);
	    right_claw_2.setYPositiveExtent(95);
	    right_claw_2.setYNegativeExtent(75);
	    right_claw_2.setZPositiveExtent(0);
	    right_claw_2.setZNegativeExtent(0);
	    
    	right_claw_3.setXPositiveExtent(0);
    	right_claw_3.setXNegativeExtent(0);
    	right_claw_3.setYPositiveExtent(-65);
    	right_claw_3.setYNegativeExtent(-95);
    	right_claw_3.setZPositiveExtent(0);
    	right_claw_3.setZNegativeExtent(0);

    	right_claw_4.setXPositiveExtent(0);
    	right_claw_4.setXNegativeExtent(0);
    	right_claw_4.setYPositiveExtent(30);
    	right_claw_4.setYNegativeExtent(15);
    	right_claw_4.setZPositiveExtent(0);
    	right_claw_4.setZNegativeExtent(0);
    	
    	right_claw_5.setXPositiveExtent(0);
    	right_claw_5.setXNegativeExtent(0);
    	right_claw_5.setYPositiveExtent(-35);
    	right_claw_5.setYNegativeExtent(-70);
    	right_claw_5.setZPositiveExtent(0);
    	right_claw_5.setZNegativeExtent(0);
    	
    	right_claw_6.setXPositiveExtent(0);
    	right_claw_6.setXNegativeExtent(0);
    	right_claw_6.setYPositiveExtent(-60);
    	right_claw_6.setYNegativeExtent(-60);
    	right_claw_6.setZPositiveExtent(0);
    	right_claw_6.setZNegativeExtent(0);
	    
	    tail_1.setXPositiveExtent(-15);
	    tail_1.setXNegativeExtent(-30);
	    tail_1.setYPositiveExtent(180);
	    tail_1.setYNegativeExtent(180);
	    tail_1.setZPositiveExtent(0);
	    tail_1.setZNegativeExtent(0);

	    for (final Component tailJoint : Arrays.asList(tail_2, tail_3, tail_4)) {
			tailJoint.setXPositiveExtent(50);
			tailJoint.setXNegativeExtent(20);
			tailJoint.setYPositiveExtent(0);
			tailJoint.setYNegativeExtent(0);
			tailJoint.setZPositiveExtent(0);
			tailJoint.setZNegativeExtent(0);
	    }

	    for (final Component tailJoint : Arrays.asList(tail_5, tail_6)) {
			tailJoint.setXPositiveExtent(45);
			tailJoint.setXNegativeExtent(20);
			tailJoint.setYPositiveExtent(0);
			tailJoint.setYNegativeExtent(0);
			tailJoint.setZPositiveExtent(0);
			tailJoint.setZNegativeExtent(0);
	    }

	    // create the list of all the components for debugging purposes
	    this.components = Arrays.asList(
	    	joint1_A, joint1_B, joint1_C, joint1_D,
	    	joint2_A, joint2_B, joint2_C, joint2_D, 
	    	joint3_A, joint3_B, joint3_C, joint3_D, 
	    	joint4_A, joint4_B, joint4_D, joint4_D,
	    	joint1_a, joint1_b, joint1_c, joint1_d,
	    	joint2_a, joint2_b, joint2_c, joint2_d,
	    	joint3_a, joint3_b, joint3_c, joint3_d,
	    	joint4_a, joint4_b, joint4_c, joint4_d, 
	    	tail_1, tail_2, tail_3, tail_4, tail_5, tail_6, 
	    	right_claw_1, right_claw_2, right_claw_3, right_claw_4, right_claw_5, right_claw_6,
	    	left_claw_1, left_claw_2, left_claw_3, left_claw_4, left_claw_5, left_claw_6,
	    	this.body);
	}


	/**
	* Redisplays the scene containing the hand model.
	* @param drawable - The OpenGL drawable object with which to create OpenGL models.
	*/

	public void display(final GLAutoDrawable drawable) {
		final GL2 gl = (GL2)drawable.getGL();

		// clear the display
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// from here on affect the model view
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		// start with the identity matrix initially
		gl.glLoadIdentity();

		// rotate the world by the appropriate rotation quaternion
		gl.glMultMatrixf(this.viewing_quaternion.toMatrix(), 0);

		// update the position of the components which need to be updated
		// TODO only need to update the selected and JUST deselected components
		if (this.stateChanged) {
			this.topLevelComponent.update(gl);
			this.stateChanged = false;
		}

		// redraw the components
		this.topLevelComponent.draw(gl);
	}

	/**
	* This method is intentionally unimplemented.
	* 
	* @param drawable -This parameter is ignored.
	* @param modeChanged - This parameter is ignored.
	* @param deviceChanged - This parameter is ignored.
	*/
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
		// intentionally unimplemented
	}



	/**
	* Initializes the scene and model.
	* 
	* @param drawable 
	*          {@inheritDoc}
	*/
	public void init(final GLAutoDrawable drawable) {
		final GL2 gl = (GL2)drawable.getGL();

		// perform any initialization needed by the hand model
		this.topLevelComponent.initialize(gl);

		// initially draw the scene
		this.topLevelComponent.update(gl);

		// set up for shaded display of the hand
		final float light0_position[] = { 1, 1, 1, 0 };
		final float light0_ambient_color[] = { 0.25f, 0.25f, 0.25f, 1 };
		final float light0_diffuse_color[] = { 1, 1, 1, 1 };

		gl.glPolygonMode(GL.GL_FRONT, GL2.GL_FILL);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL2.GL_SMOOTH);

		// set up the light source
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light0_ambient_color, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light0_diffuse_color, 0);

		// turn lighting and depth buffering on
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_NORMALIZE);
	}

	/**
	* Interprets key presses according to the following scheme:
	* 
	* up-arrow, down-arrow: increase/decrease rotation angle
	* 
	* @param key
	*          The key press event object.
	*/
	public void keyPressed(final KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_KP_UP:
		case KeyEvent.VK_UP:
			for (final Component component : this.selectedComponents) {
				component.rotate(this.selectedAxis, ROTATION_ANGLE);
			}
			this.stateChanged = true;
			break;
		case KeyEvent.VK_KP_DOWN:
		case KeyEvent.VK_DOWN:
			for (final Component component : this.selectedComponents) {
				component.rotate(this.selectedAxis, -ROTATION_ANGLE);
			}
			this.stateChanged = true;
			break;
		default:
			break;
		}
	}

	/**
	* This method is intentionally unimplemented.
	* @param key - This parameter is ignored.
	*/
	public void keyReleased(final KeyEvent key) {
		// intentionally unimplemented
	}

	private final TestCases testCases = new TestCases();



	private void setModelState(final Map<String, Angled> state) {
		this.tail[0].joint1().setAngles(state.get(TAIL_1_NAME));
		this.tail[0].joint2().setAngles(state.get(TAIL_2_NAME));
		this.tail[0].joint3().setAngles(state.get(TAIL_3_NAME));
		this.tail[0].joint4().setAngles(state.get(TAIL_4_NAME));
		this.tail[0].joint5().setAngles(state.get(TAIL_5_NAME));
		this.tail[0].joint6().setAngles(state.get(TAIL_6_NAME));

		this.body.setAngles(state.get(BODY_NAME));

		this.legs[0].joint1().setAngles(state.get(LEFT_LEG_A1_NAME));
		this.legs[0].joint2().setAngles(state.get(LEFT_LEG_A2_NAME));
		this.legs[0].joint3().setAngles(state.get(LEFT_LEG_A3_NAME));
		this.legs[0].joint4().setAngles(state.get(LEFT_LEG_A4_NAME));


		this.legs[1].joint1().setAngles(state.get(LEFT_LEG_B1_NAME));
		this.legs[1].joint2().setAngles(state.get(LEFT_LEG_B2_NAME));
		this.legs[1].joint3().setAngles(state.get(LEFT_LEG_B3_NAME));
		this.legs[1].joint4().setAngles(state.get(LEFT_LEG_B4_NAME));

		this.legs[2].joint1().setAngles(state.get(LEFT_LEG_C1_NAME));
		this.legs[2].joint2().setAngles(state.get(LEFT_LEG_C2_NAME));
		this.legs[2].joint3().setAngles(state.get(LEFT_LEG_C3_NAME));
		this.legs[2].joint4().setAngles(state.get(LEFT_LEG_C4_NAME));

		this.legs[3].joint1().setAngles(state.get(LEFT_LEG_D1_NAME));
		this.legs[3].joint2().setAngles(state.get(LEFT_LEG_D2_NAME));
		this.legs[3].joint3().setAngles(state.get(LEFT_LEG_D3_NAME));
		this.legs[3].joint4().setAngles(state.get(LEFT_LEG_D4_NAME));

		this.legs[4].joint1().setAngles(state.get(RIGHT_LEG_A1_NAME));
		this.legs[4].joint2().setAngles(state.get(RIGHT_LEG_A2_NAME));
		this.legs[4].joint3().setAngles(state.get(RIGHT_LEG_A3_NAME));
		this.legs[4].joint4().setAngles(state.get(RIGHT_LEG_A4_NAME));


		this.legs[5].joint1().setAngles(state.get(RIGHT_LEG_B1_NAME));
		this.legs[5].joint2().setAngles(state.get(RIGHT_LEG_B2_NAME));
		this.legs[5].joint3().setAngles(state.get(RIGHT_LEG_B3_NAME));
		this.legs[5].joint4().setAngles(state.get(RIGHT_LEG_B4_NAME));

		this.legs[6].joint1().setAngles(state.get(RIGHT_LEG_C1_NAME));
		this.legs[6].joint2().setAngles(state.get(RIGHT_LEG_C2_NAME));
		this.legs[6].joint3().setAngles(state.get(RIGHT_LEG_C3_NAME));
		this.legs[6].joint4().setAngles(state.get(RIGHT_LEG_C4_NAME));
		
		this.legs[7].joint1().setAngles(state.get(RIGHT_LEG_D1_NAME));
		this.legs[7].joint2().setAngles(state.get(RIGHT_LEG_D2_NAME));
		this.legs[7].joint3().setAngles(state.get(RIGHT_LEG_D3_NAME));
		this.legs[7].joint4().setAngles(state.get(RIGHT_LEG_D4_NAME));

		this.claws[0].joint1().setAngles(state.get(LEFT_CLAW_1_NAME));
		this.claws[0].joint2().setAngles(state.get(LEFT_CLAW_2_NAME));
		this.claws[0].joint3().setAngles(state.get(LEFT_CLAW_3_NAME));
		this.claws[0].joint4().setAngles(state.get(LEFT_CLAW_4_NAME));
		this.claws[0].joint5().setAngles(state.get(LEFT_CLAW_5_NAME));
		this.claws[0].joint6().setAngles(state.get(LEFT_CLAW_6_NAME));

		this.claws[1].joint1().setAngles(state.get(RIGHT_CLAW_1_NAME));
		this.claws[1].joint2().setAngles(state.get(RIGHT_CLAW_2_NAME));
		this.claws[1].joint3().setAngles(state.get(RIGHT_CLAW_3_NAME));
		this.claws[1].joint4().setAngles(state.get(RIGHT_CLAW_4_NAME));
		this.claws[1].joint5().setAngles(state.get(RIGHT_CLAW_5_NAME));
		this.claws[1].joint6().setAngles(state.get(RIGHT_CLAW_6_NAME));

		this.stateChanged = true;
	}


	/**
   	* Interprets typed keys according to the following scheme:
   	* A : toggle the most front RIGHT leg active for rotation
   	* B : toggle the 2nd most front RIGHT leg active for rotation
   	* C : toggle the 3rd most front RIGHT leg active for rotation
   	* D : toggle the 4th most front RIGHT leg active for rotation
   	* F : toggle the RIGHT front claw active for rotation
   	* T/t : toggles the tail when active for rotation
   	*
	* a : toggle the most front LEFT leg active for rotation
   	* b : toggle the 2nd most front LEFT leg active for rotation
   	* c : toggle the 3rd most front LEFT leg active for rotation
   	* d : toggle the 4th most front LEFT leg active for rotation
   	* f : toggle the LEFT front claw active for rotation
   	* 
   	* 1 : toggle joint 1 for active parts
	* 2 : toggle joint 2 for active parts
	* 3 : toggle joint 3 for active parts
	* 4 : toggle joint 4 for active parts
	* 5 : toggle joint 5 for active parts
	* 6 : toggle joint 6 for active parts
	*
	* X : use the X axis rotation at the active joint(s)
	* Y : use the Y axis rotation at the active joint(s)
	* Z : use the Z axis rotation at the active joint(s)
	* O : outputs the angle values for all joints in the scorpion for debugging purposes
	* P : cycles through test poses for demo
	* R : resets the model and the viewing angle
	* Q, Esc : exits the program
   */

	public void keyTyped(final KeyEvent key){
		switch (key.getKeyChar()) {
		    case 'Q':
		    case 'q':
		    case KeyEvent.VK_ESCAPE:
				new Thread() {
		        	@Override
		    	    public void run() {
						PA2.this.animator.stop();
		        	}
		    	}.start();
				System.exit(0);
				break;

			case 'T':
			case 't':
				toggleSelection(this.tail[0]);
				break;

			// toggle the scorpion's right claw
			case 'F':
				toggleSelection(this.claws[1]);
				break;
			// toggle the scorpion's left claw
			case 'f':
				toggleSelection(this.claws[0]);
				break;

			// toggle the right leg
			case 'A':
				toggleSelection(this.legs[0]);
				break;
			case 'B':
				toggleSelection(this.legs[1]);
				break;
			case 'C':
				toggleSelection(this.legs[2]);
				break;
			case 'D':
				toggleSelection(this.legs[3]);
				break;

			// toggle the left leg
			case 'a':
				toggleSelection(this.legs[4]);
				break;
			case 'b':
				toggleSelection(this.legs[5]);
				break;
			case 'c':
				toggleSelection(this.legs[6]);
				break;
			case 'd':
				toggleSelection(this.legs[7]);
				break;	

		    // print the angles of the components
   	 		case 'O':
    		case 'o':
      			printJoints();
      			break;

  		    // set the state of the hand to the next test case
		    case 'P':
		    case 'p':
				this.setModelState(this.testCases.next());
				break;

		    // set the viewing quaternion to 0 rotation
		    case 'R':
		    case 'r':
		    	this.setModelState(this.testCases.stop());
				this.viewing_quaternion.reset();
				break;

		    // change the axis of rotation at current active joint
		    case 'X':
		    case 'x':
				this.selectedAxis = Axis.X;
				break;
		    case 'Y':
		    case 'y':
				this.selectedAxis = Axis.Y;
				break;
		    case 'Z':
		    case 'z':
				this.selectedAxis = Axis.Z;
				break;
			
			// toggle which joints are affected by the current rotation
			case '1':
				for (final Leg leg : this.selectedLeg){
					toggleSelection(leg.joint1());
				}
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint1());
				}
				break;
			case '2':
				for (final Leg leg : this.selectedLeg){
					toggleSelection(leg.joint2());
				}
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint2());
				}
				break;
			case '3':
				for (final Leg leg : this.selectedLeg){
					toggleSelection(leg.joint3());
				}
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint3());
				}
				break;
			case '4':
				for (final Leg leg : this.selectedLeg){
					toggleSelection(leg.joint4());
				}
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint4());
				}
				break;			
			case '5':
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint5());
				}
				break;
			case '6':
				for (final Tail_Claws tail_claw : this.selected_tail_claw){
					toggleSelection(tail_claw.joint6());
				}
				break;

		    default:
				break;	
		}
	}

	/** Prints the joints on the System.out print stream. */
	private void printJoints() {
		this.printJoints(System.out);
	}


	/**
	* Prints the joints on the specified PrintStream.
	* @param printStream - The stream on which to print each of the components.
	*/
	private void printJoints(final PrintStream printStream) {
		for (final Component component : this.components) {
			printStream.println(component);
		}
	}


	/**
	* This method is intentionally unimplemented.
	* @param mouse - This parameter is ignored.
	*/
	public void mouseClicked(MouseEvent mouse) {
		// intentionally unimplemented
	}


  /**
   * Updates the rotation quaternion as the mouse is dragged.
   * @param mouse - The mouse drag event object.
   */
	public void mouseDragged(final MouseEvent mouse) {
		if (this.rotate_world) {
			// get the current position of the mouse
			final int x = mouse.getX();
			final int y = mouse.getY();

			// get the change in position from the previous one
			final int dx = x - this.last_x;
			final int dy = y - this.last_y;

			// create a unit vector in the direction of the vector (dy, dx, 0)
			final double magnitude = Math.sqrt(dx * dx + dy * dy);
			final float[] axis = magnitude == 0 ? new float[]{1,0,0}: // avoid dividing by 0
				new float[] { (float) (dy / magnitude),(float) (dx / magnitude), 0 };

			// calculate appropriate quaternion
			final float viewing_delta = 3.1415927f / 180.0f;
			final float s = (float) Math.sin(0.5f * viewing_delta);
			final float c = (float) Math.cos(0.5f * viewing_delta);
			final Quaternion Q = new Quaternion(c, s * axis[0], s * axis[1], s
					* axis[2]);
			this.viewing_quaternion = Q.multiply(this.viewing_quaternion);

			// normalize to counteract acccumulating round-off error
			this.viewing_quaternion.normalize();

			// save x, y as last x, y
			this.last_x = x;
			this.last_y = y;
		}
	}


	/**
	* This method is intentionally unimplemented.
	* @param mouse - This parameter is ignored.
	*/
	public void mouseEntered(MouseEvent mouse) {
		// intentionally unimplemented
	}

	/** 
	* This method is intentionally unimplemented.
	* @param mouse - This parameter is ignored.
	*/
	public void mouseExited(MouseEvent mouse) {
		// intentionally unimplemented
	}


	/**
	* This method is intentionally unimplemented. 
	* @param mouse - This parameter is ignored.
	*/
	public void mouseMoved(MouseEvent mouse) {
		// intentionally unimplemented
	}

	/**
	* Starts rotating the world if the left mouse button was released. 
	* @param mouse - The mouse press event object.
	*/
	public void mousePressed(final MouseEvent mouse) {
		if (mouse.getButton() == MouseEvent.BUTTON1) {
			this.last_x = mouse.getX();
			this.last_y = mouse.getY();
			this.rotate_world = true;
		}
	}

	/**
	* Stops rotating the world if the left mouse button was released.
	* @param mouse - The mouse release event object.
	*/
	public void mouseReleased(final MouseEvent mouse) {
		if (mouse.getButton() == MouseEvent.BUTTON1) {
			this.rotate_world = false;
		}
	}


	/**
	* {@inheritDoc}
	* 
	* @param drawable
	*          {@inheritDoc}
	* @param x
	*          {@inheritDoc}
	* @param y
	*          {@inheritDoc}
	* @param width
	*          {@inheritDoc}
	* @param height
	*          {@inheritDoc}
	*/
	public void reshape(final GLAutoDrawable drawable, final int x, final int y, final int width, final int height) {
		final GL2 gl = (GL2)drawable.getGL();

		// prevent division by zero by ensuring window has height 1 at least
		final int newHeight = Math.max(1, height);

		// compute the aspect ratio
		final double ratio = (double) width / newHeight;

		// reset the projection coordinate system before modifying it
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// set the viewport to be the entire window
		gl.glViewport(0, 0, width, newHeight);

		// set the clipping volume
		this.glu.gluPerspective(25, ratio, 0.1, 100);

		// camera positioned at (0,0,6), look at point (0,0,0), up vector (0,1,0)
		this.glu.gluLookAt(0, 0, 12, 0, 0, 0, 0, 1, 0);

		// switch back to model coordinate system
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	private void toggleSelection(final Component component) {
		if (this.selectedComponents.contains(component)) {
			this.selectedComponents.remove(component);
			component.setColor(INACTIVE_COLOR);
		} else {
			this.selectedComponents.add(component);
			component.setColor(ACTIVE_COLOR);
		}
		this.stateChanged = true;
	}


	private void toggleSelection(final Tail_Claws tc) {
		if (this.selected_tail_claw.contains(tc)) {
			this.selected_tail_claw.remove(tc);
			this.selectedComponents.removeAll(tc.joints());
			for (final Component joint : tc.joints()) {
				joint.setColor(INACTIVE_COLOR);
			}	
		} else {
			this.selected_tail_claw.add(tc);
		}
		this.stateChanged = true;
	}


	private void toggleSelection(final Leg leg) {
		if (this.selectedLeg.contains(leg)) {
			this.selectedLeg.remove(leg);
			this.selectedComponents.removeAll(leg.joints());
			for (final Component joint : leg.joints()) {
				joint.setColor(INACTIVE_COLOR);
			}	
		} else {
			this.selectedLeg.add(leg);
		}
		this.stateChanged = true;
	}

	
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	}
}
