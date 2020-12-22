package app.common;

import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.model.DuocNhanQua;
import app.model.form.FormDSPQChiTiet;
import app.model.form.FormDSPTChiTiet;
import app.repository.DSPhatQuaRepo;
import app.repository.DSPhatThuongRepo;
import app.repository.DuocNhanQuaRepo;
import app.repository.DuocNhanThuongRepo;
import app.service.DSPQChiTietService;
import app.service.DSPTChiTietService;
import app.service.DSPhatQuaService;
import app.service.DSPhatThuongService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class PrintPDF {
    public static DSPQChiTietService dspqChiTietService;
    public static DSPhatQuaService dsPhatQuaService;
    public static DSPTChiTietService dsptChiTietService;
    public static DSPhatThuongService dsPhatThuongService;
    public static DSPhatQuaRepo dsPhatQuaRepo;
    public static DSPhatThuongRepo dsPhatThuongRepo;

    public PrintPDF()
    {
        dspqChiTietService = new DSPQChiTietService();
        dsptChiTietService = new DSPTChiTietService();
        dsPhatQuaService = new DSPhatQuaService();
        dsPhatThuongService = new DSPhatThuongService();
        dsPhatQuaRepo = new DSPhatQuaRepo();
        dsPhatThuongRepo = new DSPhatThuongRepo();
    }

    public void printDS(int maDS, boolean type) {
        Document doc = new Document();
        File fontFile = new File("font/vuArial.ttf");

        if(type) //phát thưởng
        {
            try {
                BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(bf,15);
                PdfWriter.getInstance(doc, new FileOutputStream("pdf/Test.pdf"));
                doc.open();
//                ArrayList<FormDSPQChiTiet> dspqChiTiet = dspqChiTietService.getFormDSPQChiTietByMaDS(maDS);
//                DSPhatQua dsPhatQua = dsPhatQuaRepo.findById(maDS);

                /**
                 * Fake data
                 * (To dangquanghuy-2k) khi nhét vào thì bỏ hết đoạn fake data đi nhé,
                 * rồi kéo data tử repo lên xem lỗi không (nhớ giữ tên biến)
                 * (chỉ lấy data của danh sách đã hoàn thành)
                 */
//                ArrayList<FormDSPQChiTiet> dspqChiTiet = new ArrayList<>();
//                for(int i = 1; i <= 10; i++) {
//                    dspqChiTiet.add(new FormDSPQChiTiet(1, i, "Nguyen Vặn " + i, 1990, 15, "A", 100_000, true ));
//                }
//                DSPhatQua dsPhatQua = new DSPhatQua(1, "Giáng sinh 2020", null, 1, 0.0);

                /**
                 * Tạo file PDF
                 */
                DSPhatQua dsPhatQua = dsPhatQuaRepo.findById(1);
                ArrayList<FormDSPQChiTiet> dspqChiTiet = new DSPQChiTietService().getFormDSPQChiTietByMaDS(1);
                //Title
                Paragraph title = new Paragraph("Danh sách Phát quà " + dsPhatQua.getSuKien(),font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD, BaseColor.BLACK));
                doc.add(title);

                //Thông tin chung của DS
                Paragraph description = new Paragraph("Ngày tao: " + dsPhatQua.getNgayTao() +
                                                    "     Trang thái: " + dsPhatQua.getTrangThai(),font);
                description.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.NORMAL, BaseColor.BLACK));
                description.setIndentationLeft(80);
                doc.add(description);
                doc.add(new Phrase("\n"));

                //Hiển thị bảng thông tin

                PdfPTable tableDS = new PdfPTable(8);
                PdfPCell c1 = new PdfPCell(new Phrase("Mã DS"));
                PdfPCell c2 = new PdfPCell(new Phrase("Mã Nhan khau"));
                PdfPCell c3 = new PdfPCell(new Phrase("Họ ten",font));
                PdfPCell c4 = new PdfPCell(new Phrase("Nam sinh"));
                PdfPCell c5 = new PdfPCell(new Phrase("ID Ho khau"));
                PdfPCell c6 = new PdfPCell(new Phrase("Phan qua"));
                PdfPCell c7 = new PdfPCell(new Phrase("Muc qua"));
                PdfPCell c8 = new PdfPCell(new Phrase("Duoc xac nhan"));
                tableDS.addCell(c1);
                tableDS.addCell(c2);
                tableDS.addCell(c3);
                tableDS.addCell(c4);
                tableDS.addCell(c5);
                tableDS.addCell(c6);
                tableDS.addCell(c7);
                tableDS.addCell(c8);
                for (FormDSPQChiTiet sample: dspqChiTiet)
                {
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdDS()),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdNhanKhau()),font));
                    tableDS.addCell(new Phrase(sample.getHoTen(),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getNamSinh()),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdHoKhau()),font));
                    tableDS.addCell(new Phrase(sample.getPhanQua(),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getMucQua())));

                    tableDS.addCell(new Phrase(String.valueOf(sample.isDuocXacNhan()),font));
                }

                tableDS.setWidthPercentage(100);
                doc.add(tableDS);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        doc.close();
    }

//    public static void main(String[] args) {
//        PrintPDF printPDF = new PrintPDF();
//        //type = true: danh sách phát quà
//        //type = false: danh sách phát thưởng
//        printPDF.printDS(2, true);
//
//    }
}
