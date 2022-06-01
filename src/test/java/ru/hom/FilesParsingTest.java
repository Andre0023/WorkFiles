package ru.hom;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {


    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void parsePdfTest()throws Exception{
        Selenide.open("http://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload =  Selenide.$(byText("PDF download")).download();
        PDF parsed = new PDF(pdfDownload);
        assertThat(parsed.author).contains("Marc Philip");

    }
//    @Test
//    void parseXlsTest()throws Exception{
//        try   (InputStream stream = cl.getResourceAsStream("FAQ-2022.xlsx")){
//        XLS parsed = new XLS(stream);
//        assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue())
//                .isEqualTo("Ответ");
//        }
//
//
//
//    }

    @Test

    void zipTest()throws Exception{
        try   (InputStream stream = cl.getResourceAsStream("Law.zip");
                ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null){

                assertThat(zipEntry.getName()).isEqualTo("pir-console (12).log");
            }

        }



    }



    }

