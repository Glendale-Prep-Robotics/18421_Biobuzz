package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class Robot {

    MecanumDrive driveTrain;
    Outtake outtake;
    Intake intake;
    private Robot servo;

    public Robot(HardwareMap hwm){

        //intake = new Intake(hwm);
        //outtake = new Outtake(hwm);
        driveTrain = new MecanumDrive(hwm,new Pose2d(0,0,0));
    }

    public void toggleIntake(boolean rightBumperPressed){
        intake.toggleIntake(rightBumperPressed);
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
    public void shooterGate(boolean rightBumperPressed){
        servo.shooterGate(rightBumperPressed);
    }

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
