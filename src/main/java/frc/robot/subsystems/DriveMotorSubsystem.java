package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorReverse;
import frc.robot.DeviceId.DriveMotor;

public class DriveMotorSubsystem extends SubsystemBase {
    private final DriveMotorModule leftModule;
    private final DriveMotorModule rightModule;

    public DriveMotorSubsystem() {
        this.leftModule = new DriveMotorModule(DriveMotor.FL, DriveMotor.BL, MotorReverse.FL, MotorReverse.BL);
        this.rightModule = new DriveMotorModule(DriveMotor.FR, DriveMotor.BR, MotorReverse.FR, MotorReverse.BR);
    }
    public void move(double leftSpeed, double rightSpeed) {
        this.leftModule.setDesiredState(leftSpeed);
        this.rightModule.setDesiredState(rightSpeed);
    }
    public void stopModules() {
        this.leftModule.stop();
        this.rightModule.stop();
    }
}

