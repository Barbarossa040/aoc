package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Calculator {
	private static final int DIVISION = 3;
	private static final int SUBTRACTION = 2;
	private List<Integer> moduleWeights = new ArrayList<>();
	private Map<Integer, Integer> part1FuelMap = new HashMap<>();
	private int part1FuelCounter;
	private int part2FuelCounter;

	void calculateFuelRequirements(boolean bothParts) {
		if (loadData()) {
			calculateIndividualModules();
			if (bothParts) {
				calculateFuelWeight();
			}
		} else {
			System.err.println("HELP ER GAAT IETS FOUT, OH NO!");
		}

	}

	private void calculateFuelWeight() {
		part1FuelMap.values().forEach(this::calculateAdditionalFuel);
	}

	private void calculateAdditionalFuel(Integer integer) {
		int startingFuel = integer;
		int finalFuel = startingFuel;
		Integer result = doOperations(startingFuel);
		while (result > 0) {
			finalFuel += result;
			result = doOperations(result);
		}
		part2FuelCounter += finalFuel;
	}

	private void calculateIndividualModules() {
		moduleWeights.forEach(this::calculate);
	}

	private void calculate(Integer integer) {
		Integer result = doOperations(integer);
		part1FuelMap.put(integer, result);
		part1FuelCounter += result;
	}

	private Integer doOperations(Integer integer) {
		int divided = integer / DIVISION;
		return divided - SUBTRACTION;
	}

	private boolean loadData() {
		try (InputStreamReader isr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("day1\\input.txt"));
		     BufferedReader reader = new BufferedReader(isr)) {

			String line;
			while ((line = reader.readLine()) != null) {
				moduleWeights.add(Integer.parseInt(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	int getPart1FuelCounter() {
		return part1FuelCounter;
	}

	int getPart2FuelCounter() {
		return part2FuelCounter;
	}
}
