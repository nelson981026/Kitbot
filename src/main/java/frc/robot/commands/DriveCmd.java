package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Drive;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import java.io.*;

public class DriveCmd extends Command {
    private final DriveMotorSubsystem driveSubsystem;
    private final MotorSubsystem motorSubsystem;
    private final XboxController controller;
    private final Timer timer;

    public DriveCmd(DriveMotorSubsystem driveSubsystem, MotorSubsystem motorSubsystem, XboxController controller) {
        this.driveSubsystem = driveSubsystem;
        this.motorSubsystem = motorSubsystem;
        this.controller = controller;
        this.timer = new Timer();
        this.addRequirements(this.driveSubsystem);
    }

    @Override
    public void execute() {
        double driveSpeed = -MathUtil.applyDeadband(this.controller.getLeftY(), Drive.DEAD_BAND) * Drive.MAX_SPEED;
        double turnSpeed = MathUtil.applyDeadband(this.controller.getRightX(), Drive.DEAD_BAND) * Drive.MAX_TURN_SPEED;
        double leftSpeed = driveSpeed + turnSpeed;
        double rightSpeed = driveSpeed - turnSpeed;
        this.driveSubsystem.move(leftSpeed, rightSpeed);
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
        double intakeVoltage = 0.0;
        double shooterVoltage = 0.0;
        SmartDashboard.putString("state", "None");
        if (this.controller.getLeftTriggerAxis() > Drive.TRIGGER_DEAD_BAND) {
            intakeVoltage = Math.log10(this.controller.getLeftTriggerAxis() * 7) * Drive.MAX_SHOOT_VOLTAGE;
            shooterVoltage = Math.log10(this.controller.getLeftTriggerAxis() * 7) * Drive.MAX_SHOOT_VOLTAGE;
        } else if (this.controller.getRightTriggerAxis() > Drive.TRIGGER_DEAD_BAND) {
            timer.start();
            intakeVoltage = Math.log10(this.controller.getRightTriggerAxis() * 10) * Drive.MAX_SHOOT_VOLTAGE;
            SmartDashboard.putString("state", "PreparingT");
            if (timer.get() > 0.5) {
                shooterVoltage = -Math.log10(this.controller.getRightTriggerAxis() * 10) * Drive.MAX_SHOOT_VOLTAGE;
                SmartDashboard.putString("state", "ShootingT");
            }
        } else if (this.controller.getAButton()) {
            intakeVoltage = 0.7 * Drive.MAX_SHOOT_VOLTAGE;
            shooterVoltage = 0.7 * Drive.MAX_SHOOT_VOLTAGE;
        } else if (this.controller.getBButton()) {
            timer.start();
            intakeVoltage = Drive.MAX_SHOOT_VOLTAGE;
            SmartDashboard.putString("state", "PreparingA");
            if (timer.get() > 0.5) {
                shooterVoltage = -Drive.MAX_SHOOT_VOLTAGE;
                SmartDashboard.putString("state", "ShootingA");
            }
        } else {
            timer.stop();
            timer.reset();
        }
        this.motorSubsystem.move(intakeVoltage, shooterVoltage);
        SmartDashboard.putNumber("Intake Voltage", intakeVoltage);
        SmartDashboard.putNumber("Shooter Voltage", shooterVoltage);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}