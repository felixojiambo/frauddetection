package com.frauddetect.FraudAlgo.machinelearning;
import com.github.haifengl.smile.clustering.KMeans;
import com.github.haifengl.smile.data.DataFrame;
import com.github.haifengl.smile.data.parser.DelimitedTextParser;
import java.io.File;
import java.io.IOException;

public class KMeansModelTrainer {
    public static void main(String[] args) throws IOException {
        // Load and parse the dataset
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        DataFrame data = parser.parse(new File("path_to_dataset.csv"));
        double[][] X = data.toArray();

        // Train a K-Means model with 2 clusters (fraud and non-fraud)
        KMeans kmeans = KMeans.fit(X, 2);

        // Save the model to a file
        smile.io.Serialization.write("kmeans_model.ser", kmeans);
    }
}
