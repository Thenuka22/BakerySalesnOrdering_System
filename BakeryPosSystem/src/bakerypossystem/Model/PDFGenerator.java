/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

/**
 *
 * @author thenu
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {
  public static void generateBillPDF(
        String shopName,
        String shopAddress,
        String shopContact,
        List<String[]> billItems,
        String subTotal,
        String cash,
        String balance) {

    // Create a new PDF document
    PDDocument document = new PDDocument();
    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);

    try {
        // Create a content stream for the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font and position for your text
        contentStream.setFont(PDType1Font.COURIER_BOLD, 12);
        float margin = 50;
        float yPosition = page.getMediaBox().getHeight() - margin;

        // Add content to the PDF
        String[] shopDetails = {
            shopName,
            shopAddress,
            shopContact,
            "---------------------------------------------------------------------",
            "Item                                Qty              Price",
            "---------------------------------------------------------------------"
        };

        for (String line : shopDetails) {
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(line);
            contentStream.endText();
            yPosition -= 15;
        }

        int itemNameWidth = 200;
        int qtyWidth = 80;
        int priceWidth = 80;

        for (String[] billItem : billItems) {
            String itemName = billItem[0];
            String qty = billItem[1];
            String price = billItem[2];
            //For Debug Purpose //Data passes Correctly
            System.out.println("ItemName" + itemName);
            System.out.println("Qauntity" + qty);
            System.out.println("Price" + price);
            
          
            String formattedLine = String.format("%-" + itemNameWidth + "s %-" + qtyWidth + "s %-" + priceWidth + "s", itemName, qty, price);
            
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(formattedLine);
            contentStream.endText();
            
            yPosition -= 15; 
        }

        String[] summaryContent = {
            "---------------------------------------------------------------------",
            "Sub Total : " + subTotal,
            "Cash      : " + cash,
            "Balance   : " + balance,
            "---------------------------------------------------------------------",
            "                 Thanks For Visiting Us Come Again...!",
            "---------------------------------------------------------------------",
            "                      Software by CODSE22.3F - 072"
        };

        for (String line : summaryContent) {
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(line);
            contentStream.endText();
            yPosition -= 15;
        }

        contentStream.close();

        // Save the PDF
        document.save(new File("C:\\Users\\thenu\\OneDrive\\Desktop\\bill.pdf"));
        document.close();

        System.out.println("Bill PDF generated successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
