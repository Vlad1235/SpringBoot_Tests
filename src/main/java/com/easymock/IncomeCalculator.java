package com.easymock;

/**
 * The IncomeCalculator class should be tested.
 * The class has the purpose to calculate the salary of a person based on the provided method and position.
 * Obviously the test depends on the provided methods.
 */
public class IncomeCalculator {
    private ICalcMethod calcMethod; // зависим от метода из интерфейса
    private Position position; // зависим от метода из enum

    public void setCalcMethod(ICalcMethod iCalcMethod) {
        this.calcMethod = iCalcMethod;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double calc() { // метод для тестирования в JUnit с использованием EasyMock
        if (calcMethod == null) {
            throw new RuntimeException("CalcMethod not yet maintained");
        }
        if (position == null) {
            throw new RuntimeException("Position not yet maintained");
        }
        return calcMethod.calc(position);
    }
}
