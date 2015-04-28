# AI for a wall--following Lego Mindstorms robot

[9797 LEGO® Mindstorms Education
Base set][9797] with [9841 NXT Intelligent Brick][9841] running [leJOS
0.9.1beta-3][lejos]. 

## PHOTO: LEGO Robot

![Image](/blob/master/report/robot.jpg?raw=true)

Front and Side-view of Lego Mindstorms NXJ robot showing ultrasonic sensor and
bump sensors]

## Finite State Machine diagram

![Image](/blob/master/report/fsm.png?raw=true)

Flow diagram of robot finite state machine

## CODE: Core wall-following logic

````Java
private static void mainLoop() {
    initialiseMotorsAndSensors();
    while(true) {
        driveForward();
        steerWhenWallSensed();
        backOffWhenBumpersPressed();
    }
}

// (NON-BLOCKING)
private static void steerWhenWallSensed() { 
    int sonarDistance = sonar.getDistance();
    if (sonarDistance < TOO_CLOSE) {
        steer(LARGE_ROTATION);
    } else if (sonarDistance < CLOSE) {
        steer(SMALL_ROTATION);
    } else if (sonarDistance > TOO_FAR) {
        steer(-LARGE_ROTATION);
    } else if (sonarDistance > FAR) {
        steer(-SMALL_ROTATION);
    }
}

// (BLOCKING)
private static void backOffWhenBumpersPressed() {
    if (leftBumpSensor.isPressed() || 
            rightBumpSensor.isPressed()) {
        backOff();
        turnRight();
    }
}
````

[9797]: http://education.lego.com/en-us/lego-education-product-database/mindstorms/9797-lego-mindstorms-education-base-set/

[9841]: http://shop.lego.com/en-US/NXT-Intelligent-Brick-9841

[lejos]: http://sourceforge.net/projects/lejos/files/lejos-NXJ/0.9.1beta/

[9846]: http://shop.lego.com/en-US/Ultrasonic-Sensor-9846

[9843]: http://shop.lego.com/en-US/Touch-Sensor-9843

[9842]: http://shop.lego.com/en-US/Interactive-Servo-Motor-9842
