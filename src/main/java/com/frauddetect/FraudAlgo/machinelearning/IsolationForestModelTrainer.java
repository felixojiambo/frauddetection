package com.frauddetect.FraudAlgo.machinelearning;
import com.github.haifengl.smile.anomaly.IsolationForest;
import com.github.haifengl.smile.data.DataFrame;
import com.github.haifengl.smile.data.parser.DelimitedTextParser;
import java.io.File;
import java.io.IOException;

public class IsolationForestModelTrainer {
    public static void main(String[] args) throws IOException {
        // Load and parse the dataset
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        DataFrame data = parser.parse(new File("path_to_dataset.csv"));
        double[][] X = data.toArray();

        // Train Isolation Forest
        IsolationForest iforest = new IsolationForest(X, 100, 256); // 100 trees, 256 samples per tree

        // Save the model to a file
        smile.io.Serialization.write("isolation_forest_model.ser", iforest);
    }
}
