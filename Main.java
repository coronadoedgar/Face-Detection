
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

class DetectFaceDemo {
    CascadeClassifier faceDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
    MatOfRect faceDetections = new MatOfRect();
    VideoCapture cap = new VideoCapture(0);
    Mat imagen=new Mat();
    
    
    public void run() {
        System.out.println("\nDeteccion de rostros con OpenCV y Webcam en java");
        Deteccion ventana = new Deteccion();

        if(cap.isOpened()){
            while(true){
                if(ventana.WEBCAM){
                    try {
                        //Thread.sleep(100);
                        cap.read(imagen);
                        if(!imagen.empty()){
                            faceDetector.detectMultiScale(imagen, faceDetections);
                            boolean NoEntro=true;
                            for (Rect rect : faceDetections.toArray()) {
                                Core.rectangle(imagen, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                                if(NoEntro){
                                    Mat region = new Mat(imagen,rect);
                                    Highgui.imwrite("procesada_01.jpg", region);
                                    NoEntro=false;
                                }
                            }
                            ventana.setImage(convertir(imagen));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DetectFaceDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }        
    }
    
    private Image convertir(Mat imagen) {
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", imagen, matOfByte); 

        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;

        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Image)bufImage;
    }
}

public class Main {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        System.out.println(Core.VERSION+""+Core.VERSION_MAJOR+""+Core.VERSION_MINOR+""+Core.VERSION_REVISION);
        new DetectFaceDemo().run();
	}

}
