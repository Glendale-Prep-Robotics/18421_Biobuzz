package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    //define all hardware parts
    public final DcMotor intake;

    public enum IntakeState{INTAKING, IDLE}
    IntakeState intakeState = null;

    public Intake(HardwareMap hwm){
        intake = hwm.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeState = IntakeState.IDLE;

    }



    public void setIntakeState(boolean intakingToggle, boolean shooting){
        //if shooting is true --> turn on intake and open gate
        //if shooting is false --> let intake be based on the toggle
        if (shooting){
            intakeState=IntakeState.INTAKING;
        }
        else if(intakeState == IntakeState.IDLE && intakingToggle){
            intakeState=IntakeState.INTAKING;
        }
        else if (intakeState == IntakeState.INTAKING && intakingToggle){
            intakeState=IntakeState.IDLE;
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

    public void updateIntake(){
        if(intakeState == intakeState.IDLE){
            intake.setPower(0);
        }
        else if(intakeState == intakeState.INTAKING){
            intake.setPower(1);
        }
    }

}
