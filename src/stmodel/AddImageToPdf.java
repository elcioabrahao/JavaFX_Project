/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import controller.stmain1.ViewReceiptController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author PrasanthKumar
 */
public class AddImageToPdf {
    final static Logger logger = Logger.getLogger(AddImageToPdf.class.getName());

    public void addImage(WritableImage snapshot ) {

File pdfFile = null;
       PDDocument doc = null;
        try
        {
            
    // TODO: probably use a file chooser here
            logger.info("------------current file path: -------------"+new File(".").getAbsolutePath());
            String currentdir = new File(".").getAbsolutePath();
            File tempFolder = new File(currentdir+"\\temp1");
            if(! tempFolder.exists()){
                tempFolder.mkdir();
            }   
            pdfFile = new File(tempFolder+"\\Receipt.pdf");
         if(pdfFile.exists()){
             pdfFile.delete();
         }
         pdfFile.createNewFile();
            doc = new PDDocument();

            PDPage page = new PDPage();
            
       doc.addPage(page);

            PDImageXObject pdImage = LosslessFactory.createFromImage(doc, SwingFXUtils.fromFXImage(snapshot, null));
           
            PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true);
    float scale = 1f;
            PDRectangle mediaBox = page.getMediaBox();
            //logger.info("controller.stmain1.ViewReceiptController.btnEmailOnAction()"+mediaBox.getHeight());
            
           // contentStream.drawImage(pdImage, 72, mediaBox.getHeight() - 2 * 72, pdImage.getWidth()*scale, pdImage.getHeight()*scale);
            contentStream.drawImage(pdImage, 72, 300);
           // contentStream.drawImage(pdImage, 100, 100);

            contentStream.close();
            
            doc.save( pdfFile );
        }
        catch (IOException ex) {
            Logger.getLogger(ViewReceiptController.class.getName()).log(Level.FATAL, null, ex);
        }        finally
        {
            if( doc != null )
            {
                try {
                    doc.close();
                } catch (IOException ex) {
                    Logger.getLogger(AddImageToPdf.class.getName()).log(Level.FATAL, null, ex);
                }
            }
        }
        
}
    
}
