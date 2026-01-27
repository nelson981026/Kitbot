package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DeviceId;

public class MotorSubsystem extends SubsystemBase {
    private final SparkMax intake;
    private final SparkMax shooter;

    public MotorSubsystem() {
        // 創建 motor 物件
        this.intake = new SparkMax(DeviceId.DriveMotor.intake, MotorType.kBrushed);
        this.shooter = new SparkMax(DeviceId.DriveMotor.shooter, MotorType.kBrushed);

        // SparkMax 設定
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kCoast); // kBrake 停止後鎖住馬達, kCoast 停止後保持慣性
        config.smartCurrentLimit(60); // 電流限制
        config.inverted(true); // 是否反轉

        // Apply到motor
        this.intake.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        this.shooter.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void move(double intakeVoltage,double shooterVoltage) {
        this.intake.setVoltage(intakeVoltage);
        this.shooter.setVoltage(shooterVoltage);
    }

    public void stop() {
        this.intake.stopMotor();
        this.shooter.stopMotor();
    }
}