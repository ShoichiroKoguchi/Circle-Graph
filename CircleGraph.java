import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.canvas.GraphicsContext;
import java.io.*;

public class CircleGraph extends Application {

  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    Canvas canvas = new Canvas(400, 400);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawShapes(gc);

    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 400, 400, Color.rgb(255,255,255));
    st.setTitle("Circle Graph");
    st.setScene(scene);
    st.show();
  }

  public static void main(String[] a) {
    launch(a);
  }

  public void drawShapes(GraphicsContext gc) {
    try {
      File file = new File("./data.txt");
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String str = br.readLine();
      String[] data = str.split(" ");
        int[] pa = new int[data.length];
        for (int i = 0; i < data.length; i++) {
        pa[i] = Integer.parseInt(data[i]);
  }
  int pamax = pa[0];
  for (int i = 0; i < pa.length; i++) {
    if (pamax < pa[i]){
      pamax =pa[i];
    }
  }
   if (pa.length > 17) {System.err.println("データの数を１７個以下にしてください。");
                       System.exit(0);}
   if (pamax>1000) {System.err.println("データの最大値を1000以下にしてください。");
                        System.exit(0);}
   else
    // 円グラフを描く
      gc.setFill(Color.GRAY);
      gc.fillRect(340,40,50,pa.length * 21);
      gc.setStroke(Color.BLACK);
      gc.strokeLine(340,40,340,40 + pa.length * 21);
      gc.strokeLine(390,40,390,40 + pa.length * 21);
      gc.strokeLine(340,40,390,40);
      gc.strokeLine(340,40 + pa.length * 21,390,40 + pa.length * 21);
      int sum = 0;
      for (int i = 0; i < pa.length; i++) {
        sum += pa[i];
      }
      int sum2 = 0;
      for (int i = 0; i < pa.length; i++) {
       if (i == 0) sum2 = 0;
       else
       sum2 += pa[i-1];
       double x1 = (double) pa[i]/sum;
       double y1 = (double) (x1*360);
       double x2 = (double) sum2/sum;
       double y2 = (double) (x2*360);
       int R = (int) (Math.random()*256);
       int G = (int) (Math.random()*256);
       int B = (int) (Math.random()*256);
       gc.setFill(Color.rgb(R,G,B));
       gc.fillArc(100,100,200,200,y2,y1,ArcType.ROUND);
       gc.fillRect(350,50 + 20 * i,10,10);
       gc.setFill(Color.BLACK);
       gc.strokeLine(350,50 + 20 * i,360,50 + 20 * i);
       gc.strokeLine(350,60 + 20 * i,360,60 + 20 * i);
       gc.strokeLine(350,50 + 20 * i,350,60 + 20 * i);
       gc.strokeLine(360,50 + 20 * i,360,60 + 20 * i);
       gc.fillText(String.valueOf(i + 1),370,60 + 20 * i);
       int r = 120;
       double rd = Math.PI / 180;
       int x = (int) (r * Math.cos((y1 / 2 + y2) * rd) + 190);
       int y = (int) (205 - r * Math.sin((y1 / 2 + y2) * rd));
       gc.fillText(String.valueOf(pa[i]),x,y);;
  }
  } catch (FileNotFoundException e) {
    System.err.println("ファイルが開けませんでした．");
  } catch (IOException e) { // readLine()からの例外を処理
    System.err.println("ファイルの読み込みに失敗しました．");
  }

}
}
