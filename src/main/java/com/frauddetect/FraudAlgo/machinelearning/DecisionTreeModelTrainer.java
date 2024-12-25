package com.frauddetect.FraudAlgo.machinelearning;
import com.github.haifengl.smile.classification.DecisionTree;
import com.github.haifengl.smile.data.DataFrame;
import com.github.haifengl.smile.data.parser.DelimitedTextParser;
import com.github.haifengl.smile.validation.CrossValidation;
import com.github.haifengl.smile.validation.Accuracy;
import java.io.File;
import java.io.IOException;

public class DecisionTreeModelTrainer {
    public static void main(String[] args) throws IOException {
        // Load and parse the dataset
        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setDelimiter(",");
        parser.setResponseIndex(parser.getColumnIndex("isFraud")); // Assuming 'isFraud' is the label
        DataFrame data = parser.parse(new File("path_to_dataset.csv"));

        // Split into features and labels
        double[][] X = data.drop("isFraud").toArray();
        int[] y = data.column("isFraud").toIntArray();

        // Train a Decision Tree model
        DecisionTree tree = DecisionTree.fit(X, y);

        // Evaluate the model using cross-validation
        int folds = 5;
        double accuracy = CrossValidation.cv(folds, (train, test) -> {
            DecisionTree model = DecisionTree.fit(X[train], y[train]);
            int[] predictions = model.predict(X[test]);
            return Accuracy.of(y[test], predictions);
        });
        System.out.println("Cross-validated Accuracy: " + accuracy);

        // Save the model to a file
        smile.io.Serialization.write("decision_tree_model.ser", tree);
    }
}
