package app;

public class FuelCounterUpper {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.calculateFuelRequirements(true);
		System.out.println("calculator.getFuelCounter() = " + calculator.getPart1FuelCounter());
		System.out.println("calculator.getFuelCounter2() = " + calculator.getPart2FuelCounter());
	}
}
