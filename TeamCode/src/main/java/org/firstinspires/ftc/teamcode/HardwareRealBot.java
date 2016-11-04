package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareRealBot {
    /* Public OpMode members. */
    public DcMotor forwardLeftMotor = null;
    public DcMotor rearLeftMotor = null;
    public DcMotor forwardRightMotor = null;
    public DcMotor rearRightMotor = null;
    public DcMotor sweeperMotor = null;
    public DcMotor conveyerMotor = null;
    //public DcMotor leftShooterMotor = null;
    //public DcMotor rightShooterMotor = null;


        /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareRealBot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

            // right and left motors
        // forward left motor
        forwardLeftMotor = hwMap.dcMotor.get("fl_drive");
        forwardLeftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        forwardLeftMotor.setPower(0);
        forwardLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rear left motor
        rearLeftMotor = hwMap.dcMotor.get("rl_drive");
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rearLeftMotor.setPower(0);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // forward right motor
        forwardRightMotor = hwMap.dcMotor.get("fr_drive");
        forwardRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        forwardRightMotor.setPower(0);
        forwardRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // rear right motor
        rearRightMotor = hwMap.dcMotor.get("rr_drive");
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rearRightMotor.setPower(0);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

        //sweeper
        sweeperMotor = hwMap.dcMotor.get("s");
        sweeperMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        sweeperMotor.setPower(0);
        sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //conveyer
        conveyerMotor = hwMap.dcMotor.get("c");
        conveyerMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        conveyerMotor.setPower(0);
        conveyerMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }


            //Shooter motors
        //leftShooterMotor
        /*leftShooterMotor = hwMap.dcMotor.get("lsm");
        leftShooterMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        leftShooterMotor.setPower(0);
        leftShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //rightShooterMotor
        rightShooterMotor = hwMap.dcMotor.get("rcm");
        rightShooterMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rightShooterMotor.setPower(0);
        rightShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    */

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

