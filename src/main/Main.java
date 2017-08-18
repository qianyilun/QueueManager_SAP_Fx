

import javafx.stage.Stage;
import view.StartPage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yilunq on 12/08/17.
 *
 * This is the Main function to execute
 */
public class Main {
    private static Timer timer;
    private static TimerTask task;

    public static void main(String[] args) {

        // ---------------------------------------------------
        // NEEDS TO BE MODIFY TO BE "read from File"
        // OR GET queueID AUTOMATICALLY
        String queueID = "0090FAE68C221ED78F84917FE2FBA0CB";
        //----------------------------------------------------

        start(queueID);
    }

    public static void start(String queueID) {
        // launch the Welcome --> start page
        StartPage sp = new StartPage();
//        Thread startPage = new Thread(sp);
//        startPage.start();

        sp.main(new String[]{"2"});


        try {
            Thread.sleep(5000);
//            startPage.interrupted();
            StartPage sp2 = new StartPage();
            Thread startPage2 = new Thread(sp2);
            startPage2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        startPage.start();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {

                System.out.println("start");



//                QueueManager qm = new QueueManager(queueID);
//                qm.run();
            }
        };

        // note, period should be 15 mins
        timer.schedule(task, 0, 5000);
    }
}
