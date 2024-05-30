package com.note.validation.domain.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.note.validation.domain.services.GestorNoteServices;
import com.note.web.util.UtilLogger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GestionImpl implements GestorNoteServices {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void setGestionNote(String nameTable, List<Long> lstIds, List<Double> lstPromedio) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("UPDATE ".concat(nameTable).concat(" SET final_note = CASE "));
        IntStream.range(0, Math.min(lstIds.size(), lstPromedio.size()))
                .forEach(i -> {
                    Long id = lstIds.get(i);
                    Double promedio = lstPromedio.get(i);
                    sqlQuery.append("WHEN id = " + id + " THEN " + promedio + "\n");
                });
        sqlQuery.append("END, status = true WHERE id IN (" + getIdsString(lstIds) + ");");
        entityManager.createNativeQuery(sqlQuery.toString()).executeUpdate();
        UtilLogger.info("Actualizacion de documento - completado.");
    }

    private String getIdsString(List<Long> lstIds) {
        return lstIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}