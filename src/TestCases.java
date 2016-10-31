/**
 * @author Rosy Chen
 * @class: CS480: Introduction to Computer Graphics 
 * @assignment: PA2
 * @due_date: 10/18/2016
 * 
 * TestCases.java - generates 5 different test cases. 
 * Every click of the letter 'P'/'p' will generate a new test case. 
 * Each test case has the scorpion at a different pose
 */


import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TestCases extends CyclicIterator<Map<String, Angled>> {

  Map<String, Angled> stop() {
    return this.stop;
  }

  private final Map<String, Angled> stop;

  @SuppressWarnings("unchecked")
  TestCases() {
    this.stop = new HashMap<String, Angled>();
    final Map<String, Angled> peace = new HashMap<String, Angled>();
    final Map<String, Angled> fist = new HashMap<String, Angled>();
    final Map<String, Angled> shaka = new HashMap<String, Angled>();
    final Map<String, Angled> spread = new HashMap<String, Angled>();
    final Map<String, Angled> claw = new HashMap<String, Angled>();

    super.add(stop, peace, fist, shaka, spread, claw);

    // the body does not change through any of the test cases
    stop.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    peace.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    fist.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    shaka.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    spread.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    claw.put(PA2.BODY_NAME, new BaseAngled(0, 0, 0));
    
    // the stop test case
    stop.put(PA2.TAIL_1_NAME, new BaseAngled(-30, 180, 0));
    stop.put(PA2.TAIL_2_NAME, new BaseAngled(30, 0, 0));
    stop.put(PA2.TAIL_3_NAME, new BaseAngled(40, 0, 0));
    stop.put(PA2.TAIL_4_NAME, new BaseAngled(30, 0, 0));
    stop.put(PA2.TAIL_5_NAME, new BaseAngled(30, 0, 0));
    stop.put(PA2.TAIL_6_NAME, new BaseAngled(35, 0, 0));   
    stop.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    stop.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -20, 0));
    stop.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 45, 0));
    stop.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    stop.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    stop.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 20, 0));
    stop.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -45, 0));
    stop.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    stop.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 90, 0));
    stop.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    stop.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -90, 0));
    stop.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    stop.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    stop.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(80, 0, 0));

    // test case 1
    peace.put(PA2.TAIL_1_NAME, new BaseAngled(-15, 180, 0));
    peace.put(PA2.TAIL_2_NAME, new BaseAngled(30, 0, 0));
    peace.put(PA2.TAIL_3_NAME, new BaseAngled(40, 0, 0));
    peace.put(PA2.TAIL_4_NAME, new BaseAngled(30, 0, 0));
    peace.put(PA2.TAIL_5_NAME, new BaseAngled(30, 0, 0));
    peace.put(PA2.TAIL_6_NAME, new BaseAngled(40, 0, 0));   
    peace.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    peace.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    peace.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 90, 0));
    peace.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -20, 0));
    peace.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 45, 0));
    peace.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    peace.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(30, 0, 0));
    peace.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 75, 0));
    peace.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -95, 0));
    peace.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 20, 0));
    peace.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -45, 0));
    peace.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    peace.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 90, 0));
    peace.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    peace.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    peace.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    peace.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 90, 0));
    peace.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    peace.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    peace.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    peace.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 90, 0));
    peace.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    peace.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    peace.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    peace.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 90, 0));
    peace.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    peace.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    peace.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    peace.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -90, 0));
    peace.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(35, 0, 0));
    peace.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-95, 0, 0));
    peace.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(70, 0, 0));
    peace.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -90, 0));
    peace.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(35, 0, 0));
    peace.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-95, 0, 0));
    peace.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(70, 0, 0));
    peace.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -90, 0));
    peace.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(35, 0, 0));
    peace.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-95, 0, 0));
    peace.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(70, 0, 0));
    peace.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -90, 0));
    peace.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(35, 0, 0));
    peace.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-95, 0, 0));
    peace.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(70, 0, 0));

    // test case 2
    fist.put(PA2.TAIL_1_NAME, new BaseAngled(-30, 180, 0));
    fist.put(PA2.TAIL_2_NAME, new BaseAngled(30, 0, 0));
    fist.put(PA2.TAIL_3_NAME, new BaseAngled(40, 0, 0));
    fist.put(PA2.TAIL_4_NAME, new BaseAngled(30, 0, 0));
    fist.put(PA2.TAIL_5_NAME, new BaseAngled(30, 0, 0));
    fist.put(PA2.TAIL_6_NAME, new BaseAngled(20, 0, 0));   
    fist.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(15, 0, 0));
    fist.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 65, 0));
    fist.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -30, 0));
    fist.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 70, 0));
    fist.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    fist.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    fist.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 90, 0));
    fist.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 20, 0));
    fist.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -45, 0));
    fist.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    fist.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 90, 0));
    fist.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(60, 0, 0));
    fist.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-85, 0, 0));
    fist.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 90, 0));
    fist.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(60, 0, 0));
    fist.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-85, 0, 0));
    fist.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 90, 0));
    fist.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(60, 0, 0));
    fist.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-85, 0, 0));
    fist.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 90, 0));
    fist.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(60, 0, 0));
    fist.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-85, 0, 0));
    fist.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    
    fist.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    fist.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    fist.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    fist.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    fist.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    fist.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    fist.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    fist.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -90, 0));
    fist.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    fist.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    fist.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(80, 0, 0));




    // test case 3
    shaka.put(PA2.TAIL_1_NAME, new BaseAngled(-30, 180, 0));
    shaka.put(PA2.TAIL_2_NAME, new BaseAngled(30, 0, 0));
    shaka.put(PA2.TAIL_3_NAME, new BaseAngled(40, 0, 0));
    shaka.put(PA2.TAIL_4_NAME, new BaseAngled(20, 0, 0));
    shaka.put(PA2.TAIL_5_NAME, new BaseAngled(30, 0, 0));
    shaka.put(PA2.TAIL_6_NAME, new BaseAngled(35, 0, 0));   
    shaka.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    shaka.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    shaka.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 70, 0));
    shaka.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -30, 0));
    shaka.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 65, 0));
    shaka.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    shaka.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    shaka.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 90, 0));
    shaka.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -70, 0));
    shaka.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 20, 0));
    shaka.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -65, 0));
    shaka.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    shaka.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 90, 0));
    shaka.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 90, 0));
    shaka.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 90, 0));
    shaka.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 90, 0));
    shaka.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -90, 0));
    shaka.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -90, 0));
    shaka.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -90, 0));
    shaka.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    shaka.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -90, 0));
    shaka.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    shaka.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    shaka.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(80, 0, 0));


    // test case 4
    spread.put(PA2.TAIL_1_NAME, new BaseAngled(-30, 180, 0));
    spread.put(PA2.TAIL_2_NAME, new BaseAngled(20, 0, 0));
    spread.put(PA2.TAIL_3_NAME, new BaseAngled(20, 0, 0));
    spread.put(PA2.TAIL_4_NAME, new BaseAngled(20, 0, 0));
    spread.put(PA2.TAIL_5_NAME, new BaseAngled(50, 0, 0));
    spread.put(PA2.TAIL_6_NAME, new BaseAngled(20, 0, 0));   
    spread.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(15, 0, 0));
    spread.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    spread.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 90, 0));
    spread.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -30, 0));
    spread.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 45, 0));
    spread.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    spread.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(15, 0, 0));
    spread.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 90, 0));
    spread.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -90, 0));
    spread.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 30, 0));
    spread.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -45, 0));
    spread.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    
    spread.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 100, 0));
    spread.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 100, 0));
    spread.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 100, 0));
    spread.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 100, 0));
    spread.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -100, 0));
    spread.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -100, 0));
    spread.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -100, 0));
    spread.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    spread.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -100, 0));
    spread.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(45, 0, 0));
    spread.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    spread.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(80, 0, 0));



    // test class 5
    claw.put(PA2.TAIL_1_NAME, new BaseAngled(-20, 180, 0));
    claw.put(PA2.TAIL_2_NAME, new BaseAngled(50, 0, 0));
    claw.put(PA2.TAIL_3_NAME, new BaseAngled(50, 0, 0));
    claw.put(PA2.TAIL_4_NAME, new BaseAngled(50, 0, 0));
    claw.put(PA2.TAIL_5_NAME, new BaseAngled(45, 0, 0));
    claw.put(PA2.TAIL_6_NAME, new BaseAngled(45, 0, 0));   
    claw.put(PA2.LEFT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    claw.put(PA2.LEFT_CLAW_2_NAME, new BaseAngled(0, -90, 0));
    claw.put(PA2.LEFT_CLAW_3_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.LEFT_CLAW_4_NAME, new BaseAngled(0, -20, 0));
    claw.put(PA2.LEFT_CLAW_5_NAME, new BaseAngled(0, 70, 0));
    claw.put(PA2.LEFT_CLAW_6_NAME, new BaseAngled(0, 60, 0));
    claw.put(PA2.RIGHT_CLAW_1_NAME, new BaseAngled(0, 0, 0));
    claw.put(PA2.RIGHT_CLAW_2_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.RIGHT_CLAW_3_NAME, new BaseAngled(0, -90, 0));
    claw.put(PA2.RIGHT_CLAW_4_NAME, new BaseAngled(0, 20, 0));
    claw.put(PA2.RIGHT_CLAW_5_NAME, new BaseAngled(0, -70, 0));
    claw.put(PA2.RIGHT_CLAW_6_NAME, new BaseAngled(0, -60, 0));
    claw.put(PA2.LEFT_LEG_A1_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.LEFT_LEG_A2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.LEFT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.LEFT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.LEFT_LEG_B1_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.LEFT_LEG_B2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.LEFT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.LEFT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.LEFT_LEG_C1_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.LEFT_LEG_C2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.LEFT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.LEFT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.LEFT_LEG_D1_NAME, new BaseAngled(0, 90, 0));
    claw.put(PA2.LEFT_LEG_D2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.LEFT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.LEFT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.RIGHT_LEG_A1_NAME, new BaseAngled(0, -80, 0));
    claw.put(PA2.RIGHT_LEG_A2_NAME, new BaseAngled(40, 0, 0));
    claw.put(PA2.RIGHT_LEG_A3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.RIGHT_LEG_A4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.RIGHT_LEG_B1_NAME, new BaseAngled(0, -90, 0));
    claw.put(PA2.RIGHT_LEG_B2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.RIGHT_LEG_B3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.RIGHT_LEG_B4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.RIGHT_LEG_C1_NAME, new BaseAngled(0, -90, 0));
    claw.put(PA2.RIGHT_LEG_C2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.RIGHT_LEG_C3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.RIGHT_LEG_C4_NAME, new BaseAngled(80, 0, 0));
    claw.put(PA2.RIGHT_LEG_D1_NAME, new BaseAngled(0, -90, 0));
    claw.put(PA2.RIGHT_LEG_D2_NAME, new BaseAngled(20, 0, 0));
    claw.put(PA2.RIGHT_LEG_D3_NAME, new BaseAngled(-135, 0, 0));
    claw.put(PA2.RIGHT_LEG_D4_NAME, new BaseAngled(80, 0, 0));
  }
 
}
