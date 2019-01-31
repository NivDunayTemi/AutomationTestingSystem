package AutomationTestingSystem.Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.RunListener;

public class Main {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new ExecutionListener());
        runner.run(BootingTest.class);
    }

    private static class ExecutionListener extends RunListener
    {

    }
}
