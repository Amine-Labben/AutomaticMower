package org.mowitnow.automaticmower.infrastructure;

import org.mowitnow.automaticmower.application.IMowInputParser;
import org.mowitnow.automaticmower.application.InvalidInputException;
import org.mowitnow.automaticmower.domain.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class MowInputParser implements IMowInputParser {
    private static final String SEPARATOR = " ";

    @Override
    public List<MowerTask> parseMowingTasks(String filePath) throws IOException, URISyntaxException, InvalidInputException {
        var fileLines = getFileLines(filePath);
        validateLinesSize(fileLines);

        Area area = parseArea(fileLines.getFirst());

        return IntStream.range(1, fileLines.size())
                .filter(index -> index % 2 == 1)
                .mapToObj(index -> new MowerTask(
                        parseMower(fileLines.get(index), area),
                        parseInstructions(fileLines.get(index+1))
                ))
                .toList();
    }

    private List<String> getFileLines(String filePath) throws IOException, URISyntaxException {
        var path = Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource(filePath)).toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    private void validateLinesSize(List<String> fileLines) throws InvalidInputException{
        if (fileLines.isEmpty()) {
            throw new InvalidInputException("Input file must not be empty");
        }

        if (fileLines.size() == 1) {
            throw new InvalidInputException("Input file must have at least one mower");
        }

        if (fileLines.size() % 2 == 0 ) {
            throw new InvalidInputException("Every mower task must have be defined by exactly two lines");
        }
    }

    private static Area parseArea(String line) throws InvalidInputException {
        String[] dimensions = line.split(SEPARATOR);
        if (dimensions.length != 2) {
            throw new InvalidInputException("Area dimensions should contain exactly two numbers");
        }

        try {
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            if (width < 0 || height < 0) {
                throw new InvalidInputException("Area dimensions must be positive numbers");
            }

            return new Area(width, height);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Area dimensions must be valid numbers");
        }
    }

    private static Mower parseMower(String line, Area area) throws InvalidInputException {
        String[] parts = line.split(SEPARATOR);
        if (parts.length != 3) {
            throw new InvalidInputException("Mower initial position should have two numbers and an orientation");
        }

        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            String orientation = parts[2];

            if (x < 0 || x > area.width() || y < 0 || y > area.height()) {
                throw new InvalidInputException("Mower position is out of area");
            }

            return new Mower(area, new Position(x, y), Orientation.getByCode(orientation));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Mower position must contain valid integers.");
        }
    }

    private static List<Instruction> parseInstructions(String line) throws InvalidInputException {
        List<Instruction> instructions = new ArrayList<>();
        for (char c : line.toCharArray()) {
            try{
                Instruction instruction = Instruction.getByCode(String.valueOf(c));
                instructions.add(instruction);
            } catch(NoSuchElementException e) {
                throw new InvalidInputException("Invalid instruction: " + c);
            }
        }
        return instructions;
    }
}
