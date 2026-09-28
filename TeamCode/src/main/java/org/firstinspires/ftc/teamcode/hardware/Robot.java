package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    Intake intake = null;

    public Robot(HardwareMap hwm){
        intake = new Intake(hwm);
    }

    public void toggleIntake(boolean rightBumperPressed){
        intake.toggleIntake(rightBumperPressed);
    }
    public void toggleIntakeDirection(boolean leftBumperPressed){
        intake.toggleIntakeDirection(leftBumperPressed);
    }
}
