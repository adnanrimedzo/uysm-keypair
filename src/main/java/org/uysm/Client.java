package org.uysm;

import javafx.util.Pair;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.uysm.fileoperation.FileOperations;
import org.uysm.keypair.KeyPairGenerator;

import java.util.ArrayList;
import java.util.List;

public class Client {

    @Option(name = "-s", usage = "status: generate , validate")
    private String status = "generate";

    @Option(name = "-key", usage = "input key")
    private String key = "";

    @Option(name = "-out", usage = "export result.out file into a path")
    public String outPath = "";

    @Option(name = "-in", usage = "validate keys.out file from a path")
    public String inPath = "";

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        new Client().doMain(args);
        System.exit(0);
    }

    public void doMain(String[] args) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);

        parser.setUsageWidth(80);

        try {
            parser.parseArgument(args);

            if ("validate".equals(status)) {
                List<String> keys = FileOperations.readLines(inPath);
                String key = KeyPairGenerator.decode(new Pair<>(keys.get(0), keys.get(1)));
                FileOperations.writeLine(outPath, key);
            } else if ("generate".equals(status)) {
                Pair<String, String> keys = KeyPairGenerator.generate(key);
                List<String> lines = new ArrayList<>();
                lines.add(keys.getKey());
                lines.add(keys.getValue());
                FileOperations.writeLines(outPath, lines);
            }

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            parser.printUsage(System.err);
            System.err.println();

            return;
        }

    }
}
