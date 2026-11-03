package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

@Autonomous(name="Autonomous Testing Grounds")
public final class AutoTestingGrounds extends LinearOpMode {

    Robot r = new Robot(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);


        waitForStart();

        Actions.runBlocking(
            new SequentialAction(
                    r.startMotor(),
                    new SleepAction(2),
                    r.setMotorSpeed(.5),
                    new SleepAction(10),
                    r.stopMotor()
                    )
    }
}
