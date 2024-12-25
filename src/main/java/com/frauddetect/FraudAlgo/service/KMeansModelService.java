package com.frauddetect.FraudAlgo.service;
import com.github.haifengl.smile.clustering.KMeans;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class KMeansModelService {
    private KMeans kmeansModel;

    @PostConstruct
    public void loadModel() throws IOException, ClassNotFoundException {
        kmeansModel = (KMeans) smile.io.Serialization.read(new File("kmeans_model.ser"));
        System.out.println("K-Means model loaded successfully.");
    }

    public KMeans getKMeansModel() {
        return kmeansModel;
    }
}
