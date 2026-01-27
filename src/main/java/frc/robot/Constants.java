package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public final class Drive {
    public static final double MAX_SPEED = 0.4;
    public static final double MAX_TURN_SPEED = 0.2;
    public static final double MAX_SHOOT_VOLTAGE = 12.0;
    public static final double DEAD_BAND = 0.05;
    public static final double TRIGGER_DEAD_BAND = 0.3;
  }

  public final class MotorReverse {
    public static final boolean FL = true;
    public static final boolean FR = false;
    public static final boolean BL = true;
    public static final boolean BR = false;
  }
}
