package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@Autonomous
public class PlzWork extends LinearOpMode {
    public PixyLego Pixy = new PixyLego();
    public DcMotor fr = null;
    public DcMotor fl = null;
    public DcMotor br = null;
    public DcMotor bl = null;
    public DcMotor llift = null;
    public DcMotor rlift = null;
    public DcMotor lslide = null;
    public DcMotor rslide = null;
    
    public long starttime2 = 0;

    public CRServo hangpin = null;
    public CRServo intake = null;
    public Servo llock = null;
    public Servo rlock = null;
    public Servo rwrist = null;
    public Servo lwrist = null;
    
    public Servo gate = null;

    public double llockopen = 0.25;
    public double llockclosed = 1;
    public double rlockopen = 0.9;
    public double rlockclosed = 0.1;
    public double lwristup = 0.84;
    public double lwristdown = 0;
    public double rwristup = 0.05;
    public double rwristdown = 0.80;
    public long starttime = 0;

    public double speedOne = 0.5;

    public double flPosInt = 1;
    
    public double minPixyVal = 122;
    public double maxPixyVal = 132;
    
    public double turnSpeed = 0.25;
    
    public boolean turnedRight = false;
    public boolean turnedLeft = false;
    
    public Orientation orientation;
    
    public Orientation angleMissed;
    
