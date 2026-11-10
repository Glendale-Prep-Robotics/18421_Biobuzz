/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.hardware.Outtake.Kd;
import static org.firstinspires.ftc.teamcode.hardware.Outtake.Kf;
import static org.firstinspires.ftc.teamcode.hardware.Outtake.Ki;
import static org.firstinspires.ftc.teamcode.hardware.Outtake.Kp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Driver Control", group = "Concept")
@Config
public class DriverControl extends OpMode{
    /**
     * TASKS
     * Robot turns on shooter at start, and off at end. - DONE
     * Toggle the intake on and off
     * Toggle direction of intake
     *
     * Gamepad mapping
     * A -
     * B -
     * X -
     * Y -
     * LeftBumper - toggle intake direction
     * RightBumper - open shooter gate (controlled by gamepad 2)
     * RightTrigger - shoot
     * LeftTrigger - toggle intake
     */

  private ElapsedTime runtime = new ElapsedTime();
  public Robot r = null;
  @Override
  public void init() {
      telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
      r = new Robot(hardwareMap,new Pose2d(0,0,0));
      telemetry.addData("Status","Initialized");
  }

  @Override
  public void init_loop() {
 }

  @Override
  public void start() {
    runtime.reset();

  }

  @Override
  public void loop() {
    telemetry.addData("Status", "Run Time: " + runtime.toString());
    boolean intakePower = gamepad2.leftTriggerWasPressed();
    boolean intakeDirection = gamepad2.leftBumperWasPressed();
    double drive = gamepad1.left_stick_y;
    double turn = gamepad1.right_stick_x;
    double strafe = gamepad1.left_stick_x;

    boolean shooting = gamepad2.right_trigger > .1;
    boolean intaking = gamepad2.left_trigger > .1;
    if (gamepad2.x){
        r.turnOnShooter();
    }
    if (gamepad2.b){
        r.turnOffShooter();
    }

    r.driveRobot(drive, strafe, turn);
    r.setIntakeState(intaking, shooting);
    //TODO Check if this behaves properly.  Might need a different condition for shooting
    r.toggleIntakeDirection(intakeDirection);
    r.shooterGate(shooting);
    r.intake.updateIntake();
    r.outtake.outtake.setVelocityPIDFCoefficients(Kp, Ki, Kd, Kf);

    telemetry.addData("Gamepad2 left trigger", intaking);
    telemetry.addData("Intake State", r.intake.intakeState);
    telemetry.addData("shooter vel: ", r.outtake.outtake.getVelocity());
    telemetry.addData("Status", "Run Time" + runtime.toString());

    PIDFCoefficients pidf = r.outtake.outtake.getPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER);

      telemetry.addData("P,I,D,F (orig)", "%.04f, %.04f, %.04f, %.04f",
              pidf.p, pidf.i, pidf.d, pidf.f);

  }

  @Override
  public void stop() {

  }
}
