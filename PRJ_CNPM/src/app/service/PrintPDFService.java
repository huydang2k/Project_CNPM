package app.service;

import app.repository.*;
import app.model.*;
import app.model.form.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PrintPDFService {
    public static DSPQChiTietService dspqChiTietService;
    public static DSPhatQuaService dsPhatQuaService;
    public static DSPTChiTietService dsptChiTietService;
    public static DSPhatThuongService dsPhatThuongService;
    public static DSPhatQuaRepo dsPhatQuaRepo;
    public static DSPhatThuongRepo dsPhatThuongRepo;

    public PrintPDFService()
    {
        dspqChiTietService = new DSPQChiTietService();
        dsptChiTietService = new DSPTChiTietService();
        dsPhatQuaService = new DSPhatQuaService();
        dsPhatThuongService = new DSPhatThuongService();
        dsPhatQuaRepo = new DSPhatQuaRepo();
        dsPhatThuongRepo = new DSPhatThuongRepo();
    }

    private String trangThaiToString(int tt) {
        switch(tt) {
            case -1:
                return "Đã xóa";
            case 0:
                return "Chờ nộp minh chứng";
            case 1:
                return "Đang phát";
            case 2:
                return "Hoàn thành";
            default:
                return "Unknown status";
        }
    }

    private String xacNhanToString(boolean xn) {
        return xn ? "V" : "O";
    }

    public void printDS(int maDS, boolean type, String fileName) throws IOException, DocumentException {
        Document doc = new Document();
        File fontFile = new File("font/vuArial.ttf");
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf,15);
        PdfWriter.getInstance(doc, new FileOutputStream("pdf/test.pdf"));

        if(type) //phát quà
        {
            try {
                doc.open();
                /**
                 * Tạo file PDF
                 */
                DSPhatQua dsPhatQua = dsPhatQuaRepo.findById(maDS);
                ArrayList<FormDSPQChiTiet> dspqChiTiet = new DSPQChiTietService().getFormDSPQChiTietByMaDS(maDS);
             //   PdfWriter.getInstance(doc, new FileOutputStream("pdf/"+dsPhatQua.getSuKien()+".pdf"));
                //Title
                Paragraph title = new Paragraph("Danh sách Phát quà " + dsPhatQua.getSuKien(),font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD, BaseColor.BLACK));
                doc.add(title);

                //Thông tin chung của DS

                Paragraph description = new Paragraph("Ngày tạo: " + dsPhatQua.getNgayTao() +
                                                    "     Trạng thái: " + trangThaiToString(dsPhatQua.getTrangThai()),font);

                description.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.NORMAL, BaseColor.BLACK));
                description.setIndentationLeft(80);
                doc.add(description);
                doc.add(new Phrase("\n"));

                //Hiển thị bảng thông tin

                PdfPTable tableDS = new PdfPTable(8);
                PdfPCell c1 = new PdfPCell(new Phrase("Mã DS"));
                PdfPCell c2 = new PdfPCell(new Phrase("Mã Nhân khẩu"));
                PdfPCell c3 = new PdfPCell(new Phrase("Họ tên",font));
                PdfPCell c4 = new PdfPCell(new Phrase("Năm sinh"));
                PdfPCell c5 = new PdfPCell(new Phrase("ID Hộ khẩu"));
                PdfPCell c6 = new PdfPCell(new Phrase("Phần qua"));
                PdfPCell c7 = new PdfPCell(new Phrase("Mức qua"));
                PdfPCell c8 = new PdfPCell(new Phrase("Được xác nhận"));
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
                    tableDS.addCell(new Phrase(xacNhanToString(sample.isDuocXacNhan()),font));
                }

                tableDS.setWidthPercentage(100);
                doc.add(tableDS);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try
            {
                DSPhatThuong dsPhatThuong = dsPhatThuongRepo.findById(maDS);
                ArrayList<FormDSPTChiTiet> dsptChiTiet = dsptChiTietService.getFormDSPTChiTietByMaDS(maDS);
                doc.open();

                Paragraph title = new Paragraph("Danh sách Phát thưởng "+ dsPhatThuong.getSuKien(), font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD, BaseColor.BLACK));
                doc.add(title);

                Paragraph description = new Paragraph("Ngày tạo: " + dsPhatThuong.getNgayTao() +
                        "     Trạng thái: " + trangThaiToString(dsPhatThuong.getTrangThai()),font);

                description.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.NORMAL, BaseColor.BLACK));
                description.setIndentationLeft(80);
                doc.add(description);
                doc.add(new Phrase("\n"));

                PdfPTable tableDS = new PdfPTable(11);
                PdfPCell c1 = new PdfPCell(new Phrase("Mã DS"));
                PdfPCell c2 = new PdfPCell(new Phrase("Mã HS"));
                PdfPCell c3 = new PdfPCell(new Phrase("Mã Nhân khẩu"));
                PdfPCell c4 = new PdfPCell(new Phrase("Họ tên",font));
                PdfPCell c5 = new PdfPCell(new Phrase("Năm sinh"));
                PdfPCell c6 = new PdfPCell(new Phrase("ID Hộ khẩu"));
                PdfPCell c7 = new PdfPCell(new Phrase("Thành tích"));
                PdfPCell c8 = new PdfPCell(new Phrase("Minh chứng"));
                PdfPCell c9 = new PdfPCell(new Phrase("Xếp loại"));
                PdfPCell c10 = new PdfPCell(new Phrase("Mức thưởng"));
                PdfPCell c11 = new PdfPCell(new Phrase("Được xác nhận"));
                tableDS.addCell(c1);
                tableDS.addCell(c2);
                tableDS.addCell(c3);
                tableDS.addCell(c4);
                tableDS.addCell(c5);
                tableDS.addCell(c6);
                tableDS.addCell(c7);
                tableDS.addCell(c8);
                tableDS.addCell(c9);
                tableDS.addCell(c10);
                tableDS.addCell(c11);

                for(FormDSPTChiTiet sample: dsptChiTiet)
                {
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdDS()),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdHocSinh()),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdNhanKhau()),font));
                    tableDS.addCell(new Phrase(sample.getHoTen(),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getNamSinh()),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getIdHoKhau()),font));
                    tableDS.addCell(new Phrase(sample.getThanhTich(),font));
                    tableDS.addCell(new Phrase(xacNhanToString(sample.isMinhChung()),font));
                    tableDS.addCell(new Phrase(sample.getXepLoai(),font));
                    tableDS.addCell(new Phrase(String.valueOf(sample.getMucThuong()),font));
                    tableDS.addCell(new Phrase(xacNhanToString(sample.isDuocXacNhan()),font));
                }

                tableDS.setWidthPercentage(100);
                doc.add(tableDS);

            } catch (Exception e)
            { e.printStackTrace(); }
        }
        doc.close();
    }
}
