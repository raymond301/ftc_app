package org.firstinspires.ftc.teamcode;

/**
 * Created by Derek on 11/5/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.util.ElapsedTime;


///**
@Autonomous(name="Blue Right Beacon Hitter", group="test1")
public class B_RightBeaconHitter extends LinearOpMode {
    HardwareRealBot robot = new HardwareRealBot();
    private ElapsedTime     runtime = new ElapsedTime();
    //1440 countsperrevoulution, 2.0 gear ratio, 4.0 wheel diameter, 3.1415 pi.
    static final double COUNTS_PER_INCH         = (1440 ) / (4.0 * 3.1415);

    public HardwareRealBot getRobot() {
        return robot;
    }

    @Override
    public void runOpMode() {
        double left = 0.0;
        double right = 0.0;
        //takes hardware from the hardwaremap
        robot.init(hardwareMap);

        telemetry.addData("start?",isStarted());

        //initializing mode
        robot.rearLeftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        robot.forwardRightMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        robot.rearLeftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        robot.forwardLeftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        telemetry.addData("opmode1",opModeIsActive());
        //1; come off wall to enable turn
        encoderDrive(1.0,  40,  40, 15.0);
        //2; double forward turn
        encoderDrive(0.75,   -13, 13, 15.0);
        //3; forward to knock cap ball
        encoderDrive(0.75,   -30, -30, 20.0);


    }
    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newREARLeftTarget;
        int newREARRightTarget;
        int newFWDLeftTarget;
        int newFWDRightTarget;

        // Ensure that the opmode is still active
        telemetry.addData("opmode",opModeIsActive());
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            telemetry.addData("left current=", "%6d", robot.forwardLeftMotor.getCurrentPosition());
            telemetry.addData("right current=", "%6d", robot.forwardRightMotor.getCurrentPosition());
            newFWDLeftTarget = robot.forwardLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newFWDRightTarget = robot.forwardRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newREARLeftTarget = robot.rearLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newREARRightTarget = robot.rearRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            telemetry.addData("left current=", "%6d", newFWDLeftTarget);
            telemetry.addData("right current=", "%6d", newFWDRightTarget);
            telemetry.update();


            robot.rearLeftMotor.setTargetPosition(newREARLeftTarget);
            robot.rearRightMotor.setTargetPosition(newREARRightTarget);
            robot.forwardLeftMotor.setTargetPosition(newFWDLeftTarget);
            robot.forwardRightMotor.setTargetPosition(newFWDRightTarget);
            // Turn On RUN_TO_POSITION
            robot.rearLeftMotor.setMode(RunMode.RUN_TO_POSITION);
            robot.rearRightMotor.setMode(RunMode.RUN_TO_POSITION);
            robot.forwardLeftMotor.setMode(RunMode.RUN_TO_POSITION);
            robot.forwardRightMotor.setMode(RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.rearLeftMotor.setPower(Math.abs(speed));
            robot.rearRightMotor.setPower(Math.abs(speed));
            robot.forwardLeftMotor.setPower(Math.abs(speed));
            robot.forwardRightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && robot.anyDriveMotorsBusy() ) {

                    // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newFWDLeftTarget,  newFWDRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.rearLeftMotor.getCurrentPosition(),
                        robot.rearRightMotor.getCurrentPosition(),
                        robot.forwardLeftMotor.getCurrentPosition(),
                        robot.forwardRightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.rearLeftMotor.setPower(0);
            robot.rearRightMotor.setPower(0);
            robot.forwardLeftMotor.setPower(0);
            robot.forwardRightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.rearLeftMotor.setMode(RunMode.RUN_USING_ENCODER);
            robot.rearRightMotor.setMode(RunMode.RUN_USING_ENCODER);
            robot.forwardLeftMotor.setMode(RunMode.RUN_USING_ENCODER);
            robot.forwardRightMotor.setMode(RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
        telemetry.addData("say","end of function");
        robot.waitForTick(40);

    }



}
