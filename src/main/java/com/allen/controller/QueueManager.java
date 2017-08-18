package com.allen.controller;

import com.allen.model.*;
import com.google.gson.Gson;

import java.util.Arrays;

/**
 * Created by yilunq on 12/08/17.
 *
 * This class is a manager to organize and manage the interactive of all classes
 */
public class QueueManager {
    private String queueID;
    private DATA[] incidentsInfo;
    private ConnectionConfiguration cc;
    EmergeQueueList emergeQueueList;


    public QueueManager(String queueID) {
        this.queueID = queueID;
    }

    public void run() {
        emergeQueueList = new EmergeQueueList();
        String jsonSourceCode;
        /* ///////////////////////////////////////////////////////////////////
        // --------- Open Connection -----------
        cc = new ConnectionConfiguration(queueID);
        cc.connect();

        // --------- Get 'incidents' in JsonObject via BCP -------
        jsonSourceCode = cc.getJsonSourceCode();
        incidentsInfo = parseJson(jsonSourceCode).getDATA();

        */ ////////////////////////////////////////////////////////////////////

    // ***** below TEST ONLY ************** PREPARE TO DELETE ***********************
                                                                            //**
        // --------- Get 'incidents' in Test raw Json String ---------
        jsonSourceCode = TestJSON.pageSource;
        incidentsInfo = parseJson(TestJSON.pageSource).getDATA();
    // ***** above TEST ONLY ************** PREPARE TO DELETE ***********************


        // --------- Sort 'incidents' by IRT_EXPIRE date
        Arrays.sort(incidentsInfo);

        // --------- Save String to JSON file as the backup ----------
        JsonSaver.save(JsonStringFormatter.prettyJsonFormat(jsonSourceCode));

        // --------- Manipulate the incident_info ---------
        for (DATA data : incidentsInfo) {
            IRT irt = new IRT(data.getIRT_EXPIRY());
            System.out.println(irt);

            if (data.hasValidIRT()) {
                if (irt.isLessThan(30)) {
                    emergeQueueList.addIncidentToArray(data);
                    System.out.println(irt);
                } else if (irt.isLessThan(45)) {
                    // launch the view
                }
            }
        }

        if (emergeQueueList.getLst() != null) {
            // launch the view
        }
//        System.out.println(emergeQueueList.getLst().get(0).getIRT_EXPIRY());
    }

    private MainParser parseJson(String jsonString) {
        Gson gson = new Gson();
        MainParser mainParser = gson.fromJson(jsonString, MainParser.class);
        return mainParser;
    }
}
