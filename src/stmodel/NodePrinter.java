package stmodel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebView;

/**
 * Prints any given area of a node to multiple pages
 */
public class NodePrinter {

    final static Logger logger = Logger.getLogger(NodePrinter.class.getName());

    public static void printWebView(WebView webView) {
        Printer printer = Printer.getDefaultPrinter();

        PageLayout pageLayout = printer.createPageLayout(Paper.A4,
                PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        // Printable area
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();
        logger.info("Printable area is " + pWidth + " width and "
                + pHeight + " height.");
        job.getJobSettings().setPageLayout(pageLayout);
        if (job != null) {
            System.out.println(job.getJobSettings().getPageLayout());
            webView.getEngine().print(job);
            job.endJob();
        }
    }

    public static void printImage(Node node) {

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.getDefaultPageLayout();
        logger.info("PageLayout: " + pageLayout);

        // Printable area
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();
        logger.info("Printable area is " + pWidth + " width and "
                + pHeight + " height.");

        // Node's (Image) dimensions
        logger.info("" + node.getProperties());
        double nWidth = node.getBoundsInParent().getWidth();
        double nHeight = node.getBoundsInParent().getHeight();
        logger.info("Node's dimensions are " + nWidth + " width and "
                + nHeight + " height");

        // Check if image is big !
        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;
        logger.info("Width left: " + widthLeft
                + " height left: " + heightLeft);

        // fit the image
        double scale = 0;

        if (widthLeft < heightLeft) {
            scale = pWidth / nWidth;
        } else {
            scale = pHeight / nHeight;
        }

        // preserve ratio (both values are the same)
        node.getTransforms().add(new Scale(scale, scale));

        // after scale you can check the size fit in the printable area
        double newWidth = node.getBoundsInParent().getWidth();
        double newHeight = node.getBoundsInParent().getHeight();
        logger.info("New Node's dimensions: " + newWidth
                + " width " + newHeight + " height");

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }

    }

    public static void print(Node node) {
        try {
            logger.info("Creating a printer job...");

            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                logger.info("" + job.jobStatusProperty().asString());

                double newWidth = node.getBoundsInParent().getWidth();
                double newHeight = node.getBoundsInParent().getHeight();
                logger.info("Node's dimensions: " + newWidth
                        + " width " + newHeight + " height");
                boolean printed = job.printPage(node);
                if (printed) {
                    job.endJob();
                } else {
                    logger.info("Printing failed.");
                }
            } else {
                logger.info("Could not create a printer job.");
            }
        } catch (Exception e) {
            Logger.getLogger(NodePrinter.class.getName()).log(Level.FATAL, null, e);
        }
    }

    public static void printNode(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {
            boolean printed = job.printPage(node);

            if (printed) {
                job.endJob();
            } else {
                logger.info("Printing failed.");
            }
        } else {
            logger.info("Printing failed.");
        }
    }

}
