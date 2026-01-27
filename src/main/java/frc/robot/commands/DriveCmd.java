package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Drive;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.MotorSubsystem;

public class DriveCmd extends Command {
    private final DriveMotorSubsystem driveSubsystem;
    private final MotorSubsystem motorSubsystem;
    private final XboxController controller;

    public DriveCmd(DriveMotorSubsystem driveSubsystem, MotorSubsystem motorSubsystem, XboxController controller) {
        this.driveSubsystem = driveSubsystem;
        this.motorSubsystem = motorSubsystem;
        this.controller = controller;
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
        if(this.controller.getLeftTriggerAxis()>Drive.TRIGGER_DEAD_BAND){
            intakeVoltage = Drive.MAX_SHOOT_VOLTAGE*0.7;
            shooterVoltage = Drive.MAX_SHOOT_VOLTAGE*0.7;
        }
        else if(this.controller.getRightTriggerAxis()>Drive.TRIGGER_DEAD_BAND){
            intakeVoltage = Drive.MAX_SHOOT_VOLTAGE;
            shooterVoltage = -Drive.MAX_SHOOT_VOLTAGE;
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