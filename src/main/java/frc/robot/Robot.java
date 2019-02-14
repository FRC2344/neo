/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  DifferentialDrive drive;
  Joystick driver;
  CANSparkMax zero,one,two,three,four,five;
  
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    zero = new CANSparkMax(0, MotorType.kBrushless);
    one = new CANSparkMax(1, MotorType.kBrushless);
    two = new CANSparkMax(2, MotorType.kBrushless);
    three = new CANSparkMax(3, MotorType.kBrushless);
    four = new CANSparkMax(4, MotorType.kBrushless);
    five = new CANSparkMax(5, MotorType.kBrushless);

    drive = new DifferentialDrive(zero, three);
    driver = new Joystick(0);

    one.follow(zero);
    two.follow(zero);

    four.follow(three);
    five.follow(three);

  }

  @Override
  public void robotPeriodic() {
    System.out.println(three.getEncoder().getPosition());
  }
  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    if(three.getEncoder().getPosition() < 4.5){
      drive.tankDrive(0, -.05, false);
    }
    else if(three.getEncoder().getPosition() >= 4.489999999){
      drive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(driver.getRawAxis(1), driver.getRawAxis(2));
    //42 to one
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
