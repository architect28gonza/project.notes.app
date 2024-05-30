package com.note.validation.domain.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.note.validation.domain.repository.UploadRepository;

import com.note.persistence.entitys.GroupEntity;
import com.note.persistence.entitys.NoteEntity;
import com.note.validation.domain.repository.GroupRepository;
import com.note.validation.domain.services.GestorNoteServices;
import com.note.validation.domain.services.ProcessFileServices;
import com.note.validation.persistence.model.StructureJson;
import com.note.web.util.UtilLogger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProcessFileImpl implements ProcessFileServices {

    private final GroupRepository groupRepository;
    private final GestorNoteServices gestorNoteServices;
    private final UploadRepository uploadRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void processFile(StructureJson json) {
        Optional<String> nameTableOptional = getTablaCluster(Integer.parseInt(json.getGroupId()));
        nameTableOptional.ifPresent(nameTable -> queryDataNote(nameTable, json.getNameFileS3()));
    }

    private Optional<String> getTablaCluster(int id) {
        return groupRepository.findById(id)
                .map(GroupEntity::getName);
    }

    private void queryDataNote(String nameTable, String nameDocument) {
        final String sqlQuery = getSqlNote(nameTable, nameDocument);
        Query query = entityManager.createNativeQuery(sqlQuery, NoteEntity.class);
        @SuppressWarnings("unchecked")
        Optional<List<NoteEntity>> lstNotes = Optional.ofNullable(query.getResultList());
        lstNotes.ifPresent(notes -> processDataNote(notes, nameTable));
    }

    private String getSqlNote(String nameTable, String nameDocument) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select tu.upl_id,");
        queryBuilder.append("tc.* ");
        queryBuilder.append("from tbl_teacher tt ");
        queryBuilder.append("inner join tbl_upload tu ");
        queryBuilder.append("on tt.tea_document = tu.tea_document ");
        queryBuilder.append("inner join tbl_students_group tsg ");
        queryBuilder.append("on tt.tea_id = tsg.tea_id ");
        queryBuilder.append("inner join tbl_students ts ");
        queryBuilder.append("on ts.stu_document = tsg.stu_document ");
        queryBuilder.append("inner join ".concat(nameTable).concat(" tc "));
        queryBuilder.append("on ts.stu_document = tc.stu_document ");
        queryBuilder.append("where ");
        queryBuilder.append("tu.upl_status = false ");
        queryBuilder.append("and ");
        queryBuilder.append("tu.upl_name_document = '".concat(nameDocument).concat("' "));
        queryBuilder.append("and ");
        queryBuilder.append("tc.final_note is null ");
        queryBuilder.append("and ");
        queryBuilder.append("tc.status = false;");
        return queryBuilder.toString();
    }

    private void processDataNote(List<NoteEntity> lstNote, String table) {
        List<Long> lstIds = new ArrayList<>();
        List<Double> lstPromedio = new ArrayList<>();

        if (!lstNote.isEmpty()) {
            Integer idUpload = lstNote.get(0).getIdUp();
            lstNote.stream().forEach(note -> {
                double promedio = calculatePromedio(note);
                lstIds.add(note.getId());
                lstPromedio.add(promedio);
            });
            this.gestorNoteServices.setGestionNote(table, lstIds, lstPromedio);
            this.uploadRepository.updateStatus(idUpload, true);
        } else
            UtilLogger.error("La lista de notas se encentra vacia, Por tanto no se puede procesar", lstNote.toString());
    }

    private double calculatePromedio(NoteEntity note) {
        return (note.getNote1() + note.getNote2() + note.getNote3()) / 3;
    }

}
