package uz.master.warehouse.services.download;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.services.organization.CompanyService;
import uz.master.warehouse.services.organization.FirmService;
import uz.master.warehouse.services.product.GroupProductsService;
import uz.master.warehouse.services.product.ProductService;
import uz.master.warehouse.services.products.InComeProductsService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public final class GroupProductBetweenDatePdfService {
    private final CompanyService companyService;
    private final ProductService productService;
    private final GroupProductsService groupProductsService;
    private final InComeProductsService inComeProductsService;
    private final FirmService firmService;

    public GroupProductBetweenDatePdfService(CompanyService companyService,
                                             ProductService productService,
                                             GroupProductsService groupProductsService,
                                             InComeProductsService inComeProductsService,
                                             FirmService firmService) {
        this.companyService = companyService;
        this.productService = productService;
        this.groupProductsService = groupProductsService;
        this.inComeProductsService = inComeProductsService;
        this.firmService = firmService;
    }

    public ByteArrayInputStream groupProductReport(
            List<GroupProductsDto> groupProductsDtoList,
            String fromDate,
            String toDate) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 3, 4, 3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell cell1 = PdfPCell("T/R", headFont);
            table.addCell(cell1);

            PdfPCell cell2 = PdfPCell("Company name", headFont);
            table.addCell(cell2);

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
            for (GroupProductsDto groupProducts : groupProductsDtoList) {
                LocalDateTime date = groupProducts.getDate();
                int year = date.getYear();
                int month = date.getMonthValue();
                int day = date.getDayOfMonth();
                int hour = date.getHour();
                int minute = date.getMinute();
                String dateFull = year + "-" + month + "-" + day + "\n " + hour + ":" + minute;
                String companyName = companyService.getName(groupProducts.getCompanyId());
                InComeProducts inComeProducts = inComeProductsService.getByGroupProducts(groupProducts.getId());
                ProductDto product = productService.get(inComeProducts.getProductId()).getData();

                PdfPCell(table, l + 1 + "");
                PdfPCell(table, companyName);
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
                    "  Group products between date " + fromDate + " and " + toDate + "\n\n\n"));
            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            System.err.println("Error " + ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
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


    @Override
    public int hashCode() {
        return Objects.hash(companyService, productService, groupProductsService, firmService);
    }

    @Override
    public String toString() {
        return "GroupProductBetweenDatePdfService[" +
                "companyService=" + companyService + ", " +
                "productService=" + productService + ", " +
                "groupProductsService=" + groupProductsService + ", " +
                "firmService=" + firmService + ']';
    }

    public CompanyService companyService() {
        return companyService;
    }

    public ProductService productService() {
        return productService;
    }

    public GroupProductsService groupProductsService() {
        return groupProductsService;
    }

    public InComeProductsService inComeProductsService() {
        return inComeProductsService;
    }

    public FirmService firmService() {
        return firmService;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GroupProductBetweenDatePdfService) obj;
        return Objects.equals(this.companyService, that.companyService) &&
                Objects.equals(this.productService, that.productService) &&
                Objects.equals(this.groupProductsService, that.groupProductsService) &&
                Objects.equals(this.inComeProductsService, that.inComeProductsService) &&
                Objects.equals(this.firmService, that.firmService);
    }


}
