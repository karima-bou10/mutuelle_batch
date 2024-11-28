package org.example.mutuelle_batch.Service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.mutuelle_batch.model.MedicamentsRef;
import org.example.mutuelle_batch.repository.MedicamentsRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;

@Service
public class ExcelToDatabaseService {
    //Ce service lit le fichier Excel et remplit la table MedicamentsRef
    private final MedicamentsRefRepository medicamentsRefRepository;
    @Autowired
    public ExcelToDatabaseService(MedicamentsRefRepository medicamentsRefRepository) {
        this.medicamentsRefRepository = medicamentsRefRepository;
    }

    public void importExcelData(String filePath) throws Exception {
        // Charger le fichier Excel depuis le chemin local
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Première feuille du fichier Excel

        for (Row row : sheet) {
            // Ignorer la ligne d'en-tête
            if (row.getRowNum() == 0) {
                continue;
            }

            // Extraire les données nécessaires
            long codeBarre = Long.parseLong(row.getCell(0).getStringCellValue()); // Colonne CODE
            String nomMedicament = row.getCell(1).getStringCellValue(); // Colonne NOM
            double prixReference = row.getCell(9).getNumericCellValue(); // Colonne PRIX
            String tauxRemboursement = row.getCell(11).getStringCellValue(); // Colonne TAUX_REMBOURSEMENT
            double pourcentageRemboursement = Double.parseDouble(tauxRemboursement.replace("%", ""))/ 100.0;

            MedicamentsRef medicament = new MedicamentsRef();
            medicament.setCodeBarre(codeBarre);
            medicament.setNomMedicament(nomMedicament);
            medicament.setPrixReference(prixReference);
            medicament.setPourcentageRemboursement(pourcentageRemboursement);

            medicamentsRefRepository.save(medicament);
        }

        workbook.close();
    }
}
