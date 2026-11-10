package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Outtake {
    //define all hardware parts
    public final DcMotorEx outtake;
    public Servo shooterGate;

    //Constants
    public static double SHOOTER_GATE_CLOSED_POSITION = 0;
    public static double SHOOTER_GATE_OPEN_POSITION = 1;
    public static double SHOOTER_SPEED = 850;
    public static double SHOOTER_OFF = 0;
    public static double Kp = 700;
    public static double Kd = 0;
    public static double Ki = 0;
    public static double Kf = 19;

    public Outtake(HardwareMap hwm){
        outtake = hwm.get(DcMotorEx.class, "Outtake");
        shooterGate = hwm.get(Servo.class, "ShooterGate");
        outtake.setDirection(DcMotor.Direction.FORWARD);
        shooterGate.setDirection(Servo.Direction.REVERSE);

    }
    public void turnOnShooter(){
        outtake.setVelocity(SHOOTER_SPEED);
    }
    public void turnOffShooter(){
        outtake.setVelocity(SHOOTER_OFF);
    }

    public void setShooterGate(boolean rightBumperPressed) {
        if (rightBumperPressed){
            shooterGate.setPosition(SHOOTER_GATE_OPEN_POSITION);
        }
        else{
            shooterGate.setPosition(SHOOTER_GATE_CLOSED_POSITION);
        }
    }

}
