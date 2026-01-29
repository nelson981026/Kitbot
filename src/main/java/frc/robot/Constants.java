package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public final class Drive {
    public static final double MAX_SPEED = 0.4;
    public static final double MAX_TURN_SPEED = 0.2;
    public static final double MAX_SHOOT_VOLTAGE = 11.5;
    public static final double DEAD_BAND = 0.05;
    public static final double TRIGGER_DEAD_BAND = 0.3;
  }

  public final class MotorReverse {
    public static final boolean FL = false;
    public static final boolean FR = true;
    public static final boolean BL = false;
    public static final boolean BR = true;
  }
}
