package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RedAutonomous1 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Image img = null;
        try {
            img = ImageIO.read(new File("/Users/varunpratap/Documents/field2.png"));
        } catch (IOException e) {
            System.err.println("Failed to load background image: " + e.getMessage());
        }


        Pose2d Path1 = new Pose2d(60,-10,Math.toRadians(180));

        Pose2d Path2 = new Pose2d(-70,20,Math.toRadians(180));


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                //Set Bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

       // myBot.runAction(myBot.getDrive().actionBuilder(Path1)
                //.lineToX(30) //Drive to __ location
                //.strafeToLinearHeading(new Vector2d(30, -10), Math.toRadians(180))
                //.waitSeconds(1)
               // .setTangent(Math.toRadians(270))
                //.splineToLinearHeading(new Pose2d(57, -60, Math.toRadians(0)), Math.toRadians(0))
               // .strafeToLinearHeading(new Vector2d(57, -60), Math.toRadians(0))
               // .strafeToLinearHeading(new Vector2d(-35, -40), Math.toRadians(90))
                //.splineTo(new Vector2d(-35,-15), Math.toRadians(0))
                //.strafeToLinearHeading(new Vector2d(-35, -15), Math.toRadians(0))
                //.waitSeconds(.5)
                //.setTangent(0)
                //.splineTo(new Vector2d(-34,-62), Math.toRadians(270))
               // .strafeToLinearHeading(new Vector2d(-35, -62),Math.toRadians(90))
                //.build());


        myBot.runAction(myBot.getDrive().actionBuilder(Path2)
                //start at left wall blue side and shoot
                .strafeToLinearHeading(new Vector2d(-30,15), Math.toRadians(0)) //drive to hive
                .waitSeconds(1)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(-59, 63, Math.toRadians(180)), Math.toRadians(180))
                //.splineTo(new Vector2d(-59, 63), Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-59, 63), Math.toRadians(180)) //drive to the wall pick up
                .strafeToLinearHeading(new Vector2d(45,40), Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(40, 15), Math.toRadians(180))
                .waitSeconds(.5)
                .splineTo(new Vector2d(37,65), Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(37,65),Math.toRadians(270))//park at spot
                .build());





        if (img != null) {
            meepMeep.setBackground(img);
        }
        meepMeep.setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    private static void splineToLinearHeading(Pose2d pose2d, double radians) {
    }
}