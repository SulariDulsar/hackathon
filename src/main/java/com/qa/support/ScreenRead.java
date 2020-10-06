package com.qa.support;



import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;

public class ScreenRead {

    public static String getText(String filepath){

        File imageFile = null;
        ITesseract instance = null;
        String imagePathForScreenRead = Report.getInstance().getReportPath()+ filepath;

        try {
            System.out.println("####  "+imagePathForScreenRead);
            imageFile = new File(imagePathForScreenRead);
            System.out.println("Read From Image :" + imageFile.toString());
            instance = new Tesseract();
            File tessDataFolder = LoadLibs.extractTessResources("tessdata");
            System.out.println("@@@@@@@@@@  "+tessDataFolder.getParent());
            instance.setDatapath(tessDataFolder.getAbsolutePath());
            //instance.setDatapath("/Users/sularidulsaraweerawardena/tess/tessdata");
            instance.setLanguage("eng");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String text = null;
        String result = null;
        try {
            text = instance.doOCR(imageFile);
            System.out.println("=======================================");
            System.out.println(text);
            System.out.println("=======================================");


        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }
}
