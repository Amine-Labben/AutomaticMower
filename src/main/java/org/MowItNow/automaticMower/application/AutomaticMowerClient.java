package org.mowitnow.automaticmower.application;

import org.mowitnow.automaticmower.domain.MowingAreaService;
import org.mowitnow.automaticmower.infrastructure.MowInputParser;

import java.util.List;

public class AutomaticMowerClient {
    private static String DEFAULT_FILE_NAME = "automatic-mowing-input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println(getAutomaticMowingResult(args.length > 0 ? args[0] : DEFAULT_FILE_NAME));
    }

    public static List<String> getAutomaticMowingResult(String fileName) throws Exception{
        IMowInputParser parser = new MowInputParser();
        MowingAreaService mowingAreaService = new MowingAreaService();
        var mowerTasks = parser.parseMowingTasks(fileName);
        mowingAreaService.mowSequentially(mowerTasks);
        return mowerTasks.stream().map(mowerTask -> mowerTask.mower().toString()).toList();
    }
}