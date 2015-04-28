package com.crabdance.mouth;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

import java.util.ArrayList;

public class Robot {
	
    private static final int FORWARD_TRAVEL_DISTANCE = 1000;
    private static final int BACKOFF_TRAVEL_DISTANCE = -7;
    private static final int ROTATION_ANGLE = 90;
    private static final int ROBOT_SPEED = 15;
    private static final boolean IMMEDIATE_RETURN = true;
    private static final boolean NO_IMMEDIATE_RETURN = false;
    private static final float WHEEL_DIMENSION = 5.6f;
    private static final int SMALL_ROTATION = 20;
    private static final int LARGE_ROTATION = 45;
    private static DifferentialPilot wheels = null;
    private static TouchSensor rightBumpSensor = null;
    private static TouchSensor leftBumpSensor = null;
    private static UltrasonicSensor sonar = null;
    private static final int FAR = 21;
    private static final int TOO_FAR = 26;
    private static final int CLOSE = 17;
    private static final int TOO_CLOSE = 13;
    private static final int SLEEP_TIME = 65;

    private static ArrayList<Float> probabilities = null;
    private static ArrayList<String> world = null;
    private static ArrayList<String> measurements = null;
    private static ArrayList<Integer> motions = null;
    private static final Float pHit = 0.6f;
    private static final Float pMiss = 0.2f;
    private static final Float pExact = 0.8f;
    private static final Float pOvershoot = 0.1f;
    private static final Float pUndershoot = 0.1f;
    

    public static void main(String[] args) {
        mainLoop();
    }

    private static void mainLoop() {
        initialiseMotorsAndSensors();
        //computePosterior(probabilities, measurements);
        while(true) {
            goForward();
            backOffWhenBumpersPressed();
            steerWhenWallSensed();
        }
    }

    static void backOffWhenBumpersPressed() {
        if (leftBumpSensor.isPressed() || rightBumpSensor.isPressed()) {
            backOff();
            turnRight();
        }
    }
    
    private static void steerWhenWallSensed() {
        int sonarDistance = sonar.getDistance();
        System.out.print("S:" + sonarDistance + " ");
        if (sonarDistance < TOO_CLOSE) {
            System.out.print("2Cl");
            steer(LARGE_ROTATION);
        } else if (sonarDistance < CLOSE) {
            System.out.print("Cl");
            steer(SMALL_ROTATION);
        } else if (sonarDistance > TOO_FAR) {
            System.out.print("2Fr");
            steer(-LARGE_ROTATION);
        } else if (sonarDistance > FAR) {
            System.out.print("Fr");
            steer(-SMALL_ROTATION);
        }
    }
    
    private static void steer(int rotationAmount) {
        wheels.steer(rotationAmount);
        sleep();
    }
    
    private static void sleep() {
        sleep(SLEEP_TIME);
    }

    private static void sleep(int seconds) {
        try { Thread.sleep(seconds); } catch(Exception e) {}
    }

    private static void initialiseMotorsAndSensors() {
        wheels = new DifferentialPilot(WHEEL_DIMENSION, WHEEL_DIMENSION, Motor.C, Motor.A);
        wheels.setTravelSpeed(ROBOT_SPEED);
        rightBumpSensor = new TouchSensor(SensorPort.S1);
        leftBumpSensor = new TouchSensor(SensorPort.S3);
        sonar = new UltrasonicSensor(SensorPort.S4);
    }

    private static void goForward() {
        System.out.print("F;");
        wheels.travel(FORWARD_TRAVEL_DISTANCE, IMMEDIATE_RETURN);
    }

    private static void backOff() {
        System.out.print("B;");
        wheels.stop();
        wheels.travel(BACKOFF_TRAVEL_DISTANCE, NO_IMMEDIATE_RETURN);
    }

    private static void turnRight() {
        System.out.print("L;");
        wheels.rotate(ROTATION_ANGLE, NO_IMMEDIATE_RETURN);
    }

    private static ArrayList<Float> computePosterior(ArrayList<Float> probabilities, ArrayList<String> measurements) {
        ArrayList<Float> newProbabilities = cloneList(probabilities);
        for (int i = 0; i < measurements.size(); i++) {
            newProbabilities = move(sense(newProbabilities, measurements.get(i)), motions.get(i));
        }
        return newProbabilities;
    }

    private static ArrayList<Float> sense(ArrayList<Float> probabilities, String seen) {
        ArrayList<Float> newProbabilities = new ArrayList<Float>();
        for (int i = 0; i < probabilities.size(); i++) {
            int hit = (seen == world.get(i)) ? 1 : 0;
            newProbabilities.add(probabilities.get(i) * (hit * pHit + (1-hit) * pMiss));
        }
        return normalise(newProbabilities);
    }

    private static ArrayList<Float> move(ArrayList<Float> probabilities, int distance) {
        ArrayList<Float> newProbabilities = new ArrayList<Float>();
        for (int i = 0; i < probabilities.size(); i++) {
            Float exact = pExact * probabilities.get((i-distance) % probabilities.size());
            Float overshoot = pOvershoot * probabilities.get((i-distance-1) % probabilities.size());
            Float undershoot = pUndershoot * probabilities.get((i-distance+1) % probabilities.size());
            newProbabilities.add(exact + overshoot + undershoot);
        }
        return newProbabilities;
    }

    private static ArrayList<Float> normalise(ArrayList<Float> list) {
        Float s = sum(list);
        ArrayList<Float> newProbabilities = new ArrayList<Float>();
        for (int i = 0; i < newProbabilities.size(); i++) {
            newProbabilities.add(list.get(i)/s);
        }
        return newProbabilities;
    }

    private static Float sum(ArrayList<Float> list) {
        Float sum= 0f; 
        for (Float i:list)
            sum = sum + i;
        return sum;
    }

    private static ArrayList<Float> cloneList(ArrayList<Float> list) {
        ArrayList<Float> clone = new ArrayList<Float>(list.size());
        for(Float item: list) {
        	clone.add(item);
        }
        return clone;
    }

}
