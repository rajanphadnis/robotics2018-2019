package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.EventLoopManager;

/**
 * Created by KJsMacbookAir on 10/10/17.
 */

@TeleOp(name="Teleop", group="Pushbot")
public class Teleop extends OpMode
{
    public DcMotor fr = null;
    public DcMotor fl = null;
    public DcMotor br = null;
    public DcMotor bl = null;
    public DcMotor llift = null;
    public DcMotor rlift = null;
    public DcMotor lslide = null;
    public DcMotor rslide = null;

    public CRServo hangpin = null;
    public CRServo intake = null;
    public Servo llock = null;
    public Servo rlock = null;
    public Servo rwrist = null;
    public Servo lwrist = null;
    
    public Servo gate = null;

    public double llockopen = 0.5;
    public double llockclosed = 0.5;
    public double rlockopen = 0.5;
    public double rlockclosed = 0.5;
    public double lwristup = 0.84;
    public double lwristdown = 0; //0.1
    public double rwristup = 0.05;
    public double rwristdown = 0.80; //0.84

    @Override
    public void init()
    {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");
        llift = hardwareMap.dcMotor.get("llift");
        rlift = hardwareMap.dcMotor.get("rlift");
        lslide = hardwareMap.dcMotor.get("lslide");
        rslide = hardwareMap.dcMotor.get("rslide");
        hangpin = hardwareMap.crservo.get("hangpin");
        intake = hardwareMap.crservo.get("intake");
        lwrist = hardwareMap.servo.get("lwrist");
        rwrist = hardwareMap.servo.get("rwrist");
        llock = hardwareMap.servo.get("llock");
        rlock = hardwareMap.servo.get("rlock");
        gate = hardwareMap.servo.get("gate");

        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        llift.setPower(0);
        rlift.setPower(0);
        lslide.setPower(0);
        rslide.setPower(0);
        hangpin.setPower(0);
        intake.setPower(0);

        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        llift.setDirection(DcMotorSimple.Direction.FORWARD);
        rlift.setDirection(DcMotorSimple.Direction.FORWARD);
        lslide.setDirection(DcMotorSimple.Direction.REVERSE);
        rslide.setDirection(DcMotorSimple.Direction.REVERSE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        llift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        llift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rlift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void init_loop()
    {
        
    }

    @Override
    public void start()
    {

    }
    public void mecanumDrive_Cartesian(double x, double y, double rotation)

    {

        double wheelSpeeds[] = new double[4];



        wheelSpeeds[0] = x + y + rotation;

        wheelSpeeds[1] = -x + y - rotation;

        wheelSpeeds[2] = -x + y + rotation;

        wheelSpeeds[3] = x + y - rotation;



        normalize(wheelSpeeds);



        fl.setPower(wheelSpeeds[0]);

        fr.setPower(wheelSpeeds[1]);

        bl.setPower(wheelSpeeds[2]);

        br.setPower(wheelSpeeds[3]);

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
    public void loop()
    {
        // drive code
        // final float Vx = -gamepad1.left_stick_x;
        // final float Vy = -gamepad1.left_stick_y;
        // final float w = gamepad1.right_stick_x;
        // fl.setPower(Vy + Vx + w);
        // fr.setPower(Vy - Vx - w);
        // bl.setPower(Vy - Vx + w);
        // br.setPower(Vy + Vx - w);
        
        // telemetry.addData("Vy: ", Vy);
        // telemetry.addData("Vx: ", Vx);
        // telemetry.addData("w: ", w);
        float leftY = -gamepad1.left_stick_y;

        float leftX = -gamepad1.left_stick_x;

        float turn = gamepad1.right_stick_x;

        

        telemetry.addData("GamePad Data: ", "G1LY: " + leftY + "G1LX: " + leftX + "G1Turn: " + turn);
        telemetry.addData("leftWrist Position, rWrist Position", lwrist.getPosition() + " and " + rwrist.getPosition());
        mecanumDrive_Cartesian(leftX, leftY, turn);
        telemetry.update();
        // left lift
        if(gamepad2.left_bumper)
        {
            llift.setPower(1);
        }
        else if(gamepad2.left_trigger > 0.2)
        {
            llift.setPower(-0.4);
        }
        else
        {
            llift.setPower(0);
        }
        
        if (gamepad2.y) {
            gate.setPosition(0);
        }
        else {
            gate.setPosition(0.5);
        }

        // right lift
        if(gamepad2.right_bumper)
        {
            rlift.setPower(1);
        }
        else if(gamepad2.right_trigger > 0.2)
        {
            rlift.setPower(-0.4);
        }
        else
        {
            rlift.setPower(0);
        }

        // intake slides
        lslide.setPower(gamepad2.right_stick_y);
        rslide.setPower(gamepad2.left_stick_y);

        // hanging lock
        if(gamepad2.b)
        {
            hangpin.setPower(1);
        }
        else
        {
            hangpin.setPower(0);
        }

        // intake
        if(gamepad2.dpad_down)
        {
            intake.setPower(0.8);
        }
        else if(gamepad2.dpad_up)
        {
            intake.setPower(-0.8);
        }
        else
        {
            intake.setPower(0);
        }

        if(gamepad2.x) // lock lift
        {
            llock.setPosition(llockclosed);
            rlock.setPosition(rlockclosed);
        }
        else if(gamepad2.a) // unlock lift
        {
            llock.setPosition(llockopen);
            rlock.setPosition(llockopen);
        }

        // wrist position
        if(gamepad2.dpad_left)
        {
            lwrist.setPosition(lwristup);
            rwrist.setPosition(rwristup);
        }
        else if(gamepad2.dpad_right)
        {
            lwrist.setPosition(lwristdown);
            rwrist.setPosition(rwristdown);
        }
    }
}