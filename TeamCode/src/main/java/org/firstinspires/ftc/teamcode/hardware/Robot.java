package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class Robot {

    public MecanumDrive driveTrain;
    public Outtake outtake;
    Intake intake;


        LED frontLED_red;
        LED frontLED_green;

    public Robot(HardwareMap hwm,Pose2d pose){
        //frontLED_green = hwm.get(LED.class, "front_led_green");
        //frontLED_red = hwm.get(LED.class, "front_led_red");
        intake = new Intake(hwm);
        outtake = new Outtake(hwm);
        driveTrain = new MecanumDrive(hwm,pose);
    }

    public void setIntakeState(boolean intaking, boolean shooting){
        intake.setIntakeState(intaking, shooting);
    }
    public void toggleIntakeDirection(boolean leftBumperPressed){
        intake.toggleIntakeDirection(leftBumperPressed);
    }
    public void turnOnShooter(){
        outtake.turnOnShooter();
    }
    public void turnOffShooter(){
        outtake.turnOffShooter();
    }
    public void shooterGate(boolean button){
        outtake.setShooterGate(button);
    }
    /*
    public void turnOnYellowIndictors(){
        frontLED_green.on();
        frontLED_red.on();

    }

     */

    public void driveRobot(double drive, double strafe, double turn){
        driveTrain.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -drive,
                        -strafe
                ),
                -turn
        ));
    }

    //Example Action that is an "instant action"
    public class StartMotor implements Action {
        /**
         * This action only sets the power of a motor
         * @param packet
         * @return
         */
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            intake.intake.setPower(1);
            return false; //Think of this as "I should continue", if "false" then done with action
        }
    }

    public Action startMotor(){return new StartMotor();}

    public class StopMotor implements Action {
        /**
         * This action only sets the power of a motor
         *
         * @param packet
         * @return
         */
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            intake.intake.setPower(0);
            packet.addLine("This is a telemetry packet");
            return false; //Think of this as "I should continue", if "false" then done with action
        }
    }

    public Action stopMotor(){return new StopMotor();}

    public class SetMotorSpeed implements Action {
        /**
         * This action only sets the power of a motor
         *
         * @param packet
         * @return
         */
        double speed;

        public SetMotorSpeed(double inputSpeed){
            speed = inputSpeed;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            intake.intake.setPower(speed);
            packet.addLine("This is a telemetry packet");
            return false; //Think of this as "I should continue", if "false" then done with action
        }
    }

    public Action setMotorSpeed(double speed){return new SetMotorSpeed(speed);}

    public Action startMotorBasic(){return setMotorSpeed(1);}
    public Action stopMotorBasic(){return setMotorSpeed(0); }

    public class SetServoPosition implements Action {
        /**
         * This action only sets the position and waits a bit of time (definable in the action sequence.
         * @param packet
         * @return
         */
        double position;
        double endTimeCondition;
        ElapsedTime clock = null; //is this the only way to do this?
        public SetServoPosition(double pos, double endMilliseconds){
            position = pos;
            endTimeCondition = endMilliseconds;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (clock == null){
                clock = new ElapsedTime();
            }
            outtake.shooterGate.setPosition(position);
            if (clock.milliseconds()<endTimeCondition){
                return true;
            }
            else{
                return false;
            }

        }
    }



    //Example Action that has a condition to complete


    public class StartIntakeMotors implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.intake.setPower(1.0);
            return false;
        }
    }



}
