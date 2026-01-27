package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCmd;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.MotorSubsystem;

public class RobotContainer {
  private final GamepadJoystick joystick = new GamepadJoystick(GamepadJoystick.CONTROLLER_PORT);
  private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
  private final MotorSubsystem motorSubsystem = new MotorSubsystem();
  private final DriveCmd driveJoystickCmd = new DriveCmd(driveMotorSubsystem, motorSubsystem, joystick);

  public RobotContainer() {
    this.driveMotorSubsystem.setDefaultCommand(this.driveJoystickCmd);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}