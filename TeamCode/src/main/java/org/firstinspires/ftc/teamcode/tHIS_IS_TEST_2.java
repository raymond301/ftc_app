package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

///**
@TeleOp(name="Bot: tele-op", group="test1")
public class tHIS_IS_TEST_2 extends LinearOpMode {
    HardwareRealBot robot = new HardwareRealBot();

    double servoPosition = 0.5;

    public HardwareRealBot getRobot() {
        return robot;
    }

    public void runOpMode() throws InterruptedException {
        double left = 0.0;
        double right = 0.0;
          /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        gamepad1.setJoystickDeadzone((float) 0.001);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        //// run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            /*
               first game pad only
             */

            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            telemetry.addData("left=", "%.4f", left);
            telemetry.addData("right=", "%.4f", right);

            if (gamepad1.left_bumper) {
                left = left * 0.33;
                right = right * 0.33;
                telemetry.addData("Right Bumper", "2/3 Drive");
            } else if (gamepad1.right_bumper) {
                left = left * 0.66;
                right = right * 0.66;
                telemetry.addData("Left Bumper", "1/3 Drive");
            }
            telemetry.update();

            robot.forwardLeftMotor.setPower(left);
            robot.rearLeftMotor.setPower(left);
            robot.forwardRightMotor.setPower(right);
            robot.rearRightMotor.setPower(right);


               //second game pad only




            if(gamepad2.b) {
                robot.conveyerMotor.setPower(75.0);
                telemetry.addData("conveyor","running backwards");
            }

            if(gamepad2.x) {
                robot.conveyerMotor.setPower(-75.0);
                telemetry.addData("conveyor","running forwards");
            }

            if (gamepad2.a) {
                robot.conveyerMotor.setPower(0);
                telemetry.addData("conveyor","not running");
            }

            double sweeperspeed = gamepad2.left_stick_y;
            robot.sweeperMotor.setPower(sweeperspeed);


                if (gamepad2.right_trigger > 0.5) {
                    telemetry.addData("shooter", "10% power");
                    robot.leftShooterMotor.setPower(-0.1);
                    robot.rightShooterMotor.setPower(0.1);
                }



                if (gamepad2.left_bumper)  {
                    robot.leftShooterMotor.setPower(0);
                    robot.rightShooterMotor.setPower(0);
                    servoPosition = 0.7;
                    telemetry.addData("shooter","off");
                    telemetry.addData("servo","=servoposition");
                }

            if (gamepad2.left_trigger > 0.5 )  {
                telemetry.addData("shooter", "25% power");
                robot.leftShooterMotor.setPower(-0.25);
                robot.rightShooterMotor.setPower(0.25);

            }

                else if (gamepad2.right_bumper) {
                    telemetry.addData("shooter", "half power");
                    robot.leftShooterMotor.setPower(-0.5);
                    robot.rightShooterMotor.setPower(0.5);
                }

            if (gamepad2.dpad_up) {
                servoPosition = 0.31;
            }

            robot.sa.setPosition(Range.clip(servoPosition, 0.07, 0.31));

            telemetry.addData("Servo ", servoPosition);

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40); //

        }
    }

}