package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.io.File;
import com.qualcomm.hardware.lynx.LynxNackException;
import com.qualcomm.hardware.lynx.LynxUsbDevice;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.commands.standard.LynxSetModuleLEDColorCommand;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.EventLoopManager;
import com.qualcomm.ftccommon.SoundPlayer;

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

    public double llockopen = 0.2;
    public double llockclosed = 1;
    public double rlockopen = 0.9;
    public double rlockclosed = 0.1;
    public double lwristup = 1;
    // dont touch
    public double lwristdown = 0; //0.1
    public double rwristup = 1;
    // ok, u good
    public double rwristdown = 0; //0.84
    public double slowDown = 0.5;
    private String soundPath = "/FIRST/blocks";
    private File goldFile   = new File("/sdcard" + soundPath + "/1.mp3");
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
        // SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldFile);
        
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
        // 
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
        float leftY = -gamepad1.left_stick_y;

        float leftX = -gamepad1.left_stick_x;

        float turn = gamepad1.right_stick_x;
        
        telemetry.addData("GamePad Data: ", "G1LY: " + leftY + "G1LX: " + leftX + "G1Turn: " + turn);
        telemetry.addData("leftWrist Position, rWrist Position", lwrist.getPosition() + " and " + rwrist.getPosition());
        if(gamepad1.right_bumper){
            
            mecanumDrive_Cartesian(slowDown * leftX, slowDown * leftY,slowDown *  turn);
        }
        else {
            mecanumDrive_Cartesian(leftX, leftY, turn);
        }
        
        telemetry.update();
        // left lift
        if(gamepad2.left_bumper)
        {
            llift.setPower(-1);
        }
        else if(gamepad2.left_trigger > 0)
        {
            llift.setPower(1);
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
        else if(gamepad2.right_trigger > 0)
        {
            rlift.setPower(-1);
        }
        else
        {
            rlift.setPower(0);
        }

        // intake slides
        rslide.setPower(gamepad2.right_stick_y);
        lslide.setPower(gamepad2.left_stick_y);

        // hanging lock
        if(gamepad2.b)
        {
            hangpin.setPower(1);
        }
        else
        {
            hangpin.setPower(0);
        }
        // hangpin.setPower(0);
        // intake
        if(gamepad2.dpad_up)
        {
            intake.setPower(0.8);
        }
        else if(gamepad2.dpad_down)
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
            rlock.setPosition(rlockopen);
        }

        // wrist position Right works fine
        // if(gamepad2.dpad_right)
        // {
        //     lwrist.setPosition(lwristdown);
        //     rwrist.setPosition(rwristup);
        // }
        // //fix this, please
        // else if(gamepad2.dpad_left)
        // {
        //     // lwrist.setPosition(lwristup);
        //     rwrist.setPosition(rwristdown);
        // }
        // Right: flips in. Left: flips out
        if(gamepad2.dpad_right) {
            lwrist.setPosition(0);
            
        }
        else if(gamepad2.dpad_left) {
            lwrist.setPosition(1);
            
        }
    }
}