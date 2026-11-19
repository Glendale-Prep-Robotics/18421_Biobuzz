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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name="Autonomous Blue")
public final class AutoBlue extends LinearOpMode {

    Pose2d blueStartingPosition = new Pose2d(-55,24.5,Math.toRadians(0));

    Vector2d blueShootingPosition = new Vector2d(-32.5,43.5);
    Vector2d blueGarden = new Vector2d (-57,43.5);//new Pose2d(-59, 63, Math.toRadians(180));
    double collectToGardenY = 86.5; // park with spline 315 tangent to 0 tangent then move to park in Yr
    Vector2d blueAcrossField = new Vector2d(30, 43.5);
    Vector2d blueToPark = new Vector2d(37, 65);

    Robot r;


    @Override
    public void runOpMode() throws InterruptedException {

        r = new Robot(hardwareMap, blueStartingPosition);

        // build paths
        TrajectoryActionBuilder blueToShootingPosition = r.driveTrain.actionBuilder(blueStartingPosition)
                .setTangent(Math.toRadians(10))
                .strafeToLinearHeading(blueShootingPosition, Math.toRadians(310))
                .waitSeconds(1) //remove for time?
                .endTrajectory();

        TrajectoryActionBuilder goingToGarden = blueToShootingPosition.fresh()
                .setTangent(Math.toRadians(130))
                .splineToLinearHeading(new Pose2d(blueGarden.x,blueGarden.y,Math.toRadians(90)), Math.toRadians(180));

        TrajectoryActionBuilder collectToGarden = goingToGarden.fresh()
                .setTangent(Math.toRadians(90))
                .lineToY(collectToGardenY);

        TrajectoryActionBuilder blueGardenToAcrossField = goingToGarden.fresh()
                .setTangent(Math.toRadians(315))
                .splineTo(blueAcrossField, Math.toRadians(0))
                //.splineToLinearHeading(new Pose2d(-35, -15, Math.toRadians(0)), Math.toRadians(0))
                .endTrajectory();

        TrajectoryActionBuilder acrossFieldToBlueToPark = blueGardenToAcrossField.fresh()
                .setTangent(0)
                .splineTo(blueToPark, Math.toRadians(270))
                .endTrajectory();

        // building traj
        Action blueToShootingAction = blueToShootingPosition.build();
        Action goingToGardenAction = goingToGarden.build();
        Action collectToGardenAction = collectToGarden.build();
        Action blueGardenToAcrossFieldAction = blueGardenToAcrossField.build();
        Action acrossFieldToBlueToParkAction = acrossFieldToBlueToPark.build();

        // start opmode
        waitForStart();

        Actions.runBlocking(
            new SequentialAction(
                r.activateShooter(),
                blueToShootingAction,
                r.shoot(),
                new SleepAction(2),
                goingToGardenAction, //add in extra shooting?
                new SleepAction(2),
                collectToGardenAction,
                new SleepAction(2),
                blueGardenToAcrossFieldAction,
                new SleepAction(2),
                acrossFieldToBlueToParkAction
            )
        );
    }
}
