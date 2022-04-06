package uz.master.warehouse.services.download;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.CompanyService;
import uz.master.warehouse.services.organization.FirmService;
import uz.master.warehouse.services.product.GroupProductsService;
import uz.master.warehouse.services.product.ProductService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupProductBetweenDatePdfService {

    private final CompanyService companyService;
    private final ProductService productService;
    private final GroupProductsService groupProductsService;
    private final FirmService firmService;


    public ByteArrayInputStream groupProductReport(
            List<GroupProductsDto> groupProductsDtos,
            String fromDate,
            String toDate) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        try {

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 2, 3, 4, 3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell cell8 = PdfPCell("Date", headFont);
            table.addCell(cell8);

            PdfPCell cell1 = PdfPCell("T/R", headFont);
            table.addCell(cell1);

            PdfPCell cell2 = PdfPCell("Company name", headFont);
            table.addCell(cell2);


            PdfPCell cell4 = PdfPCell("Product model", headFont);
            table.addCell(cell4);

            PdfPCell cell5 = PdfPCell("Product color", headFont);
            table.addCell(cell5);

            PdfPCell cell6 = PdfPCell("Product item count", headFont);
            table.addCell(cell6);

            PdfPCell cell7 = PdfPCell("Product default price", headFont);
            table.addCell(cell7);


            List<Date> dateList = groupProductsService.getDate(fromDate, toDate);

            System.err.println("-------------groupProductsDtos.size() = " + groupProductsDtos.size());


            int l = 0;
            for (int i = 0; i < groupProductsDtos.size(); i++) {
                GroupProductsDto groupProductsDto = groupProductsDtos.get(i);
                String companyName = companyService.getName(groupProductsDto.getCompanyId());
                DataDto<List<FirmDto>> firms = firmService.getCompany(groupProductsDto.getCompanyId());
                for (Date date : dateList) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DATE);
                    System.err.println("--------------year = " + year);
                    System.err.println("--------------month = " + month);
                    System.err.println("--------------day = " + day);

                    for (FirmDto firmDto : firms.getData()) {

                        System.err.println("-----------firmDto = " + firmDto.toString());
                        DataDto<List<ProductDto>> products = productService.getByFirmId(firmDto.getId());
                        String dateFull = year + "-" + month + "-" + day;
                        PdfPCell(dateFull, products.getData().size(), table);
                        for (ProductDto productDto : products.getData()) {
                            System.err.println("-----------l = " + l);
                            PdfPCell(table, l + 1 + "");
                            PdfPCell(table, companyName);
                            PdfPCell(table, productDto.getModel());
                            PdfPCell(table, productDto.getColor());
                            PdfPCell(table, productDto.getItem_count().toString());
                            PdfPCell(table, productDto.getDefault_price().toString());
                            l++;
                        }

                    }
                }
            }


            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph(Element.ALIGN_JUSTIFIED_ALL, fromDate + " and " + toDate + "\n\n\n"));
            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            System.err.println("Error " + ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


    private static PdfPCell PdfPCell(String name, Font headFont) {
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase(name, headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setVerticalAlignment(Element.ALIGN_CENTER);
        return hcell;
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
