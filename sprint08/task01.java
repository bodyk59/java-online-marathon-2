import java.util.function.BinaryOperator;

/*
 * Implement class ParallelCalculator that will be able to execute an operation in parallel thread.
 * Use the implementation of Runnable for this.
 * Constructor of ParallelCalculator should take 3 parameters:
 * BinaryOperator<Integer> to define an operation that will be executed,
 * int operand1 and int operand2.
 * ParallelCalculator class should have not private int result field where
 * the result of the operation will be written when it's executed.
 */

/**
 * @author Bogdan Kurchak
 */
class ParallelCalculator implements Runnable {
    private BinaryOperator<Integer> binaryOperator;
    private int operand1;
    private int operand2;
    public int result;

    public ParallelCalculator(BinaryOperator<Integer> binaryOperator, int operand1, int operand2) {
        this.binaryOperator = binaryOperator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = binaryOperator.apply(operand1, operand2);
    }
}
