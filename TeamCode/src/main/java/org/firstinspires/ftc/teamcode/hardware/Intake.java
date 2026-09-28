package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    //define all hardware parts
    private DcMotor intake = null;

    public Intake(HardwareMap hwm){
        intake = hwm.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
    }

    public void toggleIntake(boolean rightTriggerPressed){
        if(rightTriggerPressed && Math.abs(intake.getPower())<.5){
            intake.setPower(1);
        }
        else if (rightTriggerPressed && Math.abs(intake.getPower())>.5){
            intake.setPower(0);
        }
    }

    public void toggleIntakeDirection(boolean leftBumperPressed){
        if (leftBumperPressed && intake.getDirection()== DcMotor.Direction.FORWARD){
            intake.setDirection(DcMotor.Direction.REVERSE);
        }
        else if (leftBumperPressed && intake.getDirection()== DcMotor.Direction.REVERSE){
            intake.setDirection(DcMotor.Direction.FORWARD);
        }
    }

}
