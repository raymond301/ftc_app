package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Derek on 10/15/2016.
 */
@TeleOp(name="Bot: R2D2", group="test1")
public class tHIS_IS_TEST_2 extends LinearOpMode {
    HardwareRealBot robot = new HardwareRealBot();

    public HardwareRealBot getRobot() {
        return robot;
    }

    public void runOpMode() throws InterruptedException {
        double left;
        double right;
          /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        //// run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left =  gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            robot.leftMotor.setPower(left);
            robot.rearRightMotor.setPower(right);
            telemetry.addData("left", "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }


}
