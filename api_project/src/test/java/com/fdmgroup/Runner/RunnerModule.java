package com.fdmgroup.Runner;

import com.fdmgroup.Project.Post;

public class RunnerModule {

    public static void main(String[] args) {

        // Q1
        // Post.performGetAllFood();

        // Q2
        // Post.performAdd("Burgers", 10.55);

        // Q3
        // Post.performUpdateOnPrice(5, 5.55);

        // Q4
        // Post.performGetAllComments();
        // Post.performAddCommentWithFoodId(1, "How are you", 1);

        //  Q5
        // Post.performCommentDelete(5);

        // Q6
        // Post.performGetCommentwithUserIdFoodId(1, 1);

        // Q7
        // Post.performGetAllManagers();
        // Post.performUpdateStaffUnderAManager(2, 0, "Kevin Ortiz", 200);
        // Post.performUpdateStaffUnderAManager(2, 1, "Kevin Ortiz", 200);
        // Post.performUpdateStaffUnderAManager(2, 2, "Kevin Ortiz", 200);
        

        // Q8
        Post.performDeleteStaffUnderAManager(2, 0);
        Post.performDeleteStaffUnderAManager(2, 1);

    }

}
