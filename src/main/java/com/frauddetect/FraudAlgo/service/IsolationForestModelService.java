package com.frauddetect.FraudAlgo.service;
import com.github.haifengl.smile.anomaly.IsolationForest;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class IsolationForestModelService {
    private IsolationForest isolationForestModel;

    @PostConstruct
    public void loadModel() throws IOException, ClassNotFoundException {
        isolationForestModel = (IsolationForest) smile.io.Serialization.read(new File("isolation_forest_model.ser"));
        System.out.println("Isolation Forest model loaded successfully.");
    }

    public IsolationForest getIsolationForestModel() {
        return isolationForestModel;
    }
}
