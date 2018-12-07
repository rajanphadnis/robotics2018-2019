package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

public class PixyLego
{
    HardwareMap hwMap = null;
    I2cDeviceSynch PixyCam = null;

    /* Constructor */
    public PixyLego(){
    }

    // initialize the hardware
    public void init(HardwareMap ahwMap, String name) {
        hwMap = ahwMap;
        PixyCam = hwMap.i2cDeviceSynch.get(name);
        PixyCam.engage();
    }

    // get the number of blobs of a given signature (1-7)
    public int getNumBlobs(int signature)
    {
        int register = 0;
        if(signature == 1)
        {
            register = 0x51;
        }
        else if(signature == 2)
        {
            register = 0x52;
        }
        else if(signature == 3)
        {
            register = 0x53;
        }
        else if(signature == 4)
        {
            register = 0x54;
        }
        else if(signature == 5)
        {
            register = 0x55;
        }
        else if(signature == 6)
        {
            register = 0x56;
        }
        else if(signature == 7)
        {
            register = 0x57;
        }
        else
        {
            throw new java.lang.RuntimeException("invalid signature");
        }
        byte[] sig = PixyCam.read(register,5);
        int sigWidth = 0xff&sig[0];
        return sigWidth;
    }

    // get the x coordinate of the largest blob of a given signature (1-7)
    public int getX(int signature)
    {
        int register = 0;
        if(signature == 1)
        {
            register = 0x51;
        }
        else if(signature == 2)
        {
            register = 0x52;
        }
        else if(signature == 3)
        {
            register = 0x53;
        }
        else if(signature == 4)
        {
            register = 0x54;
        }
        else if(signature == 5)
        {
            register = 0x55;
        }
        else if(signature == 6)
        {
            register = 0x56;
        }
        else if(signature == 7)
        {
            register = 0x57;
        }
        else
        {
            throw new java.lang.RuntimeException("invalid signature");
        }
        byte[] sig = PixyCam.read(register,5);
        int sigX = 0xff&sig[1];
        return sigX;
    }

    // get the y coordinate of the largest blob of a given signature (1-7)
    public int getY(int signature)
    {
        int register = 0;
        if(signature == 1)
        {
            register = 0x51;
        }
        else if(signature == 2)
        {
            register = 0x52;
        }
        else if(signature == 3)
        {
            register = 0x53;
        }
        else if(signature == 4)
        {
            register = 0x54;
        }
        else if(signature == 5)
        {
            register = 0x55;
        }
        else if(signature == 6)
        {
            register = 0x56;
        }
        else if(signature == 7)
        {
            register = 0x57;
        }
        else
        {
            throw new java.lang.RuntimeException("invalid signature");
        }
        byte[] sig = PixyCam.read(register,5);
        int sigY = 0xff&sig[2];
        return sigY;
    }

    // get the width coordinate of the largest blob of a given signature (1-7)
    public int getWidth(int signature)
    {
        int register = 0;
        if(signature == 1)
        {
            register = 0x51;
        }
        else if(signature == 2)
        {
            register = 0x52;
        }
        else if(signature == 3)
        {
            register = 0x53;
        }
        else if(signature == 4)
        {
            register = 0x54;
        }
        else if(signature == 5)
        {
            register = 0x55;
        }
        else if(signature == 6)
        {
            register = 0x56;
        }
        else if(signature == 7)
        {
            register = 0x57;
        }
        else
        {
            throw new java.lang.RuntimeException("invalid signature");
        }
        byte[] sig = PixyCam.read(register,5);
        int sigWidth = 0xff&sig[3];
        return sigWidth;
    }

    // get the height coordinate of the largest blob of a given signature (1-7)
    public int getHeight(int signature)
    {
        int register = 0;
        if(signature == 1)
        {
            register = 0x51;
        }
        else if(signature == 2)
        {
            register = 0x52;
        }
        else if(signature == 3)
        {
            register = 0x53;
        }
        else if(signature == 4)
        {
            register = 0x54;
        }
        else if(signature == 5)
        {
            register = 0x55;
        }
        else if(signature == 6)
        {
            register = 0x56;
        }
        else if(signature == 7)
        {
            register = 0x57;
        }
        else
        {
            throw new java.lang.RuntimeException("invalid signature");
        }
        byte[] sig = PixyCam.read(register,5);
        int sigWidth = 0xff&sig[4];
        return sigWidth;
    }

    public int getLargestBlob()
    {
        byte[] gen = PixyCam.read(0x50, 6);
        int color = gen[0] + gen[1];
        return color;
    }
}