package org.firstinspires.ftc.teamcode.pedroPathing; // make sure this aligns with class location

// import static org.firstinspires.ftc.teamcode.Paths.paths.myPath;
// import static org.firstinspires.ftc.teamcode.Paths.paths.*;

import static org.firstinspires.ftc.teamcode.pedroPathing.Robot.intake;
import static org.firstinspires.ftc.teamcode.pedroPathing.paths.myPath;
import static org.firstinspires.ftc.teamcode.pedroPathing.paths.*;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;

// FTC Imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// import org.firstinspires.ftc.teamcode.Mechanics.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

// makes your opmode an autonomous
@Configurable
@Autonomous(name = "pedroAuto", group = "Blue auto")
public class Auto extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    //pose / position the robot will start at
    private final Pose startPose = new Pose(60, 9, Math.PI/2);
    //this method is where you want to add code for mechanisms
    public void autonomousPathUpdate() throws InterruptedException {
        //this switch case is basically a huge if statement
        switch (pathState) {
            case 1:
                follower.followPath(path1, true); // replace the path1 with the name of your path
                setPathState(2); // this method defined below resets the path timer and sets the path state
                break;
            case 2:
                follower.followPath(path2, true);
                intake.setPower(1);
                //makes sure the robot is done moving to a new pose
                //this is where you'll write code you want to run while the robot is going through path1 for example:
                //motor.setPower(1); // turns on whatever motor you have, motor must be initialized!! use the ones in robot class.
                if (!follower.isBusy()) {
                    //wait three seconds before continuing to the next path, not necessary for just path following
                    if (pathTimer.getElapsedTimeSeconds() > 3) {
                        //this is where you'll write the code for when the robot is done following the path for example:
                        //motor.setPower(0); //this would js turn off whatever motor you have
                        setPathState(3);
                    }
                }
                break;
        }
        //does the same thing as
        //if(pathstate==1){
        // follower.followPath(path1, true);
        // setPathState(2);
        //if(pathstate==2){
        // if(!follower.isBusy()) { etc.
    }
    public void setPathState(int pState){
        pathState=pState;
        pathTimer.resetTimer();
    }
    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
    @Override
    public void loop() {
        // These loop the movements of the robot, these must be called continuously in order to work
        follower.update();
        try {
            autonomousPathUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /** This method is called once at the init of the OpMode. **/
    @Override
    public void init() {
        Robot.init(hardwareMap);
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        //basically initializes the path you're using
        myPath(follower);
        follower.setStartingPose(startPose);
    }

    /** This method is called continuously after Init while waiting for "play". **/
    @Override
    public void init_loop() {
    }

    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        //sets the timer to zero when it starts
        opmodeTimer.resetTimer();
        //sets the initial path state to 1, good to change this if you just want to test one part of the path
        setPathState(1);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {

    }

}
