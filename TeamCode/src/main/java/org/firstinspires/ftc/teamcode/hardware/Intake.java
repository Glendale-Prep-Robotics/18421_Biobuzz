package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {
    //define all hardware parts
    public final DcMotor intake;

    public enum IntakeState{INTAKING, IDLE}
    public IntakeState intakeState = null;
    ElapsedTime buttonClock = new ElapsedTime();

    double BUTTON_DELAY = 250;

    public Intake(HardwareMap hwm){
        intake = hwm.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeState = IntakeState.IDLE;
        buttonClock.reset();

    }

    public void setIntakeState(boolean intakingToggle, boolean shooting){
        //if shooting is true --> turn on intake and open gate
        //if shooting is false --> let intake be based on the toggle
        if (shooting && buttonClock.milliseconds() > BUTTON_DELAY){
            buttonClock.reset();
            intakeState=IntakeState.INTAKING;
        }
        else if(intakeState == IntakeState.IDLE && intakingToggle && buttonClock.milliseconds() > BUTTON_DELAY){
            buttonClock.reset();
            intakeState=IntakeState.INTAKING;
        }
        else if (intakeState == IntakeState.INTAKING && intakingToggle && buttonClock.milliseconds() > BUTTON_DELAY){
            buttonClock.reset();
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
        if(intakeState == IntakeState.IDLE){
            intake.setPower(0);
        }
        else if(intakeState == IntakeState.INTAKING){
            intake.setPower(1);
        }
    }

    public void turnOnIntake(){
        intake.setPower(1);
    }

}
