package thiagocury.eti.br.ccf;


import android.content.Context;
import android.os.Environment;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mauriciormp on 23/02/2016.
 */
public class ExportarPDF {

    public void exportarPDF(Context context, ArrayList<Cliente> clientes, String nome) throws IOException, DocumentException {

        //montando data atual
        DateFormat data = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        data.setLenient(false);
        Date hoje = new Date();
        String dataHoje = data.format(hoje);

        //montando o arquivo
        String csv = Environment.getExternalStorageDirectory() + "/" +nome+"-"+dataHoje+".pdf";
        Rectangle pagesize = new Rectangle(842f, 595f);
        Document folha = new Document(pagesize, 12f, 72f, 36f, 36f);

        PdfWriter.getInstance(folha, new FileOutputStream(csv));

        folha.open();
        //Metadados
        folha.addSubject("PDF gerado pelo App CCF");
        folha.addKeywords("www.thiagocury.eti.br");
        folha.addCreator("CCF");
        folha.addAuthor("Thiago Cury");

        //Configurando o documento
        try{
            //criar fonte
            Font f = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0,0,255));

            //Criando o TÍTULO DA TABELA
            //Criando o TÍTULO DA TABELA
            PdfPTable t = new PdfPTable(3);
            t.setWidthPercentage(100);

            //criando celula
            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);

            t.addCell(cell);
            PdfPCell c1 = new PdfPCell();

            c1.setBorder(PdfPCell.NO_BORDER);
            c1.setColspan(2);
            f.setColor(10, 10, 10);
            f.setSize(10);
            c1.addElement(new Paragraph("Relatório CCF - Data emissão "+dataHoje+"\n", f));
            c1.addElement(new Paragraph("\n", f));
            t.addCell(c1);
            folha.add(t);

            //criando tabela com conteudo
            f.setSize(10);


            //Criando o CONTEÚDO DA TABELA
            //Criando o CONTEÚDO DA TABELA
            PdfPTable tabela = new PdfPTable(12);
            tabela.setWidthPercentage(100);

            PdfPCell coluna1 = new PdfPCell(new Paragraph("Código", f));
            coluna1.setBackgroundColor(BaseColor.GRAY);
            coluna1.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna1.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna2 = new PdfPCell(new Paragraph("Nome", f));
            coluna2.setBackgroundColor(BaseColor.GRAY);
            coluna2.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna2.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna3 = new PdfPCell(new Paragraph("Rg", f));
            coluna3.setBackgroundColor(BaseColor.GRAY);
            coluna3.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna3.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna4 = new PdfPCell(new Paragraph("Cpf", f));
            coluna4.setBackgroundColor(BaseColor.GRAY);
            coluna4.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna4.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna5 = new PdfPCell(new Paragraph("Endereço", f));
            coluna5.setBackgroundColor(BaseColor.GRAY);
            coluna5.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna5.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna6 = new PdfPCell(new Paragraph("Bairro", f));
            coluna6.setBackgroundColor(BaseColor.GRAY);
            coluna6.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna6.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna7 = new PdfPCell(new Paragraph("Cidade/UF", f));
            coluna7.setBackgroundColor(BaseColor.GRAY);
            coluna7.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna7.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna8 = new PdfPCell(new Paragraph("Cep", f));
            coluna8.setBackgroundColor(BaseColor.GRAY);
            coluna8.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna8.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna9 = new PdfPCell(new Paragraph("T. Fixo", f));
            coluna9.setBackgroundColor(BaseColor.GRAY);
            coluna9.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna9.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna10 = new PdfPCell(new Paragraph("T. Celular", f));
            coluna10.setBackgroundColor(BaseColor.GRAY);
            coluna10.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna10.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna11 = new PdfPCell(new Paragraph("Email", f));
            coluna11.setBackgroundColor(BaseColor.GRAY);
            coluna11.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna11.setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell coluna12 = new PdfPCell(new Paragraph("Obs", f));
            coluna12.setBackgroundColor(BaseColor.GRAY);
            coluna12.setHorizontalAlignment(Element.ALIGN_CENTER);
            coluna12.setVerticalAlignment(Element.ALIGN_CENTER);

            //tabela.setWidths(new int[]{670, 700, 750, 750, 1000, 800, 700, 700, 700, 700, 850, 1500});
            tabela.addCell(coluna1);
            tabela.addCell(coluna2);
            tabela.addCell(coluna3);
            tabela.addCell(coluna4);
            tabela.addCell(coluna5);
            tabela.addCell(coluna6);
            tabela.addCell(coluna7);
            tabela.addCell(coluna8);
            tabela.addCell(coluna9);
            tabela.addCell(coluna10);
            tabela.addCell(coluna11);
            tabela.addCell(coluna12);

            //montando dados da tabela
            ArrayList<Cliente> cli = clientes;

            for(int i = 0; i < cli.size(); i ++){

                String idcliente = String.valueOf(cli.get(i).getIdcliente());
                String nomec = String.valueOf(cli.get(i).getNome());
                String rg = String.valueOf(cli.get(i).getRg());
                String cpf = String.valueOf(cli.get(i).getCpf());
                String endereco = String.valueOf(cli.get(i).getEndereco()+", "+cli.get(i).getNumeroendereco());
                String bairro = String.valueOf(cli.get(i).getBairro());
                String cidade = String.valueOf(cli.get(i).getCidade()+"/"+cli.get(i).getUf());
                String cep = String.valueOf(cli.get(i).getCep());
                String telefonefixo = String.valueOf(cli.get(i).getTelefonefixo());
                String telefonecelular = String.valueOf(cli.get(i).getTelefonecelular());
                String email = String.valueOf(cli.get(i).getEmail());
                String obs = String.valueOf(cli.get(i).getObs());

                tabela.addCell(idcliente);
                tabela.addCell(nomec);
                tabela.addCell(rg);
                tabela.addCell(cpf);
                tabela.addCell(endereco);
                tabela.addCell(bairro);
                tabela.addCell(cidade);
                tabela.addCell(cep);
                tabela.addCell(telefonefixo);
                tabela.addCell(telefonecelular);
                tabela.addCell(email);
                tabela.addCell(obs);
            }//fecha for
            folha.add(tabela);
            folha.close();

        }catch(Exception e){
            System.out.print("Erro ao gerar pdf. " + e.getMessage());
        }//fecha try_catch
    }//fecha exportarPDF
}//fecha ExportarPDF