    public void forwards(int ticks, double speed) {
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setTargetPosition(ticks);
        fr.setTargetPosition(ticks);
        bl.setTargetPosition(ticks);
        br.setTargetPosition(ticks);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void backwards(int ticks, double speed){
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setTargetPosition(-ticks);
        fr.setTargetPosition(-ticks);
        bl.setTargetPosition(-ticks);
        br.setTargetPosition(-ticks);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(-speed);
        fr.setPower(-speed);
        bl.setPower(-speed);
        br.setPower(-speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void left(int ticks, double speed) {
        int flPos = fl.getCurrentPosition() + ticks;
        int frPos = fr.getCurrentPosition() - ticks;
        int blPos = bl.getCurrentPosition() - ticks;
        int brPos = br.getCurrentPosition() + ticks;
        fr.setTargetPosition(flPos);
        fl.setTargetPosition(frPos);
        br.setTargetPosition(blPos);
        bl.setTargetPosition(brPos);
        // Turn On RUN_TO_POSITION
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setPower(speed);
        fl.setPower(speed);
        br.setPower(speed);
        bl.setPower(speed);
        while (opModeIsActive() && (fr.isBusy() && fl.isBusy() && br.isBusy() && bl.isBusy())) {
            // telemetry.addData("Path1",  "Running to position");
            // telemetry.update();
            idle();
        }
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        // Turn off RUN_TO_POSITION
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void right(int ticks, double speed) {
        int flPos = fl.getCurrentPosition() - ticks;
        int frPos = fr.getCurrentPosition() + ticks;
        int blPos = bl.getCurrentPosition() + ticks;
        int brPos = br.getCurrentPosition() - ticks;
        fr.setTargetPosition(flPos);
        fl.setTargetPosition(frPos);
        br.setTargetPosition(blPos);
        bl.setTargetPosition(brPos);
        // Turn On RUN_TO_POSITION
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setPower(speed);
        fl.setPower(speed);
        br.setPower(speed);
        bl.setPower(speed);
        while (opModeIsActive() && (fr.isBusy() && fl.isBusy() && br.isBusy() && bl.isBusy())) {
            // telemetry.addData("Path1",  "Running to position");
            // telemetry.update();
            idle();
        }
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        // Turn off RUN_TO_POSITION
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void turn(int ticks, double speed, boolean left){
        if (left) {
            int flPos = fl.getCurrentPosition() - ticks;
            int frPos = fr.getCurrentPosition() + ticks;
            int blPos = bl.getCurrentPosition() - ticks;
            int brPos = br.getCurrentPosition() + ticks;
            fr.setTargetPosition(flPos);
            fl.setTargetPosition(frPos);
            br.setTargetPosition(blPos);
            bl.setTargetPosition(brPos);
            // Turn On RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setPower(speed);
            fl.setPower(-speed);
            br.setPower(speed);
            bl.setPower(-speed);
            while (opModeIsActive() && (fr.isBusy() && fl.isBusy() && br.isBusy() && bl.isBusy())) {
                // telemetry.addData("Path1",  "Running to position");
                // telemetry.update();
                idle();
            }
            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
            // Turn off RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else {
            int flPos = fl.getCurrentPosition() + ticks;
            int frPos = fr.getCurrentPosition() - ticks;
            int blPos = bl.getCurrentPosition() + ticks;
            int brPos = br.getCurrentPosition() - ticks;
            fr.setTargetPosition(flPos);
            fl.setTargetPosition(frPos);
            br.setTargetPosition(blPos);
            bl.setTargetPosition(brPos);
            // Turn On RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setPower(-speed);
            fl.setPower(speed);
            br.setPower(-speed);
            bl.setPower(speed);
            while (opModeIsActive() && (fr.isBusy() && fl.isBusy() && br.isBusy() && bl.isBusy())) {
                // telemetry.addData("Path1",  "Running to position");
                // telemetry.update();
                idle();
            }
            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
            // Turn off RUN_TO_POSITION
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    
    public void spinleft(int ticks, double speed) {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(-ticks);
        fr.setTargetPosition(ticks);
        bl.setTargetPosition(-ticks);
        br.setTargetPosition(ticks);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(-speed);
        fr.setPower(speed);
        bl.setPower(-speed);
        br.setPower(speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void spinleftback(int ticks, double speed) {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(-ticks);
        fr.setTargetPosition(ticks);
        bl.setTargetPosition(-ticks);
        br.setTargetPosition(ticks);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(-speed);
        fr.setPower(speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void spinright(int ticks, double speed) {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(ticks);
        fr.setTargetPosition(-ticks);
        bl.setTargetPosition(ticks);
        br.setTargetPosition(-ticks);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(speed);
        fr.setPower(-speed);
        bl.setPower(speed);
        br.setPower(-speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void slideright(int ticks, double speed) {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(ticks);
        fr.setTargetPosition(-ticks);
        bl.setTargetPosition(-ticks);
        br.setTargetPosition(ticks);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(speed);
        fr.setPower(-speed);
        bl.setPower(-speed);
        br.setPower(speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void slideleft(int ticks, double speed) {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setTargetPosition(-ticks);
        fr.setTargetPosition(ticks);
        bl.setTargetPosition(ticks);
        br.setTargetPosition(-ticks);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // telemetry.addData("fr: ", fr.getCurrentPosition());
        // telemetry.update();
        fl.setPower(-speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(-speed);
        while(fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy() && opModeIsActive())
        {
            idle();
            // telemetry.addData("fr: ", fr.getCurrentPosition());
            // telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    @Override
    public void runOpMode() {
        Pixy.init(hardwareMap, "Pixy");
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
        llift.setDirection(DcMotorSimple.Direction.REVERSE);
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

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        llift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        llift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        //waitForStart();
        rlock.setPosition(rlockopen);
        llock.setPosition(llockopen);
        
        while(!opModeIsActive() && !isStopRequested())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
        }
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        Orientation angles2 = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("Data", "First Angle: " + angles2.firstAngle);
        telemetry.update();
        //backwards(100, 1);
        llift.setPower(-1);
        rlift.setPower(-1);
        rlock.setPosition(rlockclosed);
        llock.setPosition(llockclosed);
        llift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        /*try
        {
            //Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
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
        }*/
        starttime2 = System.currentTimeMillis();
        while(System.currentTimeMillis() - starttime2 < 1000 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        llift.setPower(0.1);
        rlift.setPower(0.1);
        //fl.setPower(0.25);
        //fr.setPower(0.25);
        /*try
        {
            //Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
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
        }*/
        starttime2 = System.currentTimeMillis();
        while(System.currentTimeMillis() - starttime2 < 2000 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        llift.setPower(0);
        rlift.setPower(0);
        /*try
        {
            //Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
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
        }*/
        starttime2 = System.currentTimeMillis();
        while(System.currentTimeMillis() - starttime2 < 2000 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        llift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinleftback(350, 1);
        hangpin.setPower(1);
        /*try
        {
            //Thread.sleep(250);
        }
        catch(InterruptedException e)
        {
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
        }*/
        starttime2 = System.currentTimeMillis();
        while(System.currentTimeMillis() - starttime2 < 400 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        slideright(200, 1);
        //spinright(1100, 0.5); // was right
        starttime2 = System.currentTimeMillis();
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setPower(-0.5);
        br.setPower(-0.5);
        fl.setPower(0.5);
        bl.setPower(0.5);
        while(System.currentTimeMillis() - starttime2 < 2000 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        fr.setPower(0);
        br.setPower(0);
        fl.setPower(0);
        bl.setPower(0);
        hangpin.setPower(0);
        //spinright(1000, 0.5); // was right
        starttime2 = System.currentTimeMillis();
        fr.setPower(0.4);
        fl.setPower(0.4);
        br.setPower(0.4);
        bl.setPower(0.4);
        while(opModeIsActive() && System.currentTimeMillis() - starttime2 < 2000) // 
        {
            idle();
        }
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        backwards(125, 1);
        ///////////////////////////////////////////////////////////////////////////
        long starttime = System.currentTimeMillis();
        boolean found = true;
        while(!(Pixy.getX(1) > minPixyVal && Pixy.getX(1) < maxPixyVal && Pixy.getY(1) > 110) && opModeIsActive())
        {
            
            if (Pixy.getX(1) > 0 && Pixy.getX(1) < minPixyVal && Pixy.getY(1) > 110) {
                fr.setPower(turnSpeed);
                fl.setPower(-turnSpeed);
                br.setPower(turnSpeed);
                bl.setPower(-turnSpeed);
            }
            else if (Pixy.getX(1) > maxPixyVal && Pixy.getX(1) < 255 && Pixy.getY(1) > 110) {
                fr.setPower(-turnSpeed);
                fl.setPower(turnSpeed);
                br.setPower(-turnSpeed);
                bl.setPower(turnSpeed);
            }
            else if(System.currentTimeMillis() - starttime < 800)
            {
                fr.setPower(-0.3);
                fl.setPower(0.3);
                br.setPower(-0.3);
                bl.setPower(0.3);
            }
            else if(System.currentTimeMillis() - starttime > 1300)
            {
                fr.setPower(0.3);
                fl.setPower(-0.3);
                br.setPower(0.3);
                bl.setPower(-0.3);
            }
            else
            {
                fr.setPower(0);
                fl.setPower(0);
                br.setPower(0);
                bl.setPower(0);
            }
            if(System.currentTimeMillis() - starttime > 3200)
            {
                found = false;
                break;
            }
        }
        if(found)
        {
            backwards(1000, 0.2);
            telemetry.addData("Working", " good");
            telemetry.update();
        }
        else
        {
            spinright(600, 0.5);
            backwards(1000, 0.2);
        }
    }
}
