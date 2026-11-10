package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name="Autonomous Testing Grounds")
public final class AutoTestingGrounds extends LinearOpMode {

    Pose2d redStartingPosition = new Pose2d(60,-10,Math.toRadians(180));

    //Pose2d blueStartingPosition = new Pose2d(-70,20,Math.toRadians(180));
    Vector2d redShootingPosition = new Vector2d(30, -10);
    Pose2d redGarden = new Pose2d(57, -60, Math.toRadians(0));
    Vector2d redAcrossField = new Vector2d(-35, -40);
    Vector2d redToPark = new Vector2d(-34, -62);

    Robot r;

    @Override
    public void runOpMode() throws InterruptedException {
        r = new Robot(hardwareMap, redStartingPosition);

        // build paths
        TrajectoryActionBuilder redToShootingPositionBuilder = r.driveTrain.actionBuilder(redStartingPosition)
            .lineToX(30) // Drive to __ location
            .strafeToLinearHeading(redShootingPosition, Math.toRadians(180))
            .waitSeconds(1)
            .endTrajectory();

        TrajectoryActionBuilder goingToGardenBuilder = redToShootingPositionBuilder.fresh()
            .setTangent(Math.toRadians(270))
            .splineToLinearHeading(redGarden, Math.toRadians(0));

        TrajectoryActionBuilder redGardenToAcrossFieldBuilder = goingToGardenBuilder.fresh()
            .strafeToLinearHeading(redAcrossField, Math.toRadians(90))
            .splineToLinearHeading(new Pose2d(-35, -15, Math.toRadians(0)), Math.toRadians(0))
            .endTrajectory();

        TrajectoryActionBuilder acrossFieldToRedToParkBuilder = redGardenToAcrossFieldBuilder.fresh()
            .setTangent(0)
            .splineTo(redToPark, Math.toRadians(270))
            .strafeToLinearHeading(new Vector2d(-35, -62), Math.toRadians(90))
            .endTrajectory();

        // building traj
        Action redToShootingAction = redToShootingPositionBuilder.build();
        Action goingToGardenAction = goingToGardenBuilder.build();
        Action redGardenToAcrossFieldAction = redGardenToAcrossFieldBuilder.build();
        Action acrossFieldToRedToParkAction = acrossFieldToRedToParkBuilder.build();

        // start opmode
        waitForStart();

        Actions.runBlocking(
            new SequentialAction(
                redToShootingAction,
                new SleepAction(2),
                goingToGardenAction,
                redGardenToAcrossFieldAction,
                acrossFieldToRedToParkAction
            )
        );
    }
}
