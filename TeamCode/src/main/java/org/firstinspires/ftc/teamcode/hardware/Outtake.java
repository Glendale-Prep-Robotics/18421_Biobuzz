package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    //define all hardware parts
    private final DcMotor Outtake;

    public Outtake(HardwareMap hwm){
        Outtake = hwm.get(DcMotor.class, "Outtake");
        Outtake.setDirection(DcMotor.Direction.FORWARD);
    }
    public void toggleOuttake(boolean rightTriggerPressed) {
        if (rightTriggerPressed && Math.abs(Outtake.getPower()) < .5) {
            Outtake.setPower(1);
        } else if (rightTriggerPressed && Math.abs(Outtake.getPower()) > .5) {
            Outtake.setPower(0);
        }
    }

}
