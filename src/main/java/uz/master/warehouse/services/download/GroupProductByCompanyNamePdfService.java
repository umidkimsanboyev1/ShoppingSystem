package uz.master.warehouse.services.download;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.services.product.ProductService;
import uz.master.warehouse.services.products.InComeProductsService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public final class GroupProductByCompanyNamePdfService {


    private final ProductService productService;
    private final InComeProductsService inComeProductsService;


    public ByteArrayInputStream groupProductReportByCompanyName(
            List<GroupProductsDto> groupProductsDtoList,
            String fromDate,
            String toDate,
            String companyName) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 2, 3, 4, 3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell cell2 = PdfPCell("Company name", headFont);
            table.addCell(cell2);

            PdfPCell cell1 = PdfPCell("T/R", headFont);
            table.addCell(cell1);

            PdfPCell cell4 = PdfPCell("Product model", headFont);
            table.addCell(cell4);

            PdfPCell cell5 = PdfPCell("Product color", headFont);
            table.addCell(cell5);

            PdfPCell cell6 = PdfPCell("Item price", headFont);
            table.addCell(cell6);

            PdfPCell cell7 = PdfPCell("Count", headFont);
            table.addCell(cell7);

            PdfPCell cell = PdfPCell("Date", headFont);
            table.addCell(cell);

            int l = 0;
            PdfPCell(companyName, groupProductsDtoList.size(), table);
            for (GroupProductsDto groupProducts : groupProductsDtoList) {
                LocalDateTime date = groupProducts.getDate();
                String dateFull = getDate(date);
                InComeProducts inComeProducts = inComeProductsService.getByGroupProducts(groupProducts.getId());
                ProductDto product = productService.get(inComeProducts.getProductId()).getData();

                PdfPCell(table, l + 1 + "");
                PdfPCell(table, product.getModel());
                PdfPCell(table, product.getColor());
                PdfPCell(table, inComeProducts.getItemPrice().toString());
                PdfPCell(table, String.valueOf(inComeProducts.getCount()));
                PdfPCell(table, dateFull);
                l++;
            }


            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph(Element.ALIGN_JUSTIFIED_ALL,
                    "A list of group products with the company name "+companyName+
                    " between " + fromDate + " and " + toDate + "\n\n\n"));
            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            System.err.println("Error " + ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private String getDate(LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int minute = date.getMinute();
        return year + "-" + month + "-" + day + "\n " + hour + ":" + minute;
    }


    private static PdfPCell PdfPCell(String name, Font headFont) {
        PdfPCell hCell;
        hCell = new PdfPCell(new Phrase(name, headFont));
        hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hCell.setVerticalAlignment(Element.ALIGN_CENTER);
        hCell.setBackgroundColor(BaseColor.CYAN);
        return hCell;
    }

    private static void PdfPCell(PdfPTable table, String groupProduct) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(groupProduct));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingRight(10);
        table.addCell(cell);
    }

    private static void PdfPCell(String date, int size, PdfPTable table) {
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(date, new Font(Font.FontFamily.TIMES_ROMAN, 14)));
        cell.setRowspan(size);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }


}
