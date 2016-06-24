import model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by AsusPC on 6/22/2016.
 */
public class Test {
    private static String FILE_PATH = "C:\\Users\\AsusPC\\Google Drive\\IntelliJ Idea Projects\\KNN\\knn-fossil-datatraining.txt";

    private static int K = 25;
    private static int DATA_COUNT = 87;
    private static int CLUSTER_TYPE = 3;

    private static double[] DATA_TEST = {0, 0.59, 0.01, 0.99, 0, 0.44, 0.33}; // Cluster 1
//    private static double[] DATA_TEST = {0.97, 0, 0.01, 0.85, 0.95, 1, 0.67}; // Cluster 2
//    private static double[] DATA_TEST = {0.04, 1, 0.36, 0.24, 1, 0, 1}; // Cluster 3
//    private static double[] DATA_TEST = {0.67, 0.19, 0.51, 0.07, 0.99, 0.94, 0.33}; // Data Acak

    private static List<Node> nodes = new ArrayList<>();
    private static List<Node> kNode = new ArrayList<>();
    private static List<Double> nodeDistances = new ArrayList<>();

    public static void main(String args[]) {
        double[] dblParts = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < DATA_COUNT; i++) {
                String line = br.readLine();
                sb.append(line);
                sb.append(System.lineSeparator());

                String[] parts = line.split(",");
                dblParts = new double[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    dblParts[j] = Double.parseDouble(parts[j]);
                }
                Node tmpNode = new Node();
                tmpNode.setId(i);
                tmpNode.setData(dblParts);
                nodes.add(tmpNode);
            }

            hitungKNN();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void hitungKNN() {
        for (int i = 0; i < DATA_COUNT; i++) {
            Node node = nodes.get(i);
            double[] nodeData = node.getData();

            double distance = Math.sqrt(
                    Math.pow((DATA_TEST[0] - nodeData[0]), 2) +
                            Math.pow((DATA_TEST[1] - nodeData[1]), 2) +
                            Math.pow((DATA_TEST[2] - nodeData[2]), 2) +
                            Math.pow((DATA_TEST[3] - nodeData[3]), 2) +
                            Math.pow((DATA_TEST[4] - nodeData[4]), 2));
            nodeDistances.add(distance);
            node.setDistance(distance);
        }

        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node p1, Node p2) {
                double result = (p1.getDistance() - p2.getDistance());
                if (result < 0)
                    return -1;
                else if (result == 0)
                    return 0;
                else
                    return 1;
            }
        });

        for (int i = 0; i < K; i++) {
            Node node = nodes.get(i);
//            System.out.println(node.getDistance());
            kNode.add(node);
        }

        int[] countK = new int[CLUSTER_TYPE];

        for (int i = 0; i < K; i++) {
            if (kNode.get(i).getData()[6] == 0.33) {
                countK[0]++;
            } else if (kNode.get(i).getData()[6] == 0.67) {
                countK[1]++;
            } else if (kNode.get(i).getData()[6] == 1) {
                countK[2]++;
            }
        }

        System.out.println("Cluster 1 : " + countK[0]);
        System.out.println("Cluster 2 : " + countK[1]);
        System.out.println("Cluster 3 : " + countK[2]);

        int max = Math.max(Math.max(countK[0], countK[1]), countK[2]);
        if (max == countK[0])
            System.out.println("RESULT = Cluster 1");
        else if (max == countK[1])
            System.out.println("RESULT = Cluster 2");
        else
            System.out.println("RESULT = Cluster 3");
    }

}