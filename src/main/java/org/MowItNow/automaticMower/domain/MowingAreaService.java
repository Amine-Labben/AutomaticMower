package org.mowitnow.automaticmower.domain;

import java.util.List;

public class MowingAreaService {
    public void mowSequentially(List<MowerTask> mowerTasks) {
        mowerTasks.forEach(MowerTask::executeAutomaticTask);
    }
}
