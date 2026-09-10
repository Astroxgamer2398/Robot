package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.*;
import com.pedropathing.paths.PathChain;

public class paths {
    //have to initialize all the PathChains here
    public static PathChain path1, path2;

    //used on line 77 in the auto
    public static void myPath(Follower follower) {
        //use pedropathing visualizer for the stuff after path1=
        //feel free to change this, it's just there to show you what it looks like
        path1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(28.2, 140),
                                new Pose(75.000, 44.000)
                        )
                )
                //this line is to set rotation of the robot from (start, finish)
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(90))
                .build();
        //path2 = whatever you want!!
        //add one more path here

        path2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(48.2, 240),
                                new Pose(30.000, 54.000)
                        )
                )
                //this line is to set rotation of the robot from (start, finish)
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(90))
                .build();


    }
}

