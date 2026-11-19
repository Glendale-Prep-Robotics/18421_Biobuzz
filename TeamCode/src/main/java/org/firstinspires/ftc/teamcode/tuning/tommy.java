package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.Vector;

@Autonomous(name="tommy")
public final class tommy extends LinearOpMode {

    Pose2d blueStartingPosition = new Pose2d(-55,24.5,Math.toRadians(0));

    Pose2d tommy = new Pose2d(0,0,0);
    Vector2d tommyGo = new Vector2d(-2,2);

    Robot r;


    @Override
    public void runOpMode() throws InterruptedException {

        r = new Robot(hardwareMap, blueStartingPosition);

        TrajectoryActionBuilder doSomething = r.driveTrain.actionBuilder(tommy)
                .strafeToLinearHeading(tommyGo, 0);



        // building traj
        Action doSomethingAction = doSomething.build();

        // start opmode
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        doSomethingAction
                )
        );
    }
}
