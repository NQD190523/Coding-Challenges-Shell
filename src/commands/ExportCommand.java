package commands;

public class ExportCommand {

    public static void exportVariable(String variableName, String variableValue) {
        System.setProperty(variableName, variableValue);
    }
}
