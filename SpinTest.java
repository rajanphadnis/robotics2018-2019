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
public class SpinTest extends LinearOpMode {
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
        
        while(!opModeIsActive() && !isStopRequested())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
        }
        starttime2 = System.currentTimeMillis();
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setPower(-0.5);
        br.setPower(-0.5);
        fl.setPower(0.5);
        bl.setPower(0.5);
        while(System.currentTimeMillis() - starttime2 < 1800 && opModeIsActive())
        {
            telemetry.addData("This", " is voodoo");
            telemetry.update();
            idle();
        }
        fr.setPower(0);
        br.setPower(0);
        fl.setPower(0);
        bl.setPower(0);
    }
}
