package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.TouchSensor;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import android.graphics.Color;
@TeleOp



//@Disabled
public class Teleop extends OpMode {

    DcMotor driveFrontLeft;
    DcMotor driveFrontRight;
    DcMotor driveBackLeft;
    DcMotor driveBackRight;
    ElapsedTime x = new ElapsedTime();
   long setTime = System.currentTimeMillis();
   boolean hasRun = false;
   // Assignments for lift, however UNUSED CODE
    final double LIFTERMOTORUP      = 0.5;                            // sets rate to move servo
    final double LIFTERMOTORDOWN      = -0.5;
    
    
    
    
    @Override
    public void init() {
        driveFrontLeft = hardwareMap.dcMotor.get("fl");
        driveBackLeft = hardwareMap.dcMotor.get("bl");
        driveBackRight = hardwareMap.dcMotor.get("br");
        driveFrontRight = hardwareMap.dcMotor.get("fr");

        driveFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       
        driveFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        driveBackLeft.setDirection(DcMotor.Direction.FORWARD);
        driveFrontRight.setDirection(DcMotor.Direction.FORWARD);
        driveBackRight.setDirection(DcMotor.Direction.FORWARD);
    }
    

    public void mecanumDrive_Cartesian(double x, double y, double rotation)
    {
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = x + y + rotation;
        wheelSpeeds[1] = -x + y - rotation;
        wheelSpeeds[2] = -x + y + rotation;
        wheelSpeeds[3] = x + y - rotation;

        normalize(wheelSpeeds);

        driveFrontLeft.setPower(wheelSpeeds[0]);
        driveFrontRight.setPower(wheelSpeeds[1]);
        driveBackLeft.setPower(wheelSpeeds[2]);
        driveBackRight.setPower(wheelSpeeds[3]);
    }   //mecanumDrive_Cartesian

    private void normalize(double[] wheelSpeeds)
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                 maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }   //normalize
    @Override
    public void loop() {
        float leftY = gamepad1.left_stick_y;
        float leftX = gamepad1.left_stick_x;
        float turn = gamepad1.right_stick_x;

        telemetry.addData("GamePad Data: ", "G1LY: " + leftY + "G1LX: " + leftX + "G1Turn: " + turn);
        mecanumDrive_Cartesian(leftX, leftY, turn);
        
        
        telemetry.update();    
    }
}
