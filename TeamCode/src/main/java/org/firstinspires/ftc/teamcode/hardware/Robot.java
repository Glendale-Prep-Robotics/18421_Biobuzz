package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class Robot {

    MecanumDrive driveTrain;
    public Outtake outtake;
    Intake intake;


        LED frontLED_red;
        LED frontLED_green;

    public Robot(HardwareMap hwm){
        //frontLED_green = hwm.get(LED.class, "front_led_green");
        //frontLED_red = hwm.get(LED.class, "front_led_red");
        intake = new Intake(hwm);
        outtake = new Outtake(hwm);
        driveTrain = new MecanumDrive(hwm,new Pose2d(0,0,0));
    }

    public void setIntakeState(boolean intaking, boolean shooting){
        intake.setIntakeState(intaking, shooting);
    }
    public void toggleIntakeDirection(boolean leftBumperPressed){
        intake.toggleIntakeDirection(leftBumperPressed);
    }
    public void turnOnShooter(){
        outtake.turnOnShooter();
    }
    public void turnOffShooter(){
        outtake.turnOffShooter();
    }
    public void shooterGate(boolean button){
        outtake.setShooterGate(button);
    }
    /*
    public void turnOnYellowIndictors(){
        frontLED_green.on();
        frontLED_red.on();

    }

     */

    public void driveRobot(double drive, double strafe, double turn){
        driveTrain.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -drive,
                        -strafe
                ),
                -turn
        ));
    }
}
