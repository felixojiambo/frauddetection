package com.frauddetect.FraudAlgo.service;
import com.github.haifengl.smile.classification.DecisionTree;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class DecisionTreeModelService {
    private DecisionTree decisionTreeModel;

    @PostConstruct
    public void loadModel() throws IOException, ClassNotFoundException {
        decisionTreeModel = (DecisionTree) smile.io.Serialization.read(new File("decision_tree_model.ser"));
        System.out.println("Decision Tree model loaded successfully.");
    }

    public DecisionTree getDecisionTreeModel() {
        return decisionTreeModel;
    }
}
