package org.mowitnow.automaticmower.application;

import org.mowitnow.automaticmower.domain.MowerTask;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IMowInputParser {
    public List<MowerTask> parseMowingTasks(String filePath) throws IOException, URISyntaxException, InvalidInputException;
}
