package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.EventLoopManager;

@TeleOp(name="Tushbot", group="Pushbot")
public class Tushbot extends OpMode
{
    public DcMotor fr = null;
    public DcMotor fl = null;
    public DcMotor br = null;
    public DcMotor bl = null;

    @Override
    public void init()
    {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);

        // fr.setDirection(DcMotorSimple.Direction.FORWARD);
        // fl.setDirection(DcMotorSimple.Direction.REVERSE);
        // br.setDirection(DcMotorSimple.Direction.FORWARD);
        // bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void init_loop()
    {

    }

    @Override
    public void start()
    {

    }
    
    @Override
    public void loop()
    {
        // drive code
        final float right = -gamepad1.left_stick_y;
        final float left = gamepad1.right_stick_y;
        fl.setPower(left);
        fr.setPower(right);
        bl.setPower(left);
        br.setPower(right);
        
        telemetry.addData("System Check", "All Systems Go");
        telemetry.update();
    }
}