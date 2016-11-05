package org.firstinspires.ftc.teamcode;

/**
 * Created by Derek on 11/5/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


///**
@Autonomous(name="auto: bulldozer", group="test1")
public class first_auto extends LinearOpMode {
    HardwareRealBot robot = new HardwareRealBot();
    //1440 countsperrevoulution, 2.0 gear ratio, 4.0 wheel diameter, 3.1415 pi.
    static final double COUNTS_PER_INCH         = (1440 * 2.0) / (4.0 * 3.1415);

    public HardwareRealBot getRobot() {
        return robot;
    }

    public void runOpMode() throws InterruptedException {
        double left = 0.0;
        double right = 0.0;
        //takes hardware from the hardwaremap
        robot.init(hardwareMap);
            //initializing mode
        robot.rearLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.forwardRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rearLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.forwardLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();



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
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.forwardLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.forwardRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftTarget = robot.rearLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rearRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            robot.rearLeftMotor.setTargetPosition(newLeftTarget);
            robot.rearRightMotor.setTargetPosition(newRightTarget);
            robot.forwardLeftMotor.setTargetPosition(newLeftTarget);
            robot.forwardRightMotor.setTargetPosition(newRightTarget);
            // Turn On RUN_TO_POSITION
            robot.rearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.rearLeftMotor.setPower(Math.abs(speed));
            robot.rearRightMotor.setPower(Math.abs(speed));
            robot.forwardLeftMotor.setPower(Math.abs(speed));
            robot.forwardRightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.rearLeftMotor.isBusy() && robot.rearRightMotor.isBusy()))
                    (robot.forwardLeftMotor.isBusy() && robot.forwardRightMotor.isBusy())) {


                    // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
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
            robot.rearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.forwardLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.forwardRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }



}